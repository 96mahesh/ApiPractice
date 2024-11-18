package day2;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


public class DefWaysToCreatePostRequest {

	//Different ways to create postRequest
	//post request body using HashMap
	//post request body creation using org.json
	//post request body creation  using pojo class
	//post request using external json file data
	
	//post request body using HashMap
	
	//@Test(priority=1)
	public void postRequestUsingHashMap() {
		
		HashMap  map = new HashMap();
		map.put("name", "scott");
		map.put("Loaction","france");
		map.put("phone", "1234567891");
		
		String a[] = {"c","c++"};
		map.put("courses", a);
		
		given()
		.contentType("application/json")
		.body(map)
		.when()
		.post("http://localhost:3000/students")
		.then().statusCode(201)
		.body("name", equalTo("scott"))
		.body("Loaction",equalTo("france"))
		.body("phone",equalTo("1234567891"))
		.body("courses[0]", equalTo("c"))
		.body("courses[1]", equalTo("c++"))
		.header("Content-Type", "application/json")
		.log().all();
	}
	// 2 post request body creation using org.json
	//@Test(priority=1)
	public void postRequestUsingJsonLibrery() {
		
		JSONObject  js = new JSONObject();
		js.put("name", "scott");
		js.put("Loaction","france");
		js.put("phone", "1234567891");
		
		String a[] = {"c","c++"};
		js.put("courses", a);
		
		given()
		.contentType("application/json")
		.body(js.toString())
		.when()
		.post("http://localhost:3000/students")
		.then().statusCode(201)
		.body("name", equalTo("scott"))
		.body("Loaction",equalTo("france"))
		.body("phone",equalTo("1234567891"))
		.body("courses[0]", equalTo("c"))
		.body("courses[1]", equalTo("c++"))
		.header("Content-Type", "application/json")
		.log().all();
	}
	
	// 3 post request body creation using pojo class  pojo means plain old java object
	//	@Test(priority=1)
		public void postRequestUsingPojoClass() {
			
			Pojo_PostRequest data = new Pojo_PostRequest();
			data.setName("scott");
			data.setLocation("france");
			data.setPhone("1234567891");
			String a[] = {"c","c++"};
			data.setCourse(a);
			given()
			.contentType("application/json")
			.body(data)
			
			.when()
			.post("http://localhost:3000/students")
			
			.then().statusCode(201)
			.body("name", equalTo("scott"))
			.body("location",equalTo("france"))
			.body("phone",equalTo("1234567891"))
			.body("course[0]", equalTo("c"))
			.body("course[1]", equalTo("c++"))
			.header("Content-Type", "application/json")
			.log().all();
		}
		
		// 4 post request using external json file data
		
		@Test(priority=1)
		public void postRequestUsingJsonPath() throws FileNotFoundException {
			
             File f = new File(".//body.json");
             
             FileReader fr = new FileReader(f);

             JSONTokener jt = new JSONTokener(fr);
             
             JSONObject  data = new JSONObject(jt);
			given()
			.contentType("application/json")
			.body(data.toString())
			
			.when()
			.post("http://localhost:3000/students")
			
			.then().statusCode(201)
			.body("name", equalTo("scott"))
			.body("location",equalTo("france"))
			.body("phone",equalTo("1234567891"))
			.body("course[0]", equalTo("c"))
			.body("course[1]", equalTo("c++"))
			.header("Content-Type", "application/json")
			.log().all();
		}
	@Test(priority=2)
	public void testDelet() {
		
		given()
		
		.when()
		.delete("http://localhost:3000/students/4")
		.then().statusCode(404);
	}
}
