package resolutionNotificationTest;

import com.education.EdoIntegrationApplication;
import org.apache.james.mailbox.MailboxManager;
import org.apache.james.mailbox.exception.MailboxException;
import org.apache.james.mailbox.model.MailboxPath;
import org.apache.james.smtpserver.netty.SMTPServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

/**
 * Интеграционный тест отправки уведомлений после создания resolution.
 * Для запуска требуется успешно завершить тест на создание resolution
 * и следующие модули должны быть уже запущены:
 * rabbitMQ server
 * edo-cloud-server
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = EdoIntegrationApplication.class)
@TestPropertySource(properties = {
        "spring.mail.host=localhost",
        "spring.mail.port=2525",
        "spring.mail.username=user@example.com",
        "spring.mail.password=password",
        "spring.mail.protocol=smtp",
        "spring.mail.properties.mail.smtp.auth=true",
        "spring.mail.properties.mail.smtp.starttls.enable=false",
        "spring.mail.test-connection=true"
})
public class ResolutionNotificationIntegrationTest {

    private SMTPServer smtpServer;

    @Before
    public void setup() {
        smtpServer = new SMTPServer();
        smtpServer.start();
    }

    @After
    public void cleanup() {
        smtpServer.stop();
    }

    @Test
    public void testEmailSending() throws MessagingException, MailboxException {

        MailboxManager mailboxManager = smtpServer.getMailboxManager();
        MailboxPath mailboxPath = new MailboxPath("INBOX", "user@example.com"); // Specify the mailbox path for the captured emails
        Builder searchQueryBuilder = new SearchQuery.Builder();
        SearchQuery searchQuery = searchQueryBuilder.all().build();
        Iterable<MessageResult> messages = mailboxManager.search(mailboxPath, searchQuery);

        int count = 0;
        for (MessageResult messageResult : messages) {
            count++;
        }
        assertEquals(3, count);

        for (MessageResult messageResult : messages) {
            MimeMessage mimeMessage = ((MailImpl) messageResult.getMail()).getMessage();
            assertEquals("Subject", mimeMessage.getSubject());
            assertEquals("sender@example.com", mimeMessage.getFrom()[0].toString());
            assertEquals("recipient@example.com", mimeMessage.getAllRecipients()[0].toString());
        }
    }
}
