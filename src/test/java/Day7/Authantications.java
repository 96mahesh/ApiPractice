package Day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Authantications {

	@Test(priority=1)
	public void testBasicAuthentication() {
		
		given()
		        .auth().basic("postman", "password")
		.when()
		        .get("https://postman-echo.com/basic-auth")
		.then()
		        .statusCode(200)
		        .body("authenticated",equalTo(true)).log().all();
		
	}
	
	@Test(priority=2)
	public void testDigestAuthentication() {
		
		given()
		        .auth().digest("postman", "password")
		.when()
		        .get("https://postman-echo.com/basic-auth")
		.then()
		        .statusCode(200)
		        .body("authenticated",equalTo(true)).log().all();
		
	}
	
	@Test(priority=2)
	public void testPreemptiveAuthentication() {
		
		given()
		        .auth().preemptive("postman", "password")
		.when()
		        .get("https://postman-echo.com/basic-auth")
		.then()
		        .statusCode(200)
		        .body("authenticated",equalTo(true)).log().all();
		
	}
	

	

}
