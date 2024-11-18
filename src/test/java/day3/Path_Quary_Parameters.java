package day3;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
public class Path_Quary_Parameters {
	
	//path Nd Quary Parameters 
//https://reqres.in/api/users?page=2
	@Test
	public  void  perametersPassing() {
		
		given()
		.pathParam("mypath", "users")
		.queryParam("page", 2)
		.queryParam("id", 5)
		.when() 
		.get("https://reqres.in/api/{mypath}")
		.then()
		.statusCode(200)
		.log().all();
	}
}
