package com.education.service.filepool;


import com.education.entity.FilePool;
import com.education.model.dto.FilePoolDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FilePoolService {

    /**
     * Add in db method
     *
     * @param filePool FilePool
     * @return FilePool
     */
    FilePool add(FilePool filePool);

    /**
     * findById in db
     *
     * @param id Long
     * @return FilePoolDto
     */
    FilePoolDto findById(Long id);

    /**
     * findAllById in db
     *
     * @param ids List<Long>
     * @return List<FilePoolDto>
     */
    List<FilePoolDto> findAllById(List<Long> ids);

    /**
     * Move to archive in db
     *
     * @param id Long
     */
    void moveToArchive(Long id, boolean isArchived);

    /**
     * Предоставляет не заархивированное FilePoolDto номенклатуры из БД по id
     *
     * @param id Long
     * @return FilePoolDto
     */
    FilePoolDto findByIdNotArchived(Long id);

    /**
     * Предоставляет список не заархивированных FilePoolDto номенклатур из БД по id
     *
     * @param list List of id
     * @return List of FilePoolDto
     */
    List<FilePoolDto> findAllByIdNotArchived(Iterable<Long> list);

    /**
     * Предоставляет список UUID тех файлов, которые находятся в архиве более 5 лет.
     */
    List<UUID> getListUuidFilesArchivedMoreFiveYearsAgo();

    /**
     * Метод удаляет запись в БД по UUID. УДАЛЯЕТ ТОЛЬКО в БД!. Используется для удаления старых архивных файлов!
     * Использовать метод только если файл удален из файлового хранилища.
     */
    Optional<UUID> deleteFileByUuid(UUID uuid);
}
