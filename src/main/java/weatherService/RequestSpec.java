package weatherService;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    private final static String BASE_URI = "http://api.weatherstack.com/";
    private final static String APP_HEADER_JSON = "application/json";

    public static RequestSpecification baseRequestSpecJson() {

        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URI)
                .addHeader("accept", APP_HEADER_JSON)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

}
