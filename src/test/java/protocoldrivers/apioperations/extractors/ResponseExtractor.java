package protocoldrivers.apioperations.extractors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dsl.response.models.ProductDetailsResponse;
import dsl.response.models.ProductNotFoundResponse;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class ResponseExtractor {

    ObjectMapper objectMapper = new ObjectMapper();
    public Integer extractStatusCode(Response response) {
        return response.getStatusCode();
    }

    public ProductNotFoundResponse extractErrorDetails(Response response) {
        try {
            return objectMapper.readValue(response.getBody().asString(), ProductNotFoundResponse.class);
        } catch (NullPointerException | JsonProcessingException e) {
            return null;
        }
    }

    public List<ProductDetailsResponse> extractProductDetails(Response response) {
        try {
            return Arrays.asList(objectMapper.readValue(response.getBody().asString(), ProductDetailsResponse[].class));
        } catch (NullPointerException | JsonProcessingException e) {
            return null;
        }
    }
}
