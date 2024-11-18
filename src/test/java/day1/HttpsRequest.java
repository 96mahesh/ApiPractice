package day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import org.testng.annotations.Test;
public class HttpsRequest {

	int id;
	@Test
	public void getUsers(){
		
		given()
		
		.when()
		 .get("https://reqres.in/api/users")
		.then().statusCode(200).log().all();
		//https://reqres.in/api/users/
		//.body("page", equalTo(2)).log().all()
		//https://reqres.in/api/users
		//https://reqres.in/api/users?page=2
		//https://reqres.in/api/users?page=1
	}
	
	@Test(priority=1)
public void getSingleUsers(){
		
		given()
		
		.when()
		 .get("https://reqres.in/api/users?page=2")
		.then().statusCode(200).body("page", equalTo(2)).log().all();
		//https://reqres.in/api/users/
		//
		//https://reqres.in/api/users
		//https://reqres.in/api/users?page=2
		//https://reqres.in/api/users?page=1
	}
	
	@Test(priority=2)
	public void createuser() {
		HashMap<String,String> data = new HashMap<>();
		data.put("name", "Mahesh");
		data.put("job", "AutomationEngineer");
		
		id = given()
		.contentType("application/json").body(data)
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		System.out.println(id);
	//	.then().statusCode(201).log().all();
	}
	
	@Test(priority=3,dependsOnMethods= {"createuser"})
	
	public void updateUser() {
		
		HashMap<String,String> data = new HashMap<>();
		data.put("name", "Pawan");
		data.put("job", "MobileAutomation");
		
		 given()
		.contentType("application/json").body(data)
		.when()
		.put("https://reqres.in/api/users/"+id)
		.then().statusCode(200).log().all();
	}
	
	@Test(priority=4)
	public void deleteUser() {
		
		given()
		
		.when()
		.delete("https://reqres.in/api/users/"+id)
		.then().statusCode(204).log().all();
	}
}
