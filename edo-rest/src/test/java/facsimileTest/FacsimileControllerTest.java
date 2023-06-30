package facsimileTest;

import com.education.EdoRestApplication;
import com.education.controller.FacsimileController;
import jakarta.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тест для сохранения файла facsimile
 * Для запуска требуется запустить Minio и следующие модули:
 * edo-cloud-server
 * edo-service
 * edo-repository
 * edo-file-storage
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
        String imagePath = "src\\test\\java\\facsimileTest\\imagesForFacsimileTest\\MatchFile.jpg";

        File file = new File(imagePath);
        FileInputStream inputStream = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("facsimile", "MatchFile.jpg",
                "image/jpeg", IOUtils.toByteArray(inputStream));

        mockMvc.perform(MockMvcRequestBuilders.multipart(getRootUrl())
                .file(multipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveUnmatchingFile() throws Exception {
        String imagePath = "src\\test\\java\\facsimileTest\\imagesForFacsimileTest\\UnmatchedFile.jpg";

        File file = new File(imagePath);
        FileInputStream inputStream = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("facsimile", "MatchFile.jpg",
                "image/jpeg", IOUtils.toByteArray(inputStream));

        mockMvc.perform(MockMvcRequestBuilders.multipart(getRootUrl())
                .file(multipartFile)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }
}
