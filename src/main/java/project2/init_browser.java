package project2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;

public class init_browser {

	public static WebDriver init_brw() {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.opera.driver", "/Users.dir/operadriver");
		//launching browser
		WebDriver driver = new OperaDriver();
		//open gamil.com
		//driver.get("http:/www.gmail.com");
		return driver;
	}
	

}
