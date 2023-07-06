package appealTest;

import com.education.EdoRestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Интеграционный тест отправки appeal.
 *
 * Для запуска требуется запустить следующие модули:
 * edo-cloud-server
 * edo-service
 * edo-repository
 * postgres_SQL
 * edo_db необходимо заполнить тестовыми данными за исключением раздела resolution:
 * edo-repository/src/main/resources/db.populating/tables_populating_for_tests.sql
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = EdoRestApplication.class)
public class AppealControllerIntegrationTest {


    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/rest/appeal";
    }
    private String getEditAppealUrl() {
        return "http://localhost:" + port + "/api/rest/appeal/edit";
    }


    @Test
    public void testSaveSingleAppeal() {
        given().contentType("application/json")
                .body(TestJsonStrings.SINGLE_APPEAL)
                .when().post(getRootUrl())
                .then().statusCode(201);
    }

    @Test
    public void testEditSingleAppeal() {
        given().contentType("application/json")
                .body(TestJsonStrings.CHANGED_SINGLE_APPEAL)
                .when().put(getEditAppealUrl())
                .then()
                .statusCode(200)
                .and()
                .body("annotation", equalTo("some new annotation"));
    }

    @Test
    public void testEditSingleAppealWithNotExistsId() {
        given().contentType("application/json")
                .body(TestJsonStrings.SINGLE_APPEAL_WITH_NOT_EXISTS_ID)
                .when().put(getEditAppealUrl())
                .then()
                .statusCode(404);
    }

    @Test
    public void testSaveMultipleAppeals() {
        given().contentType("application/json")
                .body(TestJsonStrings.MULTIPLE_APPEALS)
                .when().post(getRootUrl())
                .then().statusCode(400);
    }

    @Test
    public void testSaveAppealWithoutAuthor() {
        given().contentType("application/json")
                .body(TestJsonStrings.APPEAL_WITHOUT_AUTHORS)
                .when().post(getRootUrl())
                .then().statusCode(400)
                .body(containsString("this appeal has no authors"));
    }


    @Test
    public void testSaveAppealWithAuthor() {
        given().contentType("application/json")
                .body(TestJsonStrings.APPEAL_WITH_SINGLE_AUTHOR)
                .when().post(getRootUrl())
                .then().statusCode(201);
    }

    @Test
    public void testSaveAppealWithMultipleAuthors() {
        given().contentType("application/json")
                .body(TestJsonStrings.APPEAL_WITH_MULTIPLE_AUTHORS)
                .when().post(getRootUrl())
                .then().statusCode(201);
    }

    @Test
    public void testSaveAppealWithSingleQuestion() {
        given().contentType("application/json")
                .body(TestJsonStrings.APPEAL_WITH_SINGLE_QUESTION)
                .when().post(getRootUrl())
                .then().statusCode(201);
    }

    @Test
    public void testSaveAppealWithMultipleQuestions() {
        given().contentType("application/json")
                .body(TestJsonStrings.APPEAL_WITH_MULTIPLE_QUESTIONS)
                .when().post(getRootUrl())
                .then().statusCode(201);
    }

    @Test
    public void testSaveAppealWithFilePool() {
        given().contentType("application/json")
                .body(TestJsonStrings.APPEAL_WITH_FILEPOOL)
                .when().post(getRootUrl())
                .then().statusCode(201);
    }

    @Test
    public void testSaveAppealWithoutFilePool() {
        given().contentType("application/json")
                .body(TestJsonStrings.APPEAL_WITHOUT_FILEPOOL)
                .when().post(getRootUrl())
                .then().statusCode(201);
    }

    public void testSaveAppealWithValidFields() {
        given().contentType("application/json")
                .body(TestJsonStrings.APPEAL_WITH_VALID_FIELDS)
                .when().post(getRootUrl())
                .then().statusCode(201);
    }

    @Test
    public void testSaveAppealWithInvalidFields() {
        given().contentType("application/json")
                .body(TestJsonStrings.APPEAL_WITH_INVALID_FIELDS)
                .when().post(getRootUrl())
                .then().statusCode(400);
    }
}