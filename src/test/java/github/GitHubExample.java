package github;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GitHubExample {
  @Test(enabled=false,description="get all repository for Authentic user")
  public void getAllRepository() 
  {
	  given()
	  	.auth()
	  	.oauth2("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")
	  .when()
	  	.get("https://api.github.com/user/repos")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(200);
  }
  
  @SuppressWarnings("unchecked")
@Test(description="get all repository for Authentic user")
  public void createRepository() 
  {
	  JSONObject para=new JSONObject();
	  
	  para.put("name", "GitHubExampleCreate");
	  para.put("description", "Sample for Post Request");
	  para.put("homepage", "https://github.com/logeshhhh");
	  
	  given()
	  	.auth()
	  	.oauth2("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")
	  	.header("Content-Type","application/json")
		.body(para.toJSONString())
	  .when()
	  	.post("https://api.github.com/user/repos")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(201);
  }
}
