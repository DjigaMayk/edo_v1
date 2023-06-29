package facsimileTest;

import com.education.EdoRestApplication;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

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
public class FacsimileControllerTest {

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/rest/facsimile";
    }

    @Test
    public void testSaveMatchingFile() {
        given().contentType(ContentType.MULTIPART)
                .body("MatchFile.jpg")
                .when().post(getRootUrl())
                .then().statusCode(201);
    }

    @Test
    public void testSaveUnmatchingFile() {
        given().contentType(ContentType.MULTIPART)
                .body("UnmatchedFile.jpg")
                .when().post(getRootUrl())
                .then().statusCode(500)
                .body(containsString("Picture should 100x100px"));
    }
}
