package com.education.service.filepool;

import com.education.entity.FilePool;
import com.education.model.dto.FilePoolDto;
import com.education.service.BaseInterface;

import java.util.List;

public interface FilePoolService extends BaseInterface<FilePoolDto> {

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
}
