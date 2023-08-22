package facsimileTest;

import com.education.EdoRestApplication;
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

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
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
    private MockMvc mockMvc;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/rest/facsimile";
    }


    @Test
    public void testSaveMatchingFile() throws Exception {

        File file = new File(TestJsonStrings.IMAGE_CHECKED);
        FileInputStream inputStream = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("facsimile", "MatchFile.jpg",
                "image/jpeg", IOUtils.toByteArray(inputStream));

        mockMvc.perform(MockMvcRequestBuilders.multipart(getRootUrl())
                        .file(multipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveUnmatchingFile() {
        try {
            File file = new File(TestJsonStrings.IMAGE_UNCHECKED);
            FileInputStream inputStream = new FileInputStream(file);
            MockMultipartFile multipartFile = new MockMultipartFile("facsimile", "MatchFile.jpg",
                    "image/jpeg", IOUtils.toByteArray(inputStream));

            mockMvc.perform(MockMvcRequestBuilders.multipart(getRootUrl())
                            .file(multipartFile)
                            .contentType(MediaType.MULTIPART_FORM_DATA))
                    .andExpect(status().isBadRequest());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            assertEquals("400 : \"Facsimile should be jpg or png and should less than 100x100px\"", ex.getCause().getMessage());
        }
    }

    @Test
    public void testSaveFacsimileEntity() {
        given().contentType("application/json")
                .body(TestJsonStrings.STRING_FOR_ENTITY)
                .when().post(getRootUrl() + "/json")
                .then().statusCode(200);
    }

    @Test
    public void testArchiveFacsimile() {
        given().contentType("application/json")
                .body(TestJsonStrings.STRING_FOR_ARCHIVE)
                .when().delete(getRootUrl() + "/archive")
                .then().statusCode(200);
    }

    @Test
    public void testUnarchiveFacsimile() {
        given().contentType("application/json")
                .body(TestJsonStrings.STRING_FOR_UNARCHIVE)
                .when().delete(getRootUrl() + "/archive")
                .then().statusCode(200);
    }
}
