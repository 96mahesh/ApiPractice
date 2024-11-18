package day3;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class HeadredDemo {

	@Test
	public void testHeard() {
		given()

				.when().get("https://www.google.com/").then().header("Content-Type", "text/html; charset=ISO-8859-1")
				.header("Content-Encoding", "gzip").log().headers();
	}

	//@Test
	public void getHeardInfo() {
		Response res = given()

				.when()

				.get("https://www.google.com/");
//	    String actheader = res.getHeader("Content-Type");
//		System.out.println(actheader);

		Headers Myheaders = res.getHeaders();
		for (Header hd : Myheaders) {
			System.out.println(hd.getName()+"            "+hd.getValue());
		}
	}
}
