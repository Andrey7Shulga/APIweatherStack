import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import weatherService.RequestSpec;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class Tests {

    private static RequestSpecification requestSpecification;
    private final String key = "bb98b761a6a29f43187db0c088800fc4";

    @BeforeAll
    static void globalInit() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        requestSpecification = RequestSpec.baseRequestSpecJson();
    }

    @Test
    void get1() {

        Map<String, String> query = new HashMap<>();
            query.put("access_key", key);
            query.put("query", "London");

        given()
                .spec(requestSpecification)
                .queryParams(query)
        .when()
                .get("current")
        .then()
                .statusCode(200);

    }







}
