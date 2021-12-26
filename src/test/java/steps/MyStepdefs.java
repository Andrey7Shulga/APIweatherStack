package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import weatherService.ApiCore;
import weatherService.Endpoints;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class MyStepdefs {

    private final ApiCore apiCore = new ApiCore();
    private static Response response;


    @Given("{string} city weather info received")
    public void cityWeatherInfoReceived(String city) {
        response = apiCore.getWeatherForACity(city, Endpoints.getCurrent());
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
        String actual = JsonPath.from(response.asString()).getString(path);
        Assertions.assertEquals(expected, actual);
    }
}
