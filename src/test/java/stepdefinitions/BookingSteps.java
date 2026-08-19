package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import api.AuthApi;

public class BookingSteps {

	private Response response;
	private String token;
	private AuthApi authApi = new AuthApi();

	@Given("the Restful Booker API is available")
	public void restFulBookerApiIsAvailable() {
		System.out.println("Restful Booker API is ready");
	}

	@When("I generate an authentication token")
	public void generateAuthenticationToken() {

		response = authApi.generateToken("admin", "password123");

		response.prettyPrint();

		token = response.jsonPath().getString("token");

		System.out.println("Generated Token: " + token);

	}

	@Then("the response status code should be {int}")
	public void verifyStatusCode(int expectedStatusCode) {
		response.then().statusCode(expectedStatusCode);

	}

	@Then("the authentication token should be generated")
	public void verifyAuthenticationToken() {

		assert token != null;
		assert !token.isEmpty();

		System.out.println("Authentication token generated successfully");

	}
}
