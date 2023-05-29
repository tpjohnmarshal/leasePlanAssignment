package dsl.apioperations.usecases.search;

import dsl.apioperations.ApiOperationsFactory;
import dsl.apioperations.ApiOperationsProtocol;
import dsl.apioperations.entities.InputForApiOperations;
import dsl.apioperations.entities.StatusCodes;
import dsl.response.models.ResponseModel;
import org.junit.Assert;

public class SearchProduct {
    public static ResponseModel responseModel;
    transient InputForApiOperations inputForApiOperations;
    transient ApiOperationsProtocol apiOperations = ApiOperationsFactory.getProtocol();

    public void searchProduct(String productName){
        inputForApiOperations = InputForApiOperations.builder()
                .productName(productName)
                .build();
        responseModel = apiOperations.searchProduct(inputForApiOperations);
    }

    public void validateSuccessfulProductSearchStatus(){
        Assert.assertEquals("Product search not returned expected status code", StatusCodes.SUCCESS.getValue(),
                                                                                        responseModel.getStatusCode());
    }

    public void validateSearchProductList(){
        Assert.assertFalse("Product list returned empty array",
                                                        responseModel.getProductDetailsResponseList().isEmpty());
    }

    public void validateProductSearchNotFoundStatus(){
        Assert.assertEquals("Product search not returned expected status code",
                                                StatusCodes.NOT_FOUND.getValue(), responseModel.getStatusCode());
    }

    public void validateProductNotFoundError() {
        String expectedErrorMessage = "Not found";
        String actualErrorMessage = responseModel.getProductNotFoundResponse().getDetail().getMessage();
        Assert.assertEquals("Customer alerted with wrong error message", expectedErrorMessage,
                                                                                                actualErrorMessage);
    }
}
