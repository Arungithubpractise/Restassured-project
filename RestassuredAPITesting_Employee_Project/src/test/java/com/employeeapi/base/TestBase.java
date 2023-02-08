package com.employeeapi.base;

import java.io.IOException;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID = "320800"; // Hard coded Input for Get details of Single Employee & update employee
	
	public Logger logger;

	@BeforeClass
	public void setup() throws IOException {
		
	logger=Logger.getLogger("EmployeesRestAPI");//added Logger
	PropertyConfigurator.configure("Log4j.properties"); //added logger
	logger.setLevel (Level.DEBUG);
	
	Layout layout =new PatternLayout("%d  %c %m %n");
	Appender appender = new FileAppender(layout, "./log/Logging.log");
	BasicConfigurator.configure(appender);
	
	logger = Logger.getLogger(this.getClass().getName());
	
	}

}
	