package api;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class AuthApi extends BaseApi{
	
	public Response generateToken(String username, String password) {
		
		String requestBody = """
                {
                    "username": "%s",
                    "password": "%s"
                }
                """.formatted(username, password);
		
		return given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/auth");
		
	}

}
