package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import weatherService.RequestSpec;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class MyStepdefs {

    private static RequestSpecification requestSpecification;
    private final String key = "bb98b761a6a29f43187db0c088800fc4";
    private static Response response;


    @Given("{string} city weather info received")
    public void cityWeatherInfoReceived(String city) {

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        requestSpecification = RequestSpec.baseRequestSpecJson();

        Map<String, String> query = new HashMap<>();
            query.put("access_key", key);
            query.put("query", city);

        response = given()
                .spec(requestSpecification)
                .queryParams(query)
                .when().get("current");
    }

    @When("Status code is equal {int}")
    public void status_code_is_equal(int int1) {
        Assertions.assertEquals(int1, response.statusCode());
    }

    @When("Response of current weather has the expected Json schema from a file {string}")
    public void response_of_current_weather_has_the_expected_json_schema(String filePath) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(filePath));
    }

    @Then("A value from jsonPath {string} is equal to {string}")
    public void a_value_from_json_path_is_equal_to(String path, String expected) {
        JsonPath jsonPath = response.jsonPath();
        String actual = jsonPath.getString(path);
        Assertions.assertEquals(expected, actual);
    }
}
