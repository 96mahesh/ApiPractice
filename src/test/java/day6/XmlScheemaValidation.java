package day6;

import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers;
import static io.restassured.RestAssured.*;

public class XmlScheemaValidation {

	@Test
	public void xmlScheemaValidation() {
		
		given()
		
		.when().get("http://restapi.adequateshop.com/api/Traveler")
		
		.then()
		.assertThat().body(RestAssuredMatchers .matchesXsdInClasspath("travelar.xsd")).log().body();
	}
}
