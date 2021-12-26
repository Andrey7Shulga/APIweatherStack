package weatherService;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiCore {

    private static final String key = "bb98b761a6a29f43187db0c088800fc4";

    public Response getWeatherForACity (String city, String endpoint) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecification requestSpecification = RequestSpec.baseRequestSpecJson();

        Map<String, String> query = new HashMap<>();
            query.put("access_key", key);
            query.put("query", city);

        return given()
                .spec(requestSpecification)
                .queryParams(query)
                .when().get(endpoint);

    }


}
