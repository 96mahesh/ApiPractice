package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class LoggingDemo2 {

	 @Test
	public void testLogging() {

		given()
				.when().get("https://www.google.com/")
				.then().assertThat().statusCode(200)
				.log().cookies();
		
	
				
	}

	
}
