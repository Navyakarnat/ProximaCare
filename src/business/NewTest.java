package business;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import Logs.GenerateLogs;
import generic.CaptureScreenshot;
import generic.ExcelUtility;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	final String Filepath = "C:\\Users\\navya\\git\\ProximaCare\\src\\testData\\testData.xlsx";
	final String ShName = "Sheet1";

	@BeforeSuite
	@Parameters({ "browser", "URL" })
	public void SetUp(@Optional("chrome") String browser, String url) {
		// BasicConfigurator.configure();
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "D:\\Workspace\\Installation\\exe files\\chromedriver.exe");
			
			DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome();
			handlSSLErr.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			
			driver = new ChromeDriver(handlSSLErr);
			
			driver.get(url);
			GenerateLogs.INFO("Opening Proxima Application in chrome");
			break;
			
		case "FF":
			System.setProperty("webdriver.gecko.driver", "D:\\MyWorkspace\\Installation\\Driver\\geckodriver.exe");
			ProfilesIni prof = new ProfilesIni();
			FirefoxProfile ffprof = prof.getProfile("sel");

			ffprof.setAcceptUntrustedCertificates(true);
			ffprof.setAssumeUntrustedCertificateIssuer(false);
			driver = new FirefoxDriver(ffprof);
			driver.get(url);
			GenerateLogs.INFO("Opening Proxima Application in chrome");
			break;
		}
	}

	@Test(dataProvider = "getData")
	public void Login(String UName, String Password) throws IOException {
		driver.findElement(By.name("txtUserID")).sendKeys(UName);
		GenerateLogs.INFO("Entering User Name");
		driver.findElement(By.name("txtPassword")).sendKeys(Password);
		GenerateLogs.INFO("Entering Password");
		driver.findElement(By.name("btnLogin")).click();
		GenerateLogs.INFO("Logged into the Application");
		WebElement Logout = driver.findElement(By.linkText("logout"));
		boolean signIn = Logout.isDisplayed();
		if(signIn == true) {
		CaptureScreenshot.getScreenshot(driver, "Login");	
		}
	
		driver.findElement(By.linkText("logout")).click();

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		Object[][] data = null;
		ExcelUtility excel = new ExcelUtility(Filepath);
		data = excel.ReadData(ShName);
		return data;
		/*
		 * data[0][0] = "11"; data[0][1] = "priya"; return data;
		 */

	}

	@AfterSuite
	public void tearDown() {
		driver.close();
	}
}
