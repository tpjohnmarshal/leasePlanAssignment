package dsl.apioperations.usecases.search;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SearchProductFlow {
    private final transient SearchProduct searchProduct = new SearchProduct();
/*
    @Given("the authorized user is searching for the list of issues")
    public void theAuthenticatedUserSearchAnIssue(){
        searchProduct.searchIssue();
    }



    @Given("the unauthorized user is try to access the gitlab API")
    public void theUnauthorizedUserCheck(){
        searchProduct.unauthorizedAccess();
    }

    @Then("the user is not allowed to access the API")
    public void unauthorizedStatusCheck() {
        searchProduct.unauthorizedStatusCode();
    }

    @And("the user can see the error message {string} in the response")
    public void unauthorizedMessage(String errorMessage){
        searchProduct.unauthorizedErrorMessageCheck(errorMessage);
    }

    @Given("the authorized user is searching for the list of issues with specific {string}")
    public void searchIssueWithSpecificState(String issueState){
        searchProduct.searchIssueWithSpecificState(issueState);
    }

    @And("the user can able to see the list of {string} issues")
    public void checkIssueState(String issueState){
        searchProduct.verifyIssueState(issueState);
    }*/


    @Given("the customer search for {string}")
    public void theCustomerSearchFor(String productName) {
        searchProduct.searchProduct(productName);
    }

    @Then("the search is successful")
    public void searchIsSuccessful() {
        searchProduct.validateSuccessfulProductSearchStatus();
    }

    @And("a list of available options are shown to the customer")
    public void aListOfAvailableOptionsAreShownToTheCustomer() {
        searchProduct.validateSearchProductList();
    }

    @Then("the search is unsuccessful")
    public void theSearchIsUnsuccessful() {
        searchProduct.validateProductSearchNotFoundStatus();
    }

    @And("the customer alerted about the unavailability of the product")
    public void theCustomerAlertedAboutTheUnavailabilityOfTheProduct() {
        searchProduct.validateProductNotFoundError();
    }
}
