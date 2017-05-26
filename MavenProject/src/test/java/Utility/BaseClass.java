package Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {
	static BufferedWriter bufferWritter;
	public WebDriver driver;
  	
	@Parameters({"browser","url","username","password"})
	@BeforeClass
  	public void setUp(String sBrowser,String sUrl,String username,String password){
  		if(sBrowser.equalsIgnoreCase("firefox"))
  		{
  		driver = new FirefoxDriver();
  		}else if (sBrowser.equalsIgnoreCase("chrome")){
  			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chroedriver\\chromedriver.exe");
  			driver = new ChromeDriver();
  		}	
		else if (sBrowser.equalsIgnoreCase("iebrowser")){
  			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\iedriver\\iedriver.exe");
  			driver = new ChromeDriver();
  		}	

  		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  		driver.get(sUrl);
//  		loginAction(username, password);
  	}
  	
  	public WebDriver getDriver(){
  		return driver;
  	} 	
  	
//  Test Code for testing purpose and will remove once after coding part is done
  	
/**  	public void loginAction(String username,String password){
  		LoginPage objPage=new LoginPage(driver);
  		objPage.setUserName(username);
  		objPage.setPassword(password);
  		objPage.clickLogin();
		
//	}*/
  	
  	/*
  	 * mathod : getscreenshot
  	 * Description : Get the screen shot when ever the exception occurs and store the screen shots in Screen shots folder
  	 */
  	 public void getscreenshot() throws Exception 
     {
             File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
             FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\Screenshots\\screenshot.png"));
     }
		 	
  	@AfterClass
  	public void tearDown(){
  		driver.quit();	
  	}
  	
    //Get the project working directory
    public static String getProjectDirectory() {
        String projectDir = System.getProperty("user.dir");
        return projectDir;
    }

    // Create a logfile for writing log content
    public static File createLogFile() throws IOException {

        String formattedCurrentDateTime = new java.text.SimpleDateFormat("ddMMyyyyhhmmssSSS").format(new Date());
        String logFilePath = getProjectDirectory() + "\\resultlogs\\" + "Test_Results_" + formattedCurrentDateTime + ".txt";

        File logFile = new File(logFilePath);
        return logFile;

    }    
    public static void writeToLog(String logLevel, String logMessage) {
        try {
            String formattedCurrentDateTime = new java.text.SimpleDateFormat("dd-MM-yyyy hh:mm:ss:SSS").format(new Date());
            bufferWritter.write(formattedCurrentDateTime + "\t" + logLevel + "\t" + logMessage + "\r\n");
        } catch (IOException ie) {
            System.out.print("Exception while trying to write content into Log File");

        }
    }
  	
}
