package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.Functionlibrary;
import config.Apputil;
import utilities.Excel_File_Util;

public class AppTest extends Apputil {
String inputpath = "./Fileinput/LoginData_lyst3815.xlsx";
String outputpath = "./Fileoutput/DataDriven Results.xlsx";
Boolean res = false;
ExtentReports report;
ExtentTest logger;
@Test
public void starttest()throws Throwable
{
	//define path of html
	report = new ExtentReports(".Reports/Login.html");
	//create object for Excel file util class
	Excel_File_Util xl = new Excel_File_Util(inputpath);
	//count no.of rows in loginsheet
	int rc = xl.rowcount("Login");
	Reporter.log("No.of rows are::"+rc,true);
	//iterate all rows in loginsheet
	for (int i=1; i<=rc; i++)
	{
		//test case starts here
		logger = report.startTest("Validate login");
		//read username and password cells
		String Username = xl.getcelldata("Login", i, 0);
		String Password = xl.getcelldata("Login", i, 1);
		//cell login method from function library class 
		res = Functionlibrary.verify_Login(Username, Password);
		if (res)
		{
			//if res is true write as login success into results cell
			xl.setcelldata("Login", i, 2, "Login Success", outputpath);
			//write as Pass into Status Cell
			xl.setcelldata("Login", i, 3, "Pass", outputpath);
			logger.log(LogStatus.PASS, "Valid username and Password");
			}
		else
		{
//Take screenshot for fail steps
File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(screen, new File("./Screenshot/Iterations/"+i+"Loginpage.png"));
//capture error message
String Error_message = driver.findElement(By.xpath(conpro.getProperty("objError"))).getText();
xl.setcelldata("Login", i, 2, Error_message,outputpath);
xl.setcelldata("Login", i, 3, "fail",outputpath);
		logger.log(LogStatus.FAIL, Error_message);
		}
		report.endTest(logger);
		report.flush();
	}
	}
	}
