package com.education.service.filepool.impl;

import com.education.entity.FilePool;
import com.education.model.dto.FilePoolDto;
import com.education.repository.FilePoolRepository;
import com.education.service.AbstractService;
import com.education.service.filepool.FilePoolService;
import com.education.util.Mapper.impl.FilePoolMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service for entity FilePool
 */
@Service
public class FilePoolServiceImpl extends AbstractService<FilePoolRepository, FilePool, FilePoolDto, FilePoolMapper> implements FilePoolService {

    /**
     * Repository
     */
    private final FilePoolRepository repository;

    private final FilePoolMapper mapper;

    public FilePoolServiceImpl(FilePoolRepository repository, FilePoolMapper filePoolMapper, FilePoolRepository repository1, FilePoolMapper mapper) {
        super(repository, filePoolMapper);
        this.repository = repository1;
        this.mapper = mapper;
    }

    /**
     * Add in db method
     *
     * @param filePool FilePool
     * @return FilePool
     */
    @Transactional(rollbackFor = Exception.class)
    public FilePool add(FilePool filePool) {
        repository.save(filePool);
        return filePool;
    }

    /**
     * findById in db
     *
     * @param id Long
     * @return FilePoolDto
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public FilePoolDto findById(Long id) {
        FilePool filePool = repository.findById(id).orElse(null);
        return filePool != null ? mapper.toDto(filePool) : null;
    }

    /**
     * findAllById in db
     *
     * @param ids List<Long>
     * @return List<FilePoolDto>
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<FilePoolDto> findAllById(List<Long> ids) {
        return repository
                .findAllById(ids).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Move to archive in db
     *
     * @param id Long
     */
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id, boolean isArchived) {
        repository.moveToArchive(id, isArchived);
    }

    /**
     * Предоставляет не заархивированное FilePoolDto номенклатуры из БД по id
     *
     * @param id Long
     * @return FilePoolDto
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public FilePoolDto findByIdNotArchived(Long id) {
        FilePool filePool = repository.findByIdNotArchived(id).orElse(null);
        return filePool != null
                ? mapper.toDto(filePool)
                : null;
    }

    /**
     * Предоставляет список не заархивированных FilePoolDto номенклатур из БД по id
     *
     * @param list List of id
     * @return List of FilePoolDto
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<FilePoolDto> findAllByIdNotArchived(Iterable<Long> list) {
        List<FilePool> filePools = repository.findAllByIdNotArchived(list);

        return filePools != null
                ? filePools.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList())
                : null;
    }

    /**
     * Предоставляет список UUID тех файлов, которые находятся в архиве более 5 лет.
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<UUID> getListUuidFilesArchivedMoreFiveYearsAgo() {
        return repository.getListUuidFilesArchivedMoreFiveYearsAgo();
    }

    /**
     * Метод удаляет запись в БД по UUID. УДАЛЯЕТ ТОЛЬКО в БД!. Используется для удаления старых архивных файлов!
     * Использовать метод только если файл удален из файлового хранилища.
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<UUID> deleteFileByUuid(UUID uuid) {
        return repository.deleteFileByUuid(uuid);
    }

}
