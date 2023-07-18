package ResolutionTest;

import com.education.EdoRestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.given;


/**
 * Интеграционный тест сохранения резолюций.
 * Для запуска требуется запустить следующие модули:
 * edo-cloud-server
 * edo-service
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = EdoRestApplication.class)
public class ResolutionTest {
    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/rest/resolution";
    }

    @Test
    public void testSaveSingleResolution() {
        given().contentType("application/json")
                .body(TestJsonStrings.SINGLE_RESOLUTION)
                .when().post(getRootUrl())
                .then().statusCode(201);
    }

    @Test
    public void testSaveResolutionWithoutExecutors() {
        given().contentType("application/json")
                .body(TestJsonStrings.RESOLUTION_WITHOUT_EXECUTORS)
                .when().post(getRootUrl())
                .then().statusCode(400);
    }

    @Test
    public void testSaveResolutionWithoutAddressers() {
        given().contentType("application/json")
                .body(TestJsonStrings.RESOLUTION_WITHOUT_ADDRESSERS)
                .when().post(getRootUrl())
                .then().statusCode(400);
    }

    @Test
    public void testSaveResolutionWithoutSigners() {
        given().contentType("application/json")
                .body(TestJsonStrings.RESOLUTION_WITHOUT_SIGNERS)
                .when().post(getRootUrl())
                .then().statusCode(400);
    }

    @Test
    public void testSaveResolutionWithOneAddressee() {
        given().contentType("application/json")
                .body(TestJsonStrings.RESOLUTION_WITH_ONE_ADDRESSEE)
                .when().post(getRootUrl())
                .then().statusCode(201);
    }

    @Test
    public void testSaveResolutionWithSeveralAddressee() {
        given().contentType("application/json")
                .body(TestJsonStrings.RESOLUTION_WITH_SEVERAL_ADDRESSEE)
                .when().post(getRootUrl())
                .then().statusCode(201);
    }
}
