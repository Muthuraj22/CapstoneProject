package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.LoginPage;

public class TC002_LoginButtonClickability  extends ProjectSpecificationMethods{

	@BeforeTest
	public void setup() throws IOException {
		filepath = "C:\\Users\\mraj2\\eclipse-workspace\\AdactinHotelApp\\src\\test\\resources\\LoginData.properties";
		readFromFile(filepath);
	}
	
	@BeforeMethod
    public void initPageObject() {
    }
	
	
	@Test
	public void testSignupButtonIsVisible() {
		
	
		LoginPage obj = new LoginPage(driver);
		
	    Assert.assertTrue(obj.isLoginButtonClickable(), "TC002(Fail)Login button is not visible.");
	    System.out.println("TC002(Pass) - Login Button is Clickable");
	}

}
