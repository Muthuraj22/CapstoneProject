package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HotelSelectPage;
import pages.LoginPage;
import pages.SearchHotelPage;

public class TC008_SelectHotelCancelButton extends ProjectSpecificationMethods{

	@BeforeTest
	public void setup() throws IOException {
		filepath = "C:\\Users\\mraj2\\eclipse-workspace\\AdactinHotelApp\\src\\test\\resources\\LoginData.properties";
		readFromFile(filepath);
	}

	 @BeforeMethod
	    public void loginAndSearchHotel() {
	        new LoginPage(driver)
	            .enterUsername(prop.getProperty("Username"))
	            .enterPassword(prop.getProperty("Password"))
	            .clickLoginButton();

	        new SearchHotelPage(driver)
	            .selectLocationByValue("Sydney")
	            .selectHotelByValue("Hotel Creek")
	            .selectRoomTypeByValue("Standard")
	            .selectNumberofRoomsByValue("1")
	            .enterCheckInDate("20/04/2025")
	            .enterCheckOutDate("22/04/2025")
	            .selectAdultsPerRoomByValue("2")
	            .selectChildPerRoomByValue("0")
	            .clickSearchButton();
	    }
	 
	 @Test
	    public void testCancelButtonFunctionality() {
	        HotelSelectPage obj2 = new HotelSelectPage(driver);
	        obj2.clickCancel();

	        Assert.assertTrue(driver.getCurrentUrl().contains("SearchHotel"), "TC008(Fail) - Cancel did not return to Search Hotel page.");
	        System.out.println("TC008(Pass) - Cancel button returned to Search Hotel page.");
	    }

}
