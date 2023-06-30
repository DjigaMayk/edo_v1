package resolutionNotificationTest;

import com.education.EdoIntegrationApplication;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import jakarta.mail.internet.MimeMessage;

import static org.junit.Assert.assertEquals;

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

    private GreenMail greenMail;

    @Autowired
    private JavaMailSender javaMailSender;

    @Before
    public void startMailServer() {
        greenMail = new GreenMail(ServerSetupTest.SMTP);
        greenMail.start();
    }

    @After
    public void stopMailServer() {
        greenMail.stop();
    }

    @Test
    public void testEmailSending() throws MessagingException, javax.mail.MessagingException, jakarta.mail.MessagingException {
//        // Wait for the email to be delivered and received by the mock SMTP server
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Retrieve the captured emails from the mock SMTP server
//        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
//
//        // Assert the number of captured emails
//        assertEquals(3, receivedMessages.length);
//
//        // Assert the content of each captured email
//        for (MimeMessage message : receivedMessages) {
//            assertEquals("Subject", message.getSubject());
//            assertEquals("sender@example.com", message.getFrom()[0].toString());
//            assertEquals("recipient@example.com", message.getAllRecipients()[0].toString());
//        }
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo("recipient@example.com");
        helper.setSubject("Test Email");
        helper.setText("This is a test email");

        javaMailSender.send(message);

        // Assert that the email was received
        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        Assert.assertEquals(1, receivedMessages.length);
    }
}

