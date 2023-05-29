package dsl.apioperations;

import dsl.apioperations.entities.InputForApiOperations;
import dsl.response.models.ResponseModel;

public interface ApiOperationsProtocol {
    ResponseModel searchProduct(InputForApiOperations inputForApiOperations);
}
