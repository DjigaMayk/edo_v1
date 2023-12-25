package com.education.email.controller;

import com.education.email.service.EmailService;
import com.education.model.dto.AppealDto;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

/**
 * RestController для работы с email сообщениями. Позволяет отправлять email обращения (appeal).
 */
@RestController
@AllArgsConstructor
@Tag(name = "RestController для отправки email обращений (appeal)")
@RequestMapping("/api/integration/email")
@Log4j2
public class EmailController {
    @ApiModelProperty("emailService")
    private EmailService emailService;

    /**
     * Метод для отправки письма с прикрепленным файлом.
     * @param email адрес получателя
     * @return ответ с сообщением о статусе отправки письма
     */
    @Operation(summary = "Отправка email с прикрепленным файлом")
    @GetMapping("/send/{user-email}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Письмо отправлено"),
            @ApiResponse(code = 500, message = "Письмо не отправлено. Ошибка сервера")
    })
    public @ResponseBody ResponseEntity<String> sendEmailWithAttachment(@PathVariable("user-email") String email) {
        try {
            emailService.sendEmailWithAttachment(email, null, "Example message", null);
        } catch (MailException | MessagingException | FileNotFoundException ex) {
            log.error("Error while sending out email..{}", ex.getStackTrace());
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }

    /**
     * Метод для отправки письма автору.
     * @param appealId идентификатор обращения
     * @return ответ с сообщением о статусе отправки письма автору
     * @author Mustafa
     */
    @Operation(summary = "Отправка email автору")
    @GetMapping("/send/author/{appealId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Письмо отправлено автору"),
            @ApiResponse(code = 500, message = "Письмо не отправлено. Ошибка сервера")
    })
    public @ResponseBody ResponseEntity<String> sendEmailToAuthor(@PathVariable("appealId") Long appealId) {
        try {
            final AppealDto appealDto = emailService.findByIdAppeal(appealId);
            emailService.sendEmailWithAttachment(appealDto.getCreator().getWorkEmail(), "Заголовок письма автора", "Example message for author", null);
            emailService.markMailIsSent(appealId);
            log.info("Message sent successfully to authors email {} with appealId {}", appealDto.getCreator().getWorkEmail(), appealId);
        } catch (MailException ex) {
            log.error("Error while sending out email..{}", (Object) ex.getStackTrace());
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (MessagingException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }
}
