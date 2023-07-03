package resolutionNotificationTest;

import com.education.EdoIntegrationApplication;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessagingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Интеграционный тест отправки уведомлений после создания resolution.
 * Для запуска требуется успешно завершить тест на создание resolution
 * и следующие модули должны быть уже запущены:
 * rabbitMQ server
 * edo-cloud-server
 * edo-rest
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = EdoIntegrationApplication.class)
@TestPropertySource(properties = {
        "spring.mail.host=localhost",
        "spring.mail.port=9025",
        "spring.mail.protocol=smtp",
        "spring.mail.properties.mail.smtp.auth=false",
        "spring.mail.properties.mail.smtp.starttls.enable=false",
        "spring.mail.test-connection=true"
})
@Slf4j
public class ResolutionNotificationIntegrationTest {

    private static GreenMail smtpServer;

    @BeforeClass
    public static void setUp() {
        smtpServer = new GreenMail(new ServerSetup(9025, null, "smtp"))
                .withConfiguration(GreenMailConfiguration.aConfig().withDisabledAuthentication());
        smtpServer.start();
    }

    @AfterClass
    public static void tearDown() {
        smtpServer.stop();
    }


    @Test
    public void testEmailSending() throws MessagingException, jakarta.mail.MessagingException, IOException {

        // Wait for the email to be delivered and received by the mock SMTP server
        smtpServer.waitForIncomingEmail(120000, 1);

        // Retrieve the captured emails from the mock SMTP server
        MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();

        MimeMessage receivedMessage1 = receivedMessages[0];
        MimeMessage receivedMessage2 = receivedMessages[1];
        MimeMessage receivedMessage3 = receivedMessages[2];
        log.info(String.valueOf(receivedMessage1.getAllRecipients().length));
        log.info(Arrays.toString(receivedMessage1.getAllRecipients()));
        log.info(receivedMessage1.getSubject());
        log.info(receivedMessage1.getContent().toString());

        // Assert the number of captured emails
        assertEquals(3, receivedMessages.length);

        // Assert the content of the captured email
        assertEquals("Уведомление о создании резолюции", receivedMessage1.getSubject());
        assertEquals("iv@ya.ru", receivedMessage1.getAllRecipients()[0].toString());
        assertEquals("Уведомление о создании резолюции", receivedMessage2.getSubject());
        assertEquals("petrov@ya.ru", receivedMessage2.getAllRecipients()[0].toString());
        assertEquals("Уведомление о создании резолюции", receivedMessage3.getSubject());
        assertEquals("ig@ya.ru", receivedMessage3.getAllRecipients()[0].toString());
    }
}

