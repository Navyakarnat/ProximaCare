package business;

import org.testng.annotations.Test;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class NewTest {
	WebDriver driver;
	final static Logger logger = Logger.getLogger(NewTest.class);
	
	
	@BeforeSuite
	@Parameters({ "browser", "URL" })
	public void SetUp(@Optional("chrome")String browser, String url) {
	//	BasicConfigurator.configure();
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "D:\\MyWorkspace\\Installation\\Driver\\chromedriver.exe");
			DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome();
			handlSSLErr.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new ChromeDriver(handlSSLErr);
			driver.get(url);
			logger.info("Opening Proxima Application in chrome");
			break;
		case "FF":
			System.setProperty("webdriver.gecko.driver", "D:\\MyWorkspace\\Installation\\Driver\\geckodriver.exe");
			ProfilesIni prof = new ProfilesIni();
			FirefoxProfile ffprof = prof.getProfile("sel");
			
			ffprof.setAcceptUntrustedCertificates(true);
			ffprof.setAssumeUntrustedCertificateIssuer(false);
			driver = new FirefoxDriver(ffprof);
			driver.get(url);
			break;
		}
	}

	@Test(dataProvider = "getData")
	public void Login(String UName,String Password) {
		driver.findElement(By.name("txtUserID")).sendKeys(UName);
		logger.info("Entering User Name");
		driver.findElement(By.name("txtPassword")).sendKeys(Password);
		logger.info("Entering Password");
		driver.findElement(By.name("btnLogin")).click();
		logger.info("Logged into the Application");
		
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[1][2];
		data[0][0] = "11";
		data[0][1] = "priya";
		return data;

	}
	
	@AfterSuite
	public void tearDown() {
		driver.close();
	}
}
