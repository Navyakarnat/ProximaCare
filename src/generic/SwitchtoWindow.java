package generic;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class SwitchtoWindow {
	WebDriver driver;
	
	public void MoveToWindow() {
		try {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		String firstWindow = itr.next();
		String secondWindow = itr.next();
		driver.switchTo().window(secondWindow);
		Logs.GenerateLogs.INFO("Passing the control to second Window");	
	}catch (Exception e) {
		Logs.GenerateLogs.INFO("Not able to pass control to second window" + e.getMessage());
	}
		
		
	}

}	
