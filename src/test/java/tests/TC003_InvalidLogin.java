package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.LoginPage;

public class TC003_InvalidLogin extends ProjectSpecificationMethods{
		
		List<Object[]> loginTestData = new ArrayList<>();
		
		@BeforeTest
		public void setup() throws IOException {
			filepath = "C:\\Users\\mraj2\\eclipse-workspace\\AdactinHotelApp\\src\\test\\resources\\LoginData.properties";
			readFromFile(filepath);
			
			
			loginTestData.add(new Object[]{"", prop.getProperty("Password"), "Enter Username"});
			loginTestData.add(new Object[]{prop.getProperty("Username"),"", "Enter Password"});
			loginTestData.add(new Object[]{prop.getProperty("InvalidUsername"), prop.getProperty("Password"), "error"});
			loginTestData.add(new Object[]{prop.getProperty("Username"), prop.getProperty("InvalidPassword"), "error"});
		}
		
		@DataProvider(name = "loginData")
	    public Object[][] fetchLoginData() {
	        return loginTestData.toArray(new Object[0][]);
	    }
		
		@Test(dataProvider = "loginData")
		public void testLogin(String username, String password, String expectedResult) {
			
			LoginPage obj = new LoginPage(driver);
			obj.enterUsername(username)
			.enterPassword(password)
			.clickLoginButton();
			
			switch (expectedResult) {
	        case "Enter Username":
	            Assert.assertTrue(obj.isUsernameErrorDisplayed(), "TC003(Fail) - Username error message not displayed.");
	            System.out.println("TC003(Pass) - Username required error displayed as expected.");
	            break;

	        case "Enter Password":
	            Assert.assertTrue(obj.isPasswordErrorDisplayed(), "TC003(Fail) - Password error message not displayed.");
	            System.out.println("TC003(Pass) - Password required error displayed as expected.");
	            break;

	        case "error":
	            Assert.assertTrue(obj.isErrorMessageDisplayed(), "TC003(Fail) - General login error message not displayed.");
	            System.out.println("TC003(Pass) - Invalid credentials error displayed as expected.");
	            break;

	        case "success":
	            Assert.assertTrue(driver.getCurrentUrl().contains("contact"), "TC003(Fail) - User unable to login.");
	            System.out.println("TC003(Pass) - Login successful.");
	            break;

	        default:
	            Assert.fail("TC003(Fail) - Unexpected test data.");
	    }
			
		}

}
