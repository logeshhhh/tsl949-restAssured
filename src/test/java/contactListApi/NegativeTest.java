package contactListApi;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;



public class NegativeTest 
{
	String id;
     
	@Test(enabled=true,description="getting Contact which does not exist") 
	  public void getcontact()
	  {  
		  System.out.println("Getting contact");
		 	        given()
					.when()
					.get("http://3.13.86.142:3000/contacts/5")
					.then()
					.log()    //print
					.body()
					.statusCode(404);
					
	  }
	@SuppressWarnings("unchecked")
	@Test(enabled=false,description="Add Contact with missing email id")  //made it false to not run this step
	  public void addContactwithmissingemailid()
	  {
		System.out.println("creating contact");
		
		  JSONObject data=new JSONObject();
		  JSONObject emp=new JSONObject();
		  JSONObject loc=new JSONObject();
		  
		  emp.put("jobTitle", "QA");
		  emp.put("company", "LTI");
		  
		  loc.put("city", "Chennai");
		  loc.put("country", "India");
		  
		  data.put("firstName", "Logesh");
		  data.put("lastName", "M");
		 // data.put("email", "logesh123@gmai.com");
		  data.put("location", loc);
		  data.put("employer", emp);
		  
		  		id=	given()
					.header("Content-Type","application/json")
					.body(data.toJSONString())
					.when()
					.post("http://3.13.86.142:3000/contacts")
					.then()
					.log()    //print
					.body()
					.statusCode(400)
					.extract().path("err");
		  			
		  			Assert.assertEquals("Contacts validation failed: email: Email is required","err");
	  }
	@SuppressWarnings("unchecked")
	@Test(enabled=true,description="Add Contact with more character")  //made it false to not run this step
	  public void addContactwithmoresize()
	  {
		System.out.println("creating contact");
		
		  JSONObject data=new JSONObject();
		  JSONObject emp=new JSONObject();
		  JSONObject loc=new JSONObject();
		  
		  emp.put("jobTitle", "QA");
		  emp.put("company", "LTI");
		  
		  loc.put("city", "Chennai");
		  loc.put("country", "India");
		  
		  data.put("firstName", "jajajajjajajajajajajajajajajajajajajajajajajajajajajajajajajajajajajajaj");
		  data.put("lastName", "M");
		  data.put("email", "logesh123@gmai.com");
		  data.put("location", loc);
		  data.put("employer", emp);
		  
		  String err=given()
					.header("Content-Type","application/json")
					.body(data.toJSONString())
					.when()
					.post("http://3.13.86.142:3000/contacts")
					.then()
					.log()    //print
					.body()
					.statusCode(400)
					.extract().path("err");
		  			
		  			Assert.assertEquals(err.contains("is longer than the maximum allowed length"),true);
	  }
	@SuppressWarnings("unchecked")
	@Test(enabled=true,description="Add Contact with invalid characters")  //made it false to not run this step
	  public void addContactwithinvalidchar()
	  {
		System.out.println("creating contact");
		
		  JSONObject data=new JSONObject();
		  JSONObject emp=new JSONObject();
		  JSONObject loc=new JSONObject();
		  
		  emp.put("jobTitle", "QA");
		  emp.put("company", "LTI");
		  
		  loc.put("city", "Chennai");
		  loc.put("country", "India");
		  
		  data.put("firstName", "Logesh123");
		  data.put("lastName", "M");
		  data.put("email", "logesh123@gmai.com");
		  data.put("location", loc);
		  data.put("employer", emp);
		  
		  String err=given()
					.header("Content-Type","application/json")
					.body(data.toJSONString())
					.when()
					.post("http://3.13.86.142:3000/contacts")
					.then()
					.log()    //print
					.body()
					.statusCode(400)
					.extract().path("err");
		  			
		  			Assert.assertEquals(err.contains("Validator failed for path `firstName`"),true);
	  }
	
	@SuppressWarnings("unchecked")
	@Test(enabled=true,description="Add Contact with improper format")  //made it false to not run this step
	  public void addContactwithwrongparameter()
	  {
		System.out.println("creating contact");
		
		  JSONObject data=new JSONObject();
		  JSONObject emp=new JSONObject();
		  JSONObject loc=new JSONObject();
		  
		  emp.put("jobTitle", "QA");
		  emp.put("company", "LTI");
		  
		  loc.put("city", "Chennai");
		  loc.put("country", "India");
		  
		  data.put("firstName", "Logesh");
		  data.put("lastName", "M");
		  data.put("email", "logesh123");
		  data.put("location", loc);
		  data.put("employer", emp);
		  
		  String err=given()
					.header("Content-Type","application/json")
					.body(data.toJSONString())
					.when()
					.post("http://3.13.86.142:3000/contacts")
					.then()
					.log()    //print
					.body()
					.statusCode(400)
					.extract().path("err");
		  			
		  			Assert.assertEquals(err.contains("Validator failed for path `email`"),true);
	  }
	
	
  
}
