package day5;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXmlResponce {

	
	//aproch 1 with out returing the responce
	//@Test
	public void testXmlResponce() {
		
		given()
		
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
		.statusCode(200)
		.header("Content-Type","application/xml; charset=utf-8")
		.body("TravelerinformationResponse.page", equalTo("1"))
		.body("TravelerinformationResponse.per_page",  equalTo("10"))
		.body("TravelerinformationResponse.totalrecord",  equalTo("36966"))
		.body("TravelerinformationResponse.total_pages",  equalTo("3697"))
		.body("TravelerinformationResponse.travelers.Travelerinformation[0].id", equalTo("11133"))
		.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"))
		
		.log().all();
	}
	
	// Approch 2 
	//Returning the rasponce into variabul
	
	@Test
	public void testXmlResponceValidation() {
          
		Response res = given()
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");
		
		String pageno = res.xmlPath().get("TravelerinformationResponse.page").toString();
		System.out.println(pageno);
		Assert.assertEquals(pageno, "1");
		
		String name = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		System.out.println(name);
	    Assert.assertEquals(name, "Developer");
	    System.out.println(res);
	}
	
	
	@Test
	public void testXmlResponceBody() {
          
		Response res = given()
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1");

         XmlPath  xml = new XmlPath(res.asString());
//        int sizeofxml =   xml.get("TravelerinformationResponse.travelers.Travelerinformation.size()");
//        System.err.println(sizeofxml);
//        
//        for(int i=0;i<sizeofxml;i++) {
//        	String travalername = xml.get("TravelerinformationResponse.travelers.Travelerinformation["+i+"].name").toString();
//        	System.out.println(travalername);
//        }
        
        List<String> TravelerCount =   xml.getList("TravelerinformationResponse.travelers.Travelerinformation.");
        int count  = TravelerCount.size();
        System.out.println(count);
        Assert.assertEquals(count, 10);
        
        //verify tarvaler name present
        
        boolean status = false;
        List<String> Travelename =   xml.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
        for (String tname : Travelename) {
			System.out.println(tname);
			if(tname.equals("Developer"))
				status = true;
		}
        
        Assert.assertEquals(status, true);
			}
}
