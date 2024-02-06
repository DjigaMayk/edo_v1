package com.education.service.impl;

import com.education.service.MinioService;
import io.minio.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.UUID;

@Slf4j
@Service
public class MinioServiceImpl implements MinioService {
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    @Autowired
    public MinioServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public UUID uploadFile(byte[] request) {
        UUID uuid = UUID.randomUUID();
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(uuid.toString())
                    .stream(new ByteArrayInputStream(request), request.length, -1)
                    .build());

            return uuid;
        } catch (Exception e) {
            log.error("Happened error when upload file: ", e);
            throw new RuntimeException(e);
        }
    }

    public InputStream getObject(String filename) {
        InputStream stream;
        try {
            stream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filename)
                    .build());
        } catch (Exception e) {
            log.error("Happened error when get list objects from minio: ", e);
            throw new RuntimeException(e);
        }

        return stream;
    }

    @Override
    public void deleteFileInBucketWithName(String bucketName, String fileName) {
        log.info("Запрос на удаление объекта из MinIO. Баскет: " + bucketName + "; Имя объекта: " + fileName);
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build();
        try {
            minioClient.removeObject(removeObjectArgs);
            log.info("Объект удален");
        } catch (Exception e) {
            log.error("Ошибка при удалении объекта из MinIO: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFileWhoseRequestsMoreThanFiveYearsAgo() {
        ZonedDateTime fiveYearsAgo = ZonedDateTime.now().minusYears(5);
        log.info("Начало процедуры проверки объектов из MinIO на старость (последний запрос был более 5 лет).");
        ListObjectsArgs listObjectsArgs = ListObjectsArgs.builder()
                .bucket(bucketName)
                .build();
        Iterable<Result<Item>> listObjects = minioClient.listObjects(listObjectsArgs);
        Iterator<Result<Item>> iterator = listObjects.iterator();
        while (iterator.hasNext()) {
            try {
                Item item = iterator.next().get();
                log.info("Объект " + item.objectName() + ". Последний запрос к объекту: " + item.lastModified());
                if (item.lastModified().isBefore(fiveYearsAgo)) {
                    deleteFileInBucketWithName(bucketName, item.objectName());
                }
            } catch (Exception e) {
                log.error("Ошибка при получении объекта из MinIO: ", e);
                throw new RuntimeException(e);
            }
        }
    }

}

