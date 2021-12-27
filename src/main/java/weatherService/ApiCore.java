package weatherService;

import dto.response.CurrentWeatherResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiCore {

    private static final Logger log = LoggerFactory.getLogger(ApiCore.class);
    private static final String key = "bb98b761a6a29f43187db0c088800fc4";

    public Response getWeatherForACity (String city, String endpoint) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecification requestSpecification = RequestSpec.baseRequestSpecJson();

        Map<String, String> query = new HashMap<>();
            query.put("access_key", key);
            query.put("query", city);

        log.debug("Access Key: {}", key);
        log.debug("City: {}", city);
        log.debug("Endpoint: {}", endpoint);

        return given()
                .spec(requestSpecification)
                .queryParams(query)
                .when().get(endpoint);

    }

    public RestResponseInt<CurrentWeatherResponse> getWeatherForACityAsObject (String city, String endpoint) {
        Response response =  getWeatherForACity(city, endpoint);
        return new RestResponse<>(CurrentWeatherResponse.class, response);
    }




}
