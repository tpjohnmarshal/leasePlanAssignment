package dsl.apioperations;

import dsl.configuration.ProtocolFactory;

public class ApiOperationsFactory {
    public static ApiOperationsProtocol getProtocol(){
        return (ApiOperationsProtocol) ProtocolFactory.getInstance(ApiOperationsProtocol.class, "apiOperations");
    }
}
