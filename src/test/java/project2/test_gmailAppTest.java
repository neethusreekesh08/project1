package project2;

import static org.testng.Assert.assertTrue;
import org.testng.Assert;
import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import project2.init_browser;
public class test_gmailAppTest {
	 ExtentReports extent;	
	 ExtentTest logger;
	 FileInputStream fs;
	 public Properties prop;
	public WebDriver driver= init_browser.init_brw();
	@BeforeTest
	public void gmailApp() throws InterruptedException {
		// TODO Auto-generated method stub
		extent = new ExtentReports(System.getProperty("user.dir")+ "/test-output/Compose1Reports.html",true);
		extent.addSystemInfo("Host Name","Gmail Testing")
		      .addSystemInfo("Environment","gettitle")
		      .addSystemInfo("User Name","neethu");
		extent.loadConfig(new File(System.getProperty("user.dir")+"//extent-config.xml"));
		if (prop==null);
		prop=new Properties();
	try {
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//projectconfig.properties"); // will get the path of the current project
		prop.load(fs);
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 

		  driver.get(prop.getProperty("appurl"));
		//Store email and password
				String email = "********@gmail.com";
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				String psw = "*******";
				//Initialise browser
				 System.out.println("Logging In");
				    System.out.println("Logging In");
					//Enter userid and password in browser
					driver.findElement(By.cssSelector(prop.getProperty("CSS_userid"))).sendKeys(email);
					//Click on next button. Location strategy: cssSelector - single attribute (id, name have more than 1 matching nodes)
					driver.findElement(By.cssSelector(prop.getProperty("css_nxtbut1"))).click();
					//Wait for page to load
					Thread.sleep(7000);
					//Enter password. Location strategy: cssSelector - id
					driver.findElement(By.cssSelector(prop.getProperty("css_psw"))).sendKeys(psw);
					//Click on Sign in (Next) button. Location strategy: cssSelector - id
					driver.findElement(By.cssSelector(prop.getProperty("css_nxtbut2"))).click();
					//Wait for 5 seconds
					Thread.sleep(5000);
	}
	@Test
	public void testInboxTitle() {
		//throw new SkipException("Skipping test");
	// Check titles for Inbox,starred,Send,Important tabs
	
	//By default title is inbox
		logger = extent.startTest("testInboxTitle");
		
	String in = driver.getTitle();
	System.out.println(in);
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	System.out.println("Inbox title check");
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	assertTrue(in.contains("Inbox")); 
	logger.log(LogStatus.PASS," testInboxTitle is Passed");
	}
	@Test
	public void testTabs() throws AWTException, InterruptedException {
		logger = extent.startTest("testTabs");
		driver.findElement(By.xpath("//*[@id=\'gbwa\']/div[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\'gb36\']/span[1]")).click();
		//Robot r = new Robot();
		/*  r.keyPress(KeyEvent.VK_CONTROL);
		  r.keyPress(KeyEvent.VK_T);
		  r.keyRelease(KeyEvent.VK_CONTROL);
		  r.keyRelease(KeyEvent.VK_T);*/
		  String url = "https://www.youtube.com/?gl=US";
		  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		  driver.switchTo().window(tabs.get(1));
	      Assert.assertEquals("YouTube", driver.getTitle());
	     String title=driver.getTitle();
	      System.out.println(title);
		  assertTrue(url.contains (driver.getCurrentUrl() ));
		  String currenturl=driver.getCurrentUrl();
		  System.out.println(currenturl);
		  driver.close();     
		  driver.switchTo().window(tabs.get(0));
		  logger.log(LogStatus.PASS,"testTabs is Passed");
		}
	@AfterMethod
    public void getResult(ITestResult result) {
    	if(result.getStatus()==ITestResult.FAILURE) {
    		logger.log(LogStatus.FAIL,"TEst case failed is"+ result.getName());
    		logger.log(LogStatus.FAIL, "Test case failed is"+ result.getThrowable());
    	} else if(result.getStatus()== ITestResult.SKIP) {
    		logger.log(LogStatus.SKIP,"Testcase skipped is" + result.getName());
    	}
    	extent.endTest(logger);
    }
	 @AfterTest
		public void closeAll() throws IOException, EmailException{
			//close browser
			//close db connecion
			//exit browser
		   
	    	extent.flush();
			extent.close();
			 //fs.close();
			
			//System.out.println("Testing email completed.See test report");	
		}
	
	@AfterSuite
	//signout gmail and quit browser
	public void signout() throws EmailException {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//Sign out button. Location strategy - class
		System.out.println("EMAIL REPORT ");
		//Emaildemo.EmailReport();
		driver.findElement(By.xpath(prop.getProperty("xpath_signoutBut"))).click();
		//Location strategy - link text
		driver.findElement(By.cssSelector(prop.getProperty("css_signoutlink"))).click(); 
		//Quit chrome browser
		driver.quit();
		
		
	}
	

}
