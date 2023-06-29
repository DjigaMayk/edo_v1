package facsimileTest;

import com.education.EdoRestApplication;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.ByteArrayOutputStream;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тест для сохранения файла facsimile
 * Для запуска требуется запустить следующие модули:
 * edo-cloud-server
 * edo-service
 * edo-repository TODO
 * edo-file-storage TODO
 * А также запустить Minio
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = EdoRestApplication.class)
@AutoConfigureMockMvc
public class FacsimileControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/rest/facsimile";
    }


    @Test
    public void testSaveMatchingFile() throws Exception {
        byte[] file = "MatchFile.jpg".getBytes();
        ByteArrayResource resource = new ByteArrayResource(file) {
            @Override
            public String getFilename() {
                return "MatchFile.jpg";
            }
        };

        mockMvc.perform(MockMvcRequestBuilders.multipart(getRootUrl())
                .file("facsimile", resource.getByteArray())
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("Upload Success"));
    }

//    @Test
//    public void testSaveUnmatchingFile() {
//        given().contentType(ContentType.MULTIPART)
//                .body("UnmatchedFile.jpg")
//                .when().post(getRootUrl())
//                .then().statusCode(500)
//                .body(containsString("Picture should 100x100px"));
//    }
}
