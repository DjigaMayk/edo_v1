package com.education.controller.author;

import com.education.Utils.QuestionUtil;
import com.education.entity.Author;
import com.education.model.dto.AuthorDto;
import com.education.service.author.AuthorService;
import com.education.util.Mapper.impl.AuthorMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("api/repository/author")
@Tag(name = "Контроллер Автора", description = "Контроллер Автора для выполнения веб-запросов (модуль edo-repository)")
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper mapper;

    @Operation(summary = "Поиск сущности по id")
    @GetMapping("/{id}")
    public AuthorDto showById(@PathVariable("id") Long id) {
        log.info("отправил AuthorDto.class");
        return Author.authorToDto(authorService.findById(id));
    }

    @Operation(summary = "Поиск сущностей по значениям их id")
    @PostMapping("/findAll")
    public List<AuthorDto> showAllById(@RequestBody List<Long> ids) {
        log.info("отправил list AuthorDto.class");
        return authorService.findAllById(ids);
    }

    @Operation(summary = "Сохранение сущности")
    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto authorDto) {
        authorDto = authorService.save(authorDto);
        log.info("сохранил AuthorDto.class");
        return authorDto != null ?
                ResponseEntity.ok(authorDto)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Удаление сущности")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        log.info("удалил AuthorDto.class");
        return ResponseEntity.ok("DELETED");
    }

    @Operation(summary = "Поиск Автора по ФИО")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Author was successfully found"),
            @ApiResponse(code = 404, message = "Author was not found")})
    @GetMapping("/byFIO/")
    public ResponseEntity<List<AuthorDto>> findAuthorByFIO(@RequestParam(value = "fio", required = false) String fio) {
        String transformFio = QuestionUtil.textTransformer(fio);
        String decodedFio = URLDecoder.decode(transformFio, StandardCharsets.UTF_8);
        log.info("Получен запрос на поиск сущностей {0}", decodedFio);
        List<Author> dtos = authorService.findAuthorByFIO(decodedFio);
        List<AuthorDto> listDTO = mapper.toDto(dtos);
        if (!listDTO.isEmpty()) {
            log.info("Результат поиска сущностей: {0}", listDTO);
        }else {
            log.warn("Результат поиска сущностей: {0}", listDTO);
        }
        return new ResponseEntity<>(listDTO
                , !listDTO.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
