package steps;

import dto.response.CurrentWeatherResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weatherService.ApiCore;
import weatherService.Endpoints;
import weatherService.RestResponseInt;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class MyStepdefs {

    private static final Logger log = LoggerFactory.getLogger(MyStepdefs.class);

    private final ApiCore apiCore;
    private static Response response;

    public MyStepdefs() {apiCore = new ApiCore();}

    @Given("{string} city weather info received")
    public void cityWeatherInfoReceived(String city) {
        response = apiCore.getWeatherForACity(city, Endpoints.getCurrent());
    }

    @When("Status code is equal {int}")
    public void status_code_is_equal(int int1) {
        Assert.assertEquals(int1, response.statusCode());
    }

    @When("Response of current weather has the expected Json schema from a file {string}")
    public void response_of_current_weather_has_the_expected_json_schema(String filePath) {
        log.debug("FilePath: {}", filePath);
        response.then().assertThat().body(matchesJsonSchemaInClasspath(filePath));
    }

    @Then("A value from jsonPath {string} is equal to {string}")
    public void a_value_from_json_path_is_equal_to(String path, String expected) {
        log.debug("JsoPath: {}", path);
        String actual = JsonPath.from(response.asString()).getString(path);
        Assert.assertEquals(expected, actual);
    }

    @Then("A response by {string} matches current weather object")
    public void aResponseMatchesCurrentWeatherObject(String city) {
        RestResponseInt<CurrentWeatherResponse> resp = apiCore.getWeatherForACityAsObject(city, Endpoints.getCurrent());
        Assert.assertTrue(resp.isSuccessful());
        Assert.assertEquals(city, resp.getBody().location.name);
    }
}
