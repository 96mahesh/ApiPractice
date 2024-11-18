package day6;

import org.testng.annotations.Test;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;

//jan 30th json scheema
//xml schemma jan 27

public class Json_Scheema_Validations {

	@Test
	public void jsonScheemaValidations() {
		
		given()
		
		.when()
		.get("http://localhost:3000/store")
		.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("store.json"));
		//.log().body();
	}
}
