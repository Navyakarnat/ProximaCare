package generic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {

	public static void getScreenshot(WebDriver driver, String ScreenshotName) throws IOException {
		String dateName = new SimpleDateFormat("DDMMYY").format(new Date());
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String DestinationFile = "C:\\Users\\navya\\git\\ProximaCare\\Screenshots\\" + ScreenshotName + dateName
				+ ".png";
		File destFile = new File(DestinationFile);
		FileUtils.copyFile(src, destFile);

	}

}
