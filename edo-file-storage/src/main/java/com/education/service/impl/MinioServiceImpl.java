package com.education.service.impl;

import com.education.feign.feign_file_pool.FilePoolFeignClient;
import com.education.service.MinioService;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
@EnableRabbit
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;
    private final FilePoolFeignClient filePoolFeignClient;

    @Value("${minio.bucket}")
    private String bucketName;

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
    public boolean deleteFileInBucketWithName(String bucketName, String fileName) {
        log.info("Запрос на удаление объекта из MinIO. Баскет: " + bucketName + "; Имя объекта: " + fileName);
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build();
        try {
            minioClient.removeObject(removeObjectArgs);
            log.info("Объект удален");
            return true;
        } catch (Exception e) {
            log.error("Ошибка при удалении объекта из MinIO: ", e);
            return false;
        }
    }

    @Override
    @RabbitListener(queues = "fileStorageQueue")
    public void deleteFileWhoseRequestsMoreThanFiveYearsAgo() {
        log.info("Начало процедуры удаления файлов, которые заархивированы более 5 лет назад");
        List<UUID> listUuid = filePoolFeignClient.getListUuidFilesArchivedMoreFiveYearsAgo();
        if (listUuid != null && !listUuid.isEmpty()) {
            log.info("Получен список UUID для удаления. Содержит " + listUuid.size() + " элементов.");
            for (UUID uuid : listUuid) {
                if (deleteFileInBucketWithName(bucketName, uuid.toString())) {
                    log.info("Отправка команды на удаления записи про объект с UUID: [" + uuid + "] из БД");
                    filePoolFeignClient.deleteByUuid(uuid);
                }
            }
        }
    }

}

