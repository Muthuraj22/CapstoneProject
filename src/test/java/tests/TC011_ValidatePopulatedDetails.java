package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.BookHotelPage;
import pages.HotelSelectPage;
import pages.LoginPage;
import pages.SearchHotelPage;

public class TC011_ValidatePopulatedDetails extends ProjectSpecificationMethods{

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
	            .selectNumberofRoomsByValue("2")
	            .enterCheckInDate("20/04/2025")
	            .enterCheckOutDate("22/04/2025")
	            .selectAdultsPerRoomByValue("2")
	            .selectChildPerRoomByValue("0")
	            .clickSearchButton();
	        
	        HotelSelectPage obj1 = new HotelSelectPage(driver);
	        obj1.clickContinue();
	    }
	 
	 @Test
	 public void testPriceFieldsArePopulatedAndVisible() {
		    BookHotelPage bookPage = new BookHotelPage(driver);

		    Assert.assertFalse(bookPage.getPricePerNight().isEmpty(), "TC011(Fail) - Price per Night is not populated.");
		    Assert.assertFalse(bookPage.getTotalPrice().isEmpty(), "TC011(Fail) - Total Price is not populated.");
		    Assert.assertFalse(bookPage.getGST().isEmpty(), "TC011(Fail) - GST is not populated.");
		    Assert.assertFalse(bookPage.getFinalBilledPrice().isEmpty(), "TC011(Fail) - Final Billed Price is not populated.");

		    System.out.println("TC011(Pass) - All price fields are populated and visible.");
		}

}
