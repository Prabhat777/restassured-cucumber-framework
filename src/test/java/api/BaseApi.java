package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

	protected RequestSpecification requestSpec;

	public BaseApi() {

		requestSpec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com")
				.setContentType("application/json").build();
	}

}
