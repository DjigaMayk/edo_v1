package com.education.email.service;

import com.education.model.dto.AppealDto;
import com.education.model.dto.EmployeeDto;
import jakarta.mail.MessagingException;

import java.io.FileNotFoundException;

/**
 * Интерфейс EmailService.
 * Интерфейс для отправки email
 */
public interface EmailService {

    /**
     * Метод для поиска Appeal в БД
     * @param id
     */
    AppealDto findByIdAppeal(Long id);

    /**
     * Метод для поиска Employee в БД
     * @param id
     */
    EmployeeDto findByIdEmployee(Long id);

    /**
     * Метод для отправки email без attachment
     * @param toAddress
     * @param subject
     * @param message
     */
    void sendSimpleEmail(String toAddress, String subject, String message);

    /**
     * Метод для отправки email
     * @param toAddress
     * @param subject
     * @param message
     */
    void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment)
            throws MessagingException, FileNotFoundException;

    void markMailIsSent(Long appealId);
}
