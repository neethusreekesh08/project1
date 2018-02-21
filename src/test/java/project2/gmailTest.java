package project2;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;

public class gmailTest {
	WebDriver driver=null;
	 ExtentReports extent;	
		ExtentTest logger;
		FileInputStream fs;
		 public Properties prop;
		@BeforeTest
		 
		public void before_test() {
			
			extent = new ExtentReports(System.getProperty("user.dir")+ "/test-output/ComposeReports.html",true);
			extent.addSystemInfo("Host Name","Gmail Testing")
			      .addSystemInfo("Environment","Email Compose")
			      .addSystemInfo("User Name","neethu");
			extent.loadConfig(new File(System.getProperty("user.dir")+"//extent-config.xml"));
			System.out.println("Send email using data from MySQL DataBase");
			
			
			if (prop==null);
			prop=new Properties();
		try {
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//projectconfig.properties"); // will get the path of the current project
			prop.load(fs);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
		}
	 @Test(dataProvider ="getData",dataProviderClass = fetchdata.class,priority = 1)
	public void email_login(String account_no,String email,String psw,String r_email,String sub) throws IOException, InterruptedException {
    logger = extent.startTest("email_login");
	Thread.sleep(5000);
    driver=init_browser.init_brw();
    driver.get(prop.getProperty("appurl"));
    System.out.println("Logging In");
    driver.findElement(By.cssSelector(prop.getProperty("CSS_userid"))).sendKeys(email);
	//clicking next button
	driver.findElement(By.xpath(prop.getProperty("xpath_nxtbut1"))).click();
	//waiting for 5s
	Thread.sleep(5000);
	//entering password
	driver.findElement(By.xpath(prop.getProperty("xpath_psw"))).sendKeys(psw);
	//click next button
	driver.findElement(By.xpath(prop.getProperty("xpath_nxtbut2"))).click();
	//waiting 5s
	Thread.sleep(5000);
	System.out.println("clicking compose button");
	//click on compose button
	   // driver.findElement(By.cssSelector("#\3a ir > div > div")).click();
         driver.findElement(By.xpath(prop.getProperty("css_comp"))).click();
       //*[@id=":9d"]
        driver.findElement(By.xpath(prop.getProperty("xpath_To"))).sendKeys(r_email);		
    		Thread.sleep(5000);
    		//enter subject
    		
    		driver.findElement(By.cssSelector(prop.getProperty("CSSchr_Sub"))).sendKeys(sub);
    		Thread.sleep(5000);
    		//enter body
    		
    		driver.findElement(By.cssSelector(prop.getProperty("CSSchr_Msg"))).sendKeys("hello");
    		Thread.sleep(5000);
    		//click on send button
    		
    		driver.findElement(By.cssSelector(prop.getProperty("CSSchr_butSend"))).click();
    		// click logout
    		driver.findElement(By.cssSelector(prop.getProperty("css_SignoutBut"))).click();
    	    //driver.findElement(By.xpath("//*[@id='gb']/div[1]/div[1]/div[2]/div[5]/div[1]/a/span")).click();
    		Thread.sleep(5000);
    		
    	   driver.findElement(By.xpath(prop.getProperty("css_signoutlink"))).click();
    	   driver.quit();
    	   logger.log(LogStatus.PASS,"email Compose Passed");
    	    
    	  
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
	public void closeAll() throws IOException {
		//close browser
		//close db connecion
		//exit browser
    	//fs.close();
    	extent.flush();
		extent.close();
		System.out.println("Testing email completed.See test report");	
	}
    
    @AfterSuite
    public void EmailReport(){
    Emaildemo.EmailReport();
    }

    }
    