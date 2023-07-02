package resolutionNotificationTest;

import com.education.EdoIntegrationApplication;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.internet.MimeMessage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = EdoIntegrationApplication.class)
@TestPropertySource(properties = {
        "spring.mail.host=localhost",
        "spring.mail.port=2525",
        "spring.mail.protocol=smtp",
        "spring.mail.properties.mail.smtp.auth=false",
        "spring.mail.properties.mail.smtp.starttls.enable=false",
        "spring.mail.test-connection=true"
})

public class ResolutionNotificationIntegrationTest {

    private static GreenMail smtpServer;

    @Autowired
    private JavaMailSender javaMailSender;

    @BeforeClass
    public static void setUp() {
        smtpServer = new GreenMail(new ServerSetup(2525, null, "smtp"))
                .withConfiguration(GreenMailConfiguration.aConfig().withDisabledAuthentication());
        smtpServer.start();
    }

    @AfterClass
    public static void tearDown() {
        smtpServer.stop();
    }


    @Test
    public void testEmailSending() throws MessagingException, jakarta.mail.MessagingException {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
//        messageHelper.setFrom("sender@example.com");
//        messageHelper.setTo("recipient@example.com");
//        messageHelper.setSubject("Test Email");
//        messageHelper.setText("This is a test email");
//
//        javaMailSender.send(mimeMessage);

        // Wait for the email to be delivered and received by the mock SMTP server
        smtpServer.waitForIncomingEmail(2000, 1);

        // Retrieve the captured emails from the mock SMTP server
        MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();

        // Assert the number of captured emails
        assertEquals(3, receivedMessages.length);

        // Assert the content of the captured email
        MimeMessage receivedMessage = receivedMessages[0];
        assertEquals("Test Email", receivedMessage.getSubject());
        assertEquals("sender@example.com", receivedMessage.getFrom()[0].toString());
        assertEquals("recipient@example.com", receivedMessage.getAllRecipients()[0].toString());
    }
}

