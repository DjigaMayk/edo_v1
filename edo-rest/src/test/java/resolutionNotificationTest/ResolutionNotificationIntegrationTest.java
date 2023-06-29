package resolutionNotificationTest;

import com.education.EdoRestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.path.json.JsonPath;

/**
 * Интеграционный тест создания resolution.
 * Для запуска требуется запустить следующие модули:
 * rabbitMQ server
 * edo-cloud-server
 * edo-service
 * edo-repository
 * postgres_SQL
 * edo_db необходимо заполнить тестовыми данными за исключением раздела resolution:
 * edo-repository/src/main/resources/db.populating/tables_populating_for_tests.sql
 * после успешного завершения теста необходимо запустить интеграционный тест:
 * edo-integration/src/test/java/resolutionNotificationTest/ResolutionNotificationIntegrationTest.java
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = EdoRestApplication.class)
public class ResolutionNotificationIntegrationTest {

    @LocalServerPort
    private int port;

    public ResolutionNotificationIntegrationTest() throws IOException {
    }

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/rest/resolution";
    }

    @Test
    public void createResolutionAndTriggerNotifications() {
        given().contentType("application/json")
                .body(TestJsonStrings.SINGLE_RESOLUTION)
                .when().post(getRootUrl())
                .then()
                .statusCode(201)
                .and()
                .body("enumResolution", equalTo("RESOLUTION"));
    }

}