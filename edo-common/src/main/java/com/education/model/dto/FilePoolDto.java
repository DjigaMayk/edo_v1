package com.education.model.dto;

import com.education.model.enumEntity.EnumFileType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Schema(description = "Класс FilePoolDto, dto для класса FilePool.class")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilePoolDto {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "ID хранилища файла")
    private UUID storageFileId;

    @Schema(description = "Имя хранилища файла")
    private String name;

    @Schema(description = "Расширение файла")
    private String extension;

    @Schema(description = "Тип файла")
    private EnumFileType fileType;

    @Schema(description = "Размер файла")
    private Integer size;

    @Schema(description = "Количество страниц файла")
    private Integer pageCount;

    @Schema(description = "Дата загрузки файла")
    private ZonedDateTime uploadDate;

    @Schema(description = "Дата архивирования файла")
    private ZonedDateTime archivedDate;

    @Schema(description = "Автор файла")
    private EmployeeDto creator;

}
