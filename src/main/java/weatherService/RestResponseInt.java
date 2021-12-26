package weatherService;

import io.restassured.response.Response;

public interface RestResponseInt<T> {
    T getBody();
    Response getResponse();
    String getStatusDescription();
    boolean isSuccessful();
    String getContent();
    Exception getException();
    int getStatusCode();
}
