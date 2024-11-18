package day4;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ParsingJsonResponceData {
         
	//@Test
	    public  void  testJsonResponce() {
	    	
		given()
		.contentType("contentType.JSON")
		.when()
		.get("http://localhost:3000/store")
		.then().assertThat().statusCode(200)
		.header("Content-Type", "application/json")
		.body("book[0].title",equalTo ("Sayings of the Century"))
		.body("book[1].price",equalTo ("100"))
		.log().all();
		
	    }
	    
	//  @Test
	    public void testJsonValidation() {
	    Response res = 	given()
			.contentType(ContentType.JSON)
			.when()
			.get("http://localhost:3000/store");
	    
//	    Assert.assertEquals(res.getStatusCode(), 200);
//	    Assert.assertEquals(res.header("Content-Type"), "application/json");
//	    
//	    String bookTitleName = res.jsonPath().get("book[3].title");
//	    Assert.assertEquals(bookTitleName, "The Lord of the Rings");
	    
	    boolean status = false;
	    JSONObject jo = new JSONObject(res.asString());
	   for(int i=0;i<jo.getJSONArray("book").length();i++) {
		  String bookTitle =  jo.getJSONArray("book").getJSONObject(i).get("title").toString();
		 if(bookTitle.equals("The Lord of the Rings")) {
			 status = true;
		 }
	   }
	   Assert.assertTrue(status, "Test case will pass");
	    
	  double sum = 0;
	  for(int i=0;i<jo.getJSONArray("book").length();i++) {
		  String bookPrice =  jo.getJSONArray("book").getJSONObject(i).get("price").toString();
		sum = sum+Double.parseDouble(bookPrice);
	   }
	  System.out.println(sum);
	  Assert.assertEquals(sum,526.0);
	    
}

	   @Test
	    public void testJsonBodyValidation() {
	    String res = 	given()
			.contentType(ContentType.JSON)
			.when()
			.get("http://localhost:3000/store")
			
			.then().statusCode(200).extract().response().asString();
	    
	    System.out.println(res);
	    
	    JsonPath j = new JsonPath(res);
	   String bookTitle =  j.get("book[2].title");
	   Assert.assertEquals(bookTitle, "Moby Dick");
	    
	 int bookCount = j.get("book.size()");
	 System.out.println("Count of all books :"+bookCount);
	 
	 boolean status = false;
	 for(int i=0;i<bookCount;i++) {
		String bookTitlelist =  j.get("book["+i+"].title").toString();
	   if(bookTitlelist.equals("The Lord of the Rings")) {
		   status = true;
	   }
	 }
	   Assert.assertTrue(status, "Test case will pass");
	    
	    double sum = 0;
	    for(int i=0;i<bookCount;i++) {
			String bookPricelist =  j.get("book["+i+"].price").toString();
		  sum+=Double.parseDouble(bookPricelist);
		 }
		   Assert.assertEquals(sum,526.0);
	    
	    }  
}
