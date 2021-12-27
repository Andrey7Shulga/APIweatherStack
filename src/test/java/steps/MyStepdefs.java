package steps;

import dto.request.City;
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

import java.util.Objects;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class MyStepdefs {

    private static final Logger log = LoggerFactory.getLogger(MyStepdefs.class);

    private final ApiCore apiCore;
    private static Response response;
    private static CurrentWeatherResponse currentWeatherResponse;

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
        currentWeatherResponse = resp.getBody();
        Assert.assertEquals(city, currentWeatherResponse.location.name);
    }

    @Then("Checking out the city {string} data")
    public void checkingOutTheCityData(String cityName) {

        City sevastopol = new City("Sevastopol", "Russia", "44.600", "33.533", 10, 15, 980);
        City tokyo = new City("Tokyo", "Japan", "35.690", "139.692", 6, 15, 1010);
        City paris = new City("Paris", "France", "48.867", "2.333", 8, 9, 952);
        City london = new City("London", "United Kingdom", "51.517", "-0.106", 6, 14, 993);

        switch (cityName) {
            case "Sevastopol" -> checkAndLogDataDistinctions(sevastopol);
            case "Tokyo" -> checkAndLogDataDistinctions(tokyo);
            case "Paris" -> checkAndLogDataDistinctions(paris);
            case "London" -> checkAndLogDataDistinctions(london);
            default -> log.debug("Requested city's name {} does not have data to compare", cityName);
        }
    }

    private void checkAndLogDataDistinctions (City city) {
        if (!Objects.equals(currentWeatherResponse.location.name, city.getName())) {
            log.info("{} City's name: expected - {}, actual - {}", city.getName(), city.getName(), currentWeatherResponse.location.name);
        }
        if (!Objects.equals(currentWeatherResponse.location.country, city.getCountry())) {
            log.info("{} City's country: expected - {}, actual - {}", city.getName(), city.getCountry(), currentWeatherResponse.location.country);
        }
        if (!Objects.equals(currentWeatherResponse.location.lat, city.getLat())) {
            log.info("{} City's lat: expected - {}, actual - {}", city.getName(), city.getLat(), currentWeatherResponse.location.lat);
        }
        if (!Objects.equals(currentWeatherResponse.location.lon, city.getLon())) {
            log.info("{} City's lon: expected - {}, actual - {}", city.getName(), city.getLon(), currentWeatherResponse.location.lon);
        }
        if (!Objects.equals(currentWeatherResponse.current.temperature, city.getTemperature())) {
            log.info("{} City's temperature: expected - {}, actual - {}", city.getName(), city.getTemperature(), currentWeatherResponse.current.temperature);
        }
        if (!Objects.equals(currentWeatherResponse.current.wind_speed, city.getWind_speed())) {
            log.info("{} City's wind speed: expected - {}, actual - {}", city.getName(), city.getWind_speed(), currentWeatherResponse.current.wind_speed);
        }
        if (!Objects.equals(currentWeatherResponse.current.pressure, city.getPressure())) {
            log.info("{} City's pressure: expected - {}, actual - {}", city.getName(), city.getPressure(), currentWeatherResponse.current.pressure);
        }
    }

}
