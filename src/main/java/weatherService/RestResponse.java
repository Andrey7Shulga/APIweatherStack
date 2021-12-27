package weatherService;

import io.restassured.response.Response;

/**
 * A wrapper class to operate with various types of Response object
 * @param <T>
 */
public class RestResponse<T> implements RestResponseInt<T> {
    private T data;
    private final Response response;
    private Exception e;

    public RestResponse(Class<T> t, Response response) {
        this.response = response;
        try{
            this.data = t.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            throw new RuntimeException("The Response POJO must have a default constructor.");
        }
    }

    public String getContent() {
        return response.getBody().asString();
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public boolean isSuccessful() {
        int code = response.getStatusCode();
        return code == 200 || code == 201 || code == 202 || code == 203 || code == 204 || code == 205;
    }

    public String getStatusDescription() {
        return response.getStatusLine();
    }

    public Response getResponse() {
        return response;
    }

    public T getBody() {
        try {
            data = (T) response.getBody().as(data.getClass());
        }catch (Exception e) {
            this.e=e;
        }
        return data;
    }

    public Exception getException() {
        return e;
    }
}
