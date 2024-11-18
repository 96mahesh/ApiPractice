package day3;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Map;
import java.util.Set;

public class CoockesDemo {

	// @Test
	public void testCoockes() {

		given()

				.when().get("https://www.google.com/").then().assertThat().statusCode(200)
				.cookie("AEC", "Ae3NU9PYpuovvHbeVZHesCZr8bo7uQM78YkvRcSyjeQrCgsglfsX5Q6-jQ").log().all();
	}

	@Test
	public void getCoockesInfo() {

		Response res = given()

				.when().get("https://www.google.com/");
//                String coockie_value =  res.getCookie("AEC");
//                System.out.println("CoockeValue is :"+coockie_value);
		
		Map<String , String> responce = res.getCookies();
		//System.out.println(responce.keySet());
		Set<String> coocke_res =responce.keySet();
		for (String k : coocke_res) {
			String cookie_value = res.getCookie(k);
			System.out.println(k+"   "+cookie_value);
		}
	}
}
