package commonFunctions;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.Apputil;

public class Functionlibrary extends Apputil{
	public static boolean verify_Login(String user,String pass)
	{
		driver.get(conpro.getProperty("URL"));
		driver.findElement(By.xpath(conpro.getProperty("objuser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("objpass"))).sendKeys(pass);
		driver.findElement(By.xpath(conpro.getProperty("objlogin"))).click();
		String Expected ="dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			Reporter.log("Login success::"+Expected+"    "+Actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login Failed::"+Expected+"   "+Actual,true); 
			return false;
			
		}
	}

}
