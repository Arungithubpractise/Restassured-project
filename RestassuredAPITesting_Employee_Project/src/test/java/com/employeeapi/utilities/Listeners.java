package com.employeeapi.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter{	
	
	public ExtentHtmlReporter htmlReporter; 
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {
		
		
		//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		//String repName = "Test-Report-" + timeStamp + ".html";

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/myReport.html");// specify	location where report should be saved 																								// location
		//htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("project Name", "Employee Datebase API");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Arun");

		htmlReporter.config().setDocumentTitle("Automation report"); // Title of report
		htmlReporter.config().setReportName("Rest API TESTING REPORT"); // name of the report
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); // create new entry in th report
		test.log(Status.PASS, "Test case passed is" +result.getName()); // send the passed inf
	}

	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getName());
		
		test.log(Status.FAIL, "Test case failed is" +result.getName()); // create new entry in th report
		test.log(Status.FAIL, "Test case failed is" +result.getThrowable()); // send the failed inf

		
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.SKIP, "Test case Skipped is" + result.getName());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}



