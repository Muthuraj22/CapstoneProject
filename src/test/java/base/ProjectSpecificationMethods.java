package base;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

import Utils.Utility;

public class ProjectSpecificationMethods extends Utility{
	
	protected WebDriverWait wait;
	
	public void setDriver(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
	
	@Parameters({"url","browser"})
	@BeforeMethod
	public void launchingBrowserAndLoadingURL(String url, String browser) {
//		System.out.println("URL received: " + url);
//	    System.out.println("Browser received: " + browser);
		browser(url, browser);
	}
	
	public void takeScreenshot(String testName) {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);
	    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File dest = new File("./screenshots/" + testName + "_" + timestamp + ".png");

	    try {
	        FileUtils.copyFile(src, dest);
	        System.out.println("✅ Screenshot saved: " + dest.getAbsolutePath());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@AfterMethod
	public void closeBrowser() {
		if (driver != null) {  // Ensure driver is not null
	        try {
	            driver.quit();
	            driver = null; // Reset driver to prevent reuse of a closed session
	        } catch (WebDriverException e) {
	            System.out.println("WebDriver already closed or invalid session.");
	        }
	    } 
	}
	
	
}
