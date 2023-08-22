package com.education.entity;

import com.education.model.enumEntity.EnumFileType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Представляет хранилище файла
 * @author Рамазан Гаджиев
 */

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Getter
@Setter
@Table(name = "file_pool")
public class FilePool extends BaseEntity{
    /**
     * ID хранилища файла
     */
    @Column(name = "storage_file_id")
    private UUID storageFileId;
    /**
     * Имя хранилища файла
     */
    @Column(name = "file_name")
    private String name;
    /**
     * Расширение файла
     */
    @Column(name = "extension")
    private String extension;
    /**
     * Тип файла
     */
    @Column(name = "file_type")
    @Enumerated(EnumType.STRING)
    private EnumFileType fileType;
    /**
     * Размер файла
     */
    @Column(name = "file_size")
    private Integer size;
    /**
     * Количество страниц файла
     */
    @Column(name = "page_count")
    private Integer pageCount;
    /**
     * Дата загрузки файла
     */
    @Column(name = "upload_date")
    private ZonedDateTime uploadDate;
    /**
     * Дата архивирования файла
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;
    /**
     * Автор файла
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Employee creator;

}
