package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import api.BookingApi;
import pojo.Booking;
import pojo.BookingDates;

import api.AuthApi;

public class BookingSteps {

	private Response response;
	private String token;
	private int bookingId;
	

	private AuthApi authApi = new AuthApi();
	private BookingApi bookingApi = new BookingApi();

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

	@When("I create a new booking")
	public void createNewBooking() {

		BookingDates bookingDates = new BookingDates("2026-08-20", "2026-08-25");

		Booking booking = new Booking("Prabhat", "Singh", 111, true, bookingDates, "Lunch");

		response = bookingApi.createBooking(booking);

		response.prettyPrint();
	}

	@Then("the booking should be created successfully")
	public void verifyBookingCreatedSuccessfully() {

		bookingId = response.jsonPath().getInt("bookingid");

		System.out.println("Created Booking id is " + bookingId);
	}
	
	@When("I get the newly created booking")
	public void getTheNewlyCreatedBooking() {
		
		response = bookingApi.getBooking(bookingId);
		
		response.prettyPrint();
	}
	
	@Then("the booking details should be displayed")
	public void verifyBookingDetails() {
		
		String firstName = response.jsonPath().getString("firstname");
		String lastName =response.jsonPath().getString("lastname");
		
		assert(firstName).equals("Prabhat");
		assert(lastName).equals("Singh");
		
	}
	
	@When("I update the newly created booking")
	public void updateTheNewlyCreatedBooking() {
		
		BookingDates updatedDates = new BookingDates("2026-08-20", "2026-08-25");
		Booking updatedBooking = new Booking("James", "Brown", 222, true, updatedDates, "Dinner");
		
		response = bookingApi.updateBooking(bookingId, updatedBooking, token);
		
		response.prettyPrint();		
	}
	
	@Then("the booking should be updated successfully")
	public void verifyBookingUpdatedSuccessfully() {
		
		String firstName = response.jsonPath().getString("firstname");
		String lastName =response.jsonPath().getString("lastname");
		String additionalNeeds = response.jsonPath().getString("additionalneeds");
		
		assert(firstName).equals("James");
		assert(lastName).equals("Brown");
		assert(additionalNeeds).equals("Dinner");
		
	}
	
	@When("I delete the newly created booking")
	public void deleteTheNewlyCreatedBooking() {
		
		response = bookingApi.deleteBooking(bookingId, token);
				
	}
	
	@Then("the booking should be deleted successfully")
	public void verifyBookingDeletedSuccessfully() {
		
		String responseBody = response.asString();
		
		System.out.println("Delete Response: " + responseBody);

	    assert responseBody.equals("Created");
				
	}
	
	
}
