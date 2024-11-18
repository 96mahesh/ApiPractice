package day4;

import org.testng.annotations.Test;
import files.Playload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	JsonPath js;
	int bookcount;
	@Test(priority=1)
	public void testJsonValidations() {
		 js = new JsonPath(Playload.stores());
	}
	@Test(priority=2)
	public void getBookSize() {
	 bookcount = 	js.getInt("book.size()");
	System.out.println(bookcount);
	}
	
	@Test(priority=3)
	public void getFristTitle() {
	String titleFrist = js.get("book[0].title");
	System.out.println(titleFrist);
	}
	
	@Test(priority=4)
	public void getBookAuthor() {
	String authorFrist = js.get("book[0].author");
	System.out.println(authorFrist);
	}
	
	@Test(priority=5)
	public void getSayingsoftheCenturyprice() {
	for(int i=0; i<bookcount; i++) {
		String booktitles = js.get("book["+i+"].title");
		if(booktitles.equals("Sayings of the Century")) {
			String bookPrices = js.get("book["+i+"].price").toString();
			System.out.println("Book price :"+bookPrices);
		}
	}
	}
	
	
	@Test(priority=6)
	public void getAllBookTitles() {
	for(int i=0; i<bookcount; i++) {
		String booktitles = js.get("book["+i+"].title");
			System.out.println("Book Titles :"+booktitles);
		}
	}
	
	
	@Test(priority=7)
	public void getSumOfPrices() {
	int sum = 0;
	for(int i=0; i<bookcount; i++) {
		float bookPrices = js.get("book["+i+"].price");
		System.out.println(bookPrices);
		 sum+=bookPrices;
		
		}
	System.out.println(sum);
	}
	
	
	}

