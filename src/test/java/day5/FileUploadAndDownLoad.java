package day5;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownLoad {

	
	@Test(priority=1)
	public void singleFileUpload() {
		
		File Myfile = new File("C:\\Users\\Admin\\Downloads\\Student.json");
		given()
	.multiPart("file",Myfile)
	.contentType("multipart/form-data")
		.when()
		.post("http://localhost:8080/uploadFile")
		
		.then().statusCode(200)
		.body("fileName",equalTo("Student.json"))
		.body("size", equalTo(607)).log().all();
			
	}
	
	//@Test
	public void MultipleFileUpload() {
		
		File Myfile1 = new File("C:\\Users\\Admin\\Downloads\\Student.json");
		File Myfile2 = new File("C:\\Users\\Admin\\Downloads\\pom.txt");
		
		//File filearr[] = {Myfile1,Myfile2};
		given()
	.multiPart("files",Myfile1)
	.multiPart("files",Myfile2)
	.contentType("multipart/form-data")
		.when()
		.post("http://localhost:8080/uploadMultipleFiles")
		
		.then().statusCode(200)
		.body("[0].fileName", equalTo("Student.json"))
		.body("[1].fileName", equalTo("pom.txt"))
		.body("[0].size", equalTo(607))
		.body("[1].size", equalTo(19738))
		.body("[0].fileType", equalTo("application/octet-stream"))
		.body("[1].fileType", equalTo("application/octet-stream"))
		.log().all();
			
	}
	
	
	@Test(priority=2)
	public void fileDownLoad() {
		given()
		.contentType("application/json")
			.when()
			.get("http://localhost:8080/downloadFile/Student.json")
			.then().statusCode(200).log().body();
	}
	
}
