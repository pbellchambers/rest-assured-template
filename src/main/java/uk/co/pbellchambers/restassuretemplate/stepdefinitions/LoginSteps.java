package uk.co.pbellchambers.restassuretemplate.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import uk.co.pbellchambers.restassuretemplate.requests.LoginRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginSteps {

    private RequestSpecification request;
    private Response response;

    @Given("I have login details {string} {string}")
    public void iHaveLoginDetails(String email, String password) {
        LoginRequest loginBody = new LoginRequest(email, password);

        request = given()
                .contentType("application/json")
                .body(loginBody);
    }

    @When("I send a login request")
    public void iSendALoginRequest() {
        response = request.when().post("https://reqres.in/api/login");
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        response.then().
                statusCode(200).
                body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Then("I should not be logged in")
    public void iShouldNotBeLoggedIn() {
        response.then().
                statusCode(400).
                body("error", equalTo("Missing password"));
    }
}
