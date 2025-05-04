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

public class TC007_SelectHotelContinueButton  extends ProjectSpecificationMethods{

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
	    public void testContinueAfterSelectingHotel() {
	        HotelSelectPage obj1 = new HotelSelectPage(driver);
	        obj1.clickContinue();

	        Assert.assertTrue(driver.getCurrentUrl().contains("BookHotel"), "TC007(Fail) - Did not navigate to booking page.");
	        System.out.println("TC007(Pass) - Navigation to Booking Page after selecting hotel is successful.");
	    }

}
