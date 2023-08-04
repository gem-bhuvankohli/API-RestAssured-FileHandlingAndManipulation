package stepdefinitions;

import implementation.RestAssuredBookerApiImplementation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RestAssuredBookerStepDefinitions {

    @Given("^post user data using POST request$")
    public static void post(){
        RestAssuredBookerApiImplementation.post();
    }
    @Then("^update user data with authentication using PATCH request$")
    public static void patch(){
        RestAssuredBookerApiImplementation.patch();
    }
    @Then("^update user data with authentication using PUT request$")
    public static void put(){
        RestAssuredBookerApiImplementation.put();
    }
    @When("^deleting user data with authentication using DELETE request$")
    public static void delete(){
        RestAssuredBookerApiImplementation.delete();
    }
    @Then("^validate whether data deleted or not using GET request$")
    public static void validateDelete(){
        RestAssuredBookerApiImplementation.validateDelete();
    }

}