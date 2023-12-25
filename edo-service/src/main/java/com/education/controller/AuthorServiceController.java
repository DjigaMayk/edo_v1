package com.education.controller;

import com.education.Utils.QuestionUtil;
import com.education.service.author.AuthorService;
import com.education.model.dto.AuthorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/service/author")
@AllArgsConstructor
@Log4j2
@Tag(name = "Контроллер Автора", description = "Контроллер Автора для выполнения веб-запросов (модуль edo-service)")
public class AuthorServiceController {


    private final AuthorService authorService;

    @Operation(summary = "Поиск сущности по id")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> showById(@PathVariable("id") Long id) {
        Optional<AuthorDto> authorDto = Optional.ofNullable(authorService.findById(id));
        if (authorDto.isEmpty()) {
            log.warn("Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Сущность найдена");
        return new ResponseEntity<>(authorDto.get(), HttpStatus.OK);
    }

    @Operation(summary = "Поиск сущностей по значениям их id")
    @PostMapping("/findAll")
    public ResponseEntity<List<AuthorDto>> showAllById(@RequestBody List<Long> ids) {
        List<AuthorDto> authorDtos = authorService.findAllById(ids);
        if (authorDtos == null && authorDtos.isEmpty()) {
            log.warn("Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Сущности найдены");
        return new ResponseEntity<>(authorDtos, HttpStatus.OK);
    }

    @Operation(summary = "Сохранение сущности")
    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto author) {
        AuthorDto authorDto = authorService.save(author).getBody();
        if (authorDto.getId() != null) {
            log.info("Сущность сохранена или обновлена");
            return new ResponseEntity<>(authorDto, HttpStatus.CREATED);
        }
        log.warn("Сущность не сохранена и не обновлена");
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @Operation(summary = "Удаление сущности")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        log.info("удалил AuthorDto.class");
        return ResponseEntity.ok("DELETED");
    }

    @Operation(summary = "Поиск Автора по ФИО")
    @GetMapping("/byFIO/")
    public ResponseEntity<List<AuthorDto>> findAuthorByFIO(@RequestParam(value = "fio", required = false) String fio) {
        String transformFio = QuestionUtil.textTransformer(fio);
        String decodedFio = URLDecoder.decode(transformFio, StandardCharsets.UTF_8);
        log.info("Получен запрос на поиск сущностей {}", decodedFio);
        List<AuthorDto> dtos = authorService.findAuthorByFIO(decodedFio);
        if (!dtos.isEmpty()) {
            log.info("Результат поиска сущностей: {}", dtos);
        }else {
            log.warn("Результат поиска сущностей: {}", dtos);
        }
        return new ResponseEntity<>(dtos, !dtos.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
