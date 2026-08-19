package api;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import pojo.Booking;

public class BookingApi extends BaseApi {

	public Response createBooking(Booking booking) {

		return given().spec(requestSpec).body(booking).when().post("/booking");
	}

	public Response getBooking(int bookingId) {

		System.out.println("Getting booking ID: " + bookingId);

		return given().spec(requestSpec).log().all().when().get("/booking/" + bookingId).then().log().all().extract()
				.response();
	}

	public Response updateBooking(int bookingId, Booking booking, String token) {

		return given().spec(requestSpec).header("Cookie", "token="+token).body(booking).when().put("/booking/" + bookingId);
	}
	
	public Response deleteBooking(int bookingId, String token) {

		return given().spec(requestSpec).header("Cookie", "token="+token).when().delete("/booking/" + bookingId);
	}
}
