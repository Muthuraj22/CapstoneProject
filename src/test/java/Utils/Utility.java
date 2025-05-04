package Utils;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import base.ProjectSpecificationMethods;


public class Utility {
	public WebDriver driver;
	public static Properties prop;
	public static String filepath;
	
	public static void readFromFile(String filepath) throws IOException {
		FileReader file = new FileReader(filepath);
		prop = new Properties();
		prop.load(file);
	}
	
	public void browser(String url, String browser) {
		
		if(browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.get("https://adactinhotelapp.com/index.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
		if (this instanceof ProjectSpecificationMethods) {
	        ((ProjectSpecificationMethods) this).setDriver(driver);
	    }
	
	}
	
	public void browserClose() {
		driver.close();
	}
	
	public static String getCellData(String filepath, String SheetName, int rowNum, int colNum) throws IOException {
		try (XSSFWorkbook book = new XSSFWorkbook(filepath)) {
			XSSFSheet sheet = book.getSheet(SheetName);
			XSSFCell cell = sheet.getRow(rowNum).getCell(colNum);
			String data = cell.toString();
			return data;
		}

	}
	
	
	
}

