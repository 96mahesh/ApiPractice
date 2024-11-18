package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



//pojo........serialization.........json / object .........deserialization.......pojo
public class Serialization_Deserialization {

	
	//serialization 
	
	@Test
	public void convertPojoToJson() throws JsonProcessingException {
		
		student data = new student();
		data.setName("scott");
		data.setLocation("france");
		data.setPhone("1234567891");
		String a[] = {"c","c++"};
		data.setCourse(a);
		
		//convert java object to json object
		
		ObjectMapper objmap = new ObjectMapper();
		String jsondata = objmap.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		System.out.println(jsondata);
		
		
	}
	
	//Deserilization
	@Test
	public void convertPJsonToojo() throws JsonProcessingException {
		
		String jsondata = "{\r\n"
				+ "  \"name\" : \"scott\",\r\n"
				+ "  \"location\" : \"france\",\r\n"
				+ "  \"phone\" : \"1234567891\",\r\n"
				+ "  \"course\" : [ \"c\", \"c++\" ]\r\n"
				+ "}";
		
		
		ObjectMapper objmap = new ObjectMapper();
		student stu = objmap.readValue(jsondata, student.class);
		System.out.println("name of the student  : " +stu.getName());
	    System.out.println("name Of Location :"+stu.getLocation());
		System.out.println("stu Phone Number :"+stu.getPhone());
		String a [] = stu.getCourse();
		System.out.println("Frist course Of Student :"+a[0]);
		System.out.println("secound course Of Student :"+a[1]);
		
	}
}
