package fileTest;

import com.education.EdoRestApplication;
import facsimileTest.TestJsonStrings;
import jakarta.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;
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

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = EdoRestApplication.class)
@AutoConfigureMockMvc
public class FileUploadControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/rest/file";
    }

    @Test
    public void testUploadFile() throws Exception {
        File file = new File(TestJsonStrings.IMAGE_CHECKED);
        FileInputStream inputStream = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "MatchFile.jpg",
                "image/jpeg", IOUtils.toByteArray(inputStream));

        mockMvc.perform(MockMvcRequestBuilders.multipart(getRootUrl())
                        .file(multipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }
}
