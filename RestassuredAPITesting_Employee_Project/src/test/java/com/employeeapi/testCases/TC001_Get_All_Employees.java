package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase {

	@BeforeClass
	void getAllEmployees() throws InterruptedException {

		logger.info("*********Started TC001_Get_All_Employees ***********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		Thread.sleep(3);
	}

	@Test
	void checkResposeBody() {
		logger.info("***** Checking Respose Body ***********");

		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" + responseBody);
		Assert.assertTrue(responseBody != null);

	}

	@Test
	void checkStatusCode() {
		logger.info("***** Checking status code ***********");

		int statusCode = response.getStatusCode();
		logger.info("status code is==>" + statusCode);
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	void checkResponseTime() {
		logger.info("*********Checking Response Time ***********");

		long responseTime = response.getTime(); // Getting status Line
		logger.info("Response Time is ==>" + responseTime);
		if (responseTime > 2000)

			logger.warn("Response Time is greater than 2000");
		Assert.assertTrue(responseTime < 2000);
	}

	@Test
	void checkstatusLine() {
		logger.info("*********Checking Status Line **********");

		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType() {
		logger.info("*********Checking Content Type **********");
		String contentType = response.header("Content-Type");
		logger.info("Content type is ==>" + contentType);
		Assert.assertEquals(contentType, "application/json");

	}

	@Test
	void checkserverType() {
		logger.info("*********** Checking Server Type **********");
		
		String serverType = response.header("Server");
		logger.info("Server Type is =>" + serverType);
		Assert.assertEquals(serverType, "nginx/1.21.6");

	}

	@Test
	void checkcontentEncoding() {
		logger.info("********** Checking Content Encoding**********");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is==>" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

	/*@Test
	void checkContentLenght() {
		logger.info("*********** Checking Content Lenght**********");
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is==>" +contentLength);

		if(Integer.parseInt(contentLength)< 100)
			logger.warn("Content Length is less than 100");
		
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}*/

	@Test
	void checkCookies() {
		logger.info("*********** Checking Cookies ********");
		String cookie = response.getCookie("PHPSESSID");
		// Assert.assertEquals (cookie, "lesuvsfslcmiee2bfrsgnijtge");
	}

	@AfterClass
	void tearDown() {
		logger.info("********* Finished TCee1_Get_All_Employees **********");
	}

}
