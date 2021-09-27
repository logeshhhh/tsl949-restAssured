package contactListApi;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PositiveTests 
{
  String id;
  @Test(enabled=false,description="getting all contact") //made it false to not run this step
  public void gettingAllContact()
  {
	  			given()
				.when()
				.get("http://3.13.86.142:3000/contacts")
				.then()
				.log()    //print
				.body()
				.statusCode(200);
  }
  @SuppressWarnings("unchecked")
@Test(enabled=true,description="Add Specific Contact")  //made it false to not run this step
  public void addContact()
  {
	  JSONObject data=new JSONObject();
	  JSONObject emp=new JSONObject();
	  JSONObject loc=new JSONObject();
	  
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  
	  loc.put("city", "Chennai");
	  loc.put("country", "India");
	  
	  data.put("firstName", "Logesh");
	  data.put("lastName", "M");
	  data.put("email", "logesh123@gmai.com");
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
				.statusCode(200)
				.extract().path("_id");
	  			System.out.println("Id is "+id);
  }
  @Test(enabled=true,dependsOnMethods="addContact",description="getting Specific Contact") //made it false to not run this step
  public void getcontact()
  {  
	 	        given()
				.when()
				.get("http://3.13.86.142:3000/contacts/"+id)
				.then()
				.log()    //print
				.body()
				.statusCode(200);
				
  }
  @SuppressWarnings("unchecked")
  @Test(enabled=true,dependsOnMethods="getcontact",description="updating Specific Contact")
    public void putcontact()
    {  
  	  JSONObject data=new JSONObject();
  	  JSONObject emp=new JSONObject();
  	  JSONObject loc=new JSONObject();
  	  
  	  emp.put("jobTitle", "HR");
  	  emp.put("company", "LTI");
  	  
  	  loc.put("city", "Erode");
  	  loc.put("country", "India");
  	  
  	  data.put("firstName", "Logesh");
  	  data.put("lastName", "M");
  	  data.put("email", "logesh123@gmai.com");
  	  data.put("location", loc);
  	  data.put("employer", emp);
  	  
  	  
  	  			given()
  	  			.header("Content-Type","application/json")
  	  			.body(data.toJSONString())
  				.when()
  				.put("http://3.13.86.142:3000/contacts/"+id)
  				.then()
  				.log()    //print
  				.body()
  				.statusCode(204);
  				
  				
    }
  
  @Test(enabled=true,dependsOnMethods="putcontact",description="deleting Specific Contact")
  public void deletecontact()
  {  
	  System.out.println("Deleting Contact");
	 	        given()
				.when()
				.delete("http://3.13.86.142:3000/contacts/"+id)
				.then()
				.log()    //print
				.body()
				.statusCode(204);
				
  }
  
  
}
