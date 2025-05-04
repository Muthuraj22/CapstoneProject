package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.LoginPage;

public class TC004_ValidLogin   extends ProjectSpecificationMethods{

	@BeforeTest
	public void setup() throws IOException {
		filepath = "C:\\Users\\mraj2\\eclipse-workspace\\AdactinHotelApp\\src\\test\\resources\\LoginData.properties";
		readFromFile(filepath);
	}
	
	
	@Test
	public void testSignupButtonIsVisible() {
		
	
		LoginPage obj = new LoginPage(driver);
		
		obj.enterUsername(prop.getProperty("Username"))
		.enterPassword(prop.getProperty("Password"))
		.clickLoginButton();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("SearchHotel"), "TC004(Fail) -User unable to LOGIN");
		System.out.println("TC004(Pass) - User able to Login successfully");
		
	}

}
