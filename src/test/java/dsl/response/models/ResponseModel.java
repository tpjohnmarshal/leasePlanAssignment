package dsl.response.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
@Builder
@Getter
public class ResponseModel {
    Integer statusCode;
    List<ProductDetailsResponse> productDetailsResponseList;
    ProductNotFoundResponse productNotFoundResponse;
}
