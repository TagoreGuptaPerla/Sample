package ModifyMyOrder;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
 
public class SampleTest {
 
	@Test
	public void detailedOrderStatusResponse()
	{   
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://mbcpnydev01:7808";
		
		//Request Creation
		String Request = "{"+
				"\"detailedOrderStatusRequest\":{" + 
				"   \"orderDetailRequest\": {" + 
				"      \"brandID\": \"ATLAS\"," + 
				"      \"orderType\": \"\"," + 
				"      \"orderNumber\": \"W00995422403887\"," + 
				"      \"trackingNumber\": \"\"," + 
				"      \"productType\": \"\"," + 
				"      \"emailId\": \"\"," + 
				"      \"sourceSystem\": \"MBP\"}"+
				"}"+
				"}";
		
		// Get the RequestSpecification of the request that you want to sent to the server.
		// The server is specified by the BaseURI that we have specified in the above step.
		RequestSpecification httpRequest = RestAssured.given().body(Request);
 
		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.POST, "/wismo/Service");
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		//JsonPath InnerParth = response.path("detailedOrderStatusResponse");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		String  OrderNumber = jsonPathEvaluator.get("detailedOrderStatusResponse.orderID");
		System.out.println("Order Number is:" + OrderNumber);
		Assert.assertEquals(OrderNumber.contains("W00995422403887"),true,"Order Number Wrong");
	}
	
	@Test
	public void orderDetailByEmailIdResponse()
	{
		RestAssured.baseURI = "http://mbcpnydev01:7808";
		
		String Request = "{"+
			   "\"orderDetailByEmailIdRequest\": {"+
			    " \"orderDetailRequest\": {"+
			       " \"brandID\": \"ATLAS\","+
			        "\"orderType\": \"\","+
			         "\"orderNumber\": \"\","+
			         "\"trackingNumber\": \"\","+
			         "\"productType\": \"\","+
			         "\"emailId\": \"jhokanson@bco.com\","+
			         "\"sourceSystem\": \"MBP\"}"+
			   "}"+
			"}";
		RequestSpecification httpRequest = RestAssured.given().body(Request);
		 
		Response response = httpRequest.request(Method.POST, "/wismo/Service");
		
		JsonPath jsonPathEvaluator = response.jsonPath();

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		String  EmailID = jsonPathEvaluator.get("orderDetailByEmailIdResponse.emailId");
		System.out.println("EmailID is:" + EmailID);
		Assert.assertEquals(EmailID.contains("jhokanson@bco.com"),true,"EmailID is Wrong");
		
	}
	
	@Test
	public void orderStatusResponse()
	{
		RestAssured.baseURI = "http://mbcpnydev01:7808";
		
		String Request = "{"+
		           "\"orderStatusRequest\": {"+
				      "\"orderDetailRequest\": {"+
				         "\"brandID\": \"ATLAS\","+
				         "\"orderType\": \"\","+
				         "\"orderNumber\": \"5422541903\","+
				         "\"trackingNumber\": \"\","+
				         "\"productType\": \"\","+
				         "\"emailId\": \"\","+
				         "\"sourceSystem\": \"MBP\"}"+
				   "}"+
				"}";

		RequestSpecification httpRequest = RestAssured.given().body(Request);
		 
		Response response = httpRequest.request(Method.POST, "/wismo/Service");
		
		JsonPath jsonPathEvaluator = response.jsonPath();

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		String  orderID = jsonPathEvaluator.get("orderStatusResponse.orderID");
		System.out.println("Order Number is:" + orderID);
		Assert.assertEquals(orderID.contains("5422541903"),true,"Order Number is Wrong");
		
	}
	
 
}
