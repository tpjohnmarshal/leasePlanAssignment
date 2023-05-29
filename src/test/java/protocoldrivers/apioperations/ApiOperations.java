package protocoldrivers.apioperations;

import dsl.apioperations.ApiOperationsProtocol;
import dsl.apioperations.entities.InputForApiOperations;
import dsl.configuration.ConfigurationFactory;
import dsl.response.models.ResponseModel;
import io.restassured.http.Method;
import io.restassured.response.Response;
import protocoldrivers.apioperations.modelers.ResponseModeler;
import protocoldrivers.httpclient.HttpClient;

public class ApiOperations implements ApiOperationsProtocol {
    transient ResponseModeler responseModeler;
    transient HttpClient client;
    static String searchEndpoint = "/api/v1/search/demo";

    public ApiOperations(ConfigurationFactory.AutomationURLs automationURLs){
        client = new HttpClient(automationURLs.getGitLabURL());
        responseModeler = new ResponseModeler();
    }

    @Override
    public ResponseModel searchProduct(InputForApiOperations inputForApiOperations) {
        Response response = prepareAndCallSearchProduct(inputForApiOperations);
        return responseModeler.prepareResponseModelForSearchProduct(response);
    }

    private Response prepareAndCallSearchProduct(InputForApiOperations inputForApiOperations) {
        return client.call(Method.GET, createSearchProductEndpoint(inputForApiOperations));
    }

    private String createSearchProductEndpoint(InputForApiOperations inputForApiOperations) {
        return String.format("%s/%s", searchEndpoint, inputForApiOperations.getProductName());
    }
}
