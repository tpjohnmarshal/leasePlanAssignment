package protocoldrivers.httpclient;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.java.Log;

import static net.serenitybdd.rest.SerenityRest.rest;

@Log
public class HttpClient {
    private final transient RequestSpecification request;


    public HttpClient(String uri) {
        request = initRequest(uri);
    }

    public RequestSpecification initRequest(String uri) {
        return rest().baseUri(uri).given();
    }


    public Response call(Method method, String endpoint) {
        log.info(String.format("Calling: %s %s", method, endpoint));
        Response response = executeCall(method, endpoint);
        return handleResponse(response);
    }

    private Response executeCall(Method method, String endpoint) {
        switch (method) {
            case GET:
                return handleResponse(request.get(endpoint));
            case POST:
                return handleResponse(request.post(endpoint));
            case PATCH:
                return handleResponse(request.patch(endpoint));
            case PUT:
                return handleResponse(request.put(endpoint));
            case DELETE:
                return handleResponse(request.delete(endpoint));
            default:
                throw new IllegalArgumentException("You tried to call a non-existing HTTP method");
        }
    }

    public Response handleResponse(Response response) {
        log.info(
                String.format("Response with status %d and body %s", response.statusCode(), response.body().prettyPrint())
        );
        return response;
    }
}
