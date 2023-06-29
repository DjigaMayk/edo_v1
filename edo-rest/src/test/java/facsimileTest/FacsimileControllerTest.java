package facsimileTest;

import com.education.EdoRestApplication;
import com.education.controller.FacsimileController;
import jakarta.ws.rs.core.MediaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тест для сохранения файла facsimile
 * Для запуска требуется запустить следующие модули:
 * edo-cloud-server
 * edo-service
 * edo-repository TODO
 * edo-file-storage TODO
 * А также запустить Minio TODO
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = EdoRestApplication.class)
@AutoConfigureMockMvc
public class FacsimileControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacsimileController controller;

    @Autowired
    private MockMvc mockMvc;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/rest/facsimile";
    }


    @Test
    public void testSaveMatchingFile() throws Exception {

        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_BGR);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);

        byte[] image = baos.toByteArray();

        MultipartFile multipartFile = new MockMultipartFile("image.png", image);

        mockMvc.perform(MockMvcRequestBuilders.multipart(getRootUrl())
                .file("facsimile", multipartFile.getBytes())
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().string("Facsimile saved"));
    }
}
