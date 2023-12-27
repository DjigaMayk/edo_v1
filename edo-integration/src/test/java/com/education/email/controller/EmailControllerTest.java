package com.education.email.controller;

import com.education.email.service.EmailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Модульный тест для контроллера {@link EmailController}.
 * @author Mustafa
 */
@WebMvcTest(EmailController.class)
@ExtendWith(MockitoExtension.class)
class EmailControllerTest {
    private static final String EMAIL = "test@example.com";
    private static final Long APPEAL_ID = 1L;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmailService emailService;

    @Test
    @DisplayName("Отправка письма с прикрепленным файлом, позитивный сценарий")
    void sendEmailWithAttachmentPositiveTest() throws Exception {
        mockMvc.perform(post("/email/sendEmailWithAttachment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(EMAIL))
                .andExpect(status().isOk());

        verify(emailService).sendEmailWithAttachment(EMAIL, null, "Example message", null);
    }

    @Test
    @DisplayName("Отправка письма с null значением электронной почты, негативный сценарий")
    void sendEmailWithNullEmailNegativeTest() throws Exception {
        mockMvc.perform(post("/email/sendEmailWithAttachment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": null}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Отправка письма автору с прикрепленным файлом, позитивный сценарий")
    void sendEmailToAuthorWithAttachmentPositiveTest() throws Exception {
        mockMvc.perform(post("/email/sendEmailToAuthorWithAttachment")
                        .param("appealId", APPEAL_ID.toString()))
                .andExpect(status().isOk());

        verify(emailService).sendEmailWithAttachment(emailService.findByIdAppeal(APPEAL_ID).getCreator().getWorkEmail(), null, "Example message", null);
    }

    @Test
    @DisplayName("Отправка письма автору с null значением appealId, негативный сценарий")
    void sendEmailToAuthorWithNullAppealIdNegativeTest() throws Exception {
        mockMvc.perform(post("/email/sendEmailToAuthorWithAttachment")
                        .param("appealId", (String) null))
                .andExpect(status().isBadRequest());

        verify(emailService).sendEmailWithAttachment(emailService.findByIdAppeal(APPEAL_ID).getCreator().getWorkEmail(), null, "Example message", null);
    }
}