package protocoldrivers.apioperations.modelers;

import dsl.response.models.ResponseModel;
import io.restassured.response.Response;
import protocoldrivers.apioperations.extractors.ResponseExtractor;

public class ResponseModeler {
    transient ResponseExtractor responseExtractor = new ResponseExtractor();

    public ResponseModel prepareResponseModelForSearchProduct(Response response){
        return ResponseModel
                .builder()
                .statusCode(responseExtractor.extractStatusCode(response))
                .productDetailsResponseList(responseExtractor.extractProductDetails(response))
                .productNotFoundResponse(responseExtractor.extractErrorDetails(response))
                .build();
    }
}
