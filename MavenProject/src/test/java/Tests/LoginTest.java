package Tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Pages.LoginPage;
import Utility.BaseClass;

public class LoginTest extends BaseClass {

	LoginPage loginpage;

	@Parameters({"username","password"})
	@Test
	public void LoginTestCase(String username,String password) throws Exception 
	{
		try
			{
			 	loginpage = new Pages.LoginPage(getDriver());
			 	loginpage.loginAction(username,password);
				Assert.assertEquals(loginpage.verifyTitle(), "Online Shopping Site for Mobiles, Fashion, Books, Electronics, Home Appliances and More");
				writeToLog("INFO", "Home Page title verified");
			}
		catch(Exception e)
		{
			e.printStackTrace();
			getscreenshot();
		}
	
	}

}
