package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.BookHotelPage;
import pages.BookingConfirmationPage;
import pages.HotelSelectPage;
import pages.LoginPage;
import pages.SearchHotelPage;

public class TC015_SearchHotelButtonNavigation extends ProjectSpecificationMethods{

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
	            .enterCheckInDate("25/04/2025")
	            .enterCheckOutDate("26/04/2025")
	            .selectAdultsPerRoomByValue("2")
	            .selectChildPerRoomByValue("0")
	            .clickSearchButton();
	        
	        HotelSelectPage obj1 = new HotelSelectPage(driver);
	        BookHotelPage obj2 = new BookHotelPage(driver);

	        obj1.clickContinue();
		    obj2.enterFirstName("Muthu")
		            .enterLastName("Raj")
		            .enterBillingAddress("Thiruvottiyur, Chennai")
		            .enterCreditCardNumber("4111111111111111")
		            .selectCreditCardType("VISA")
		            .selectExpiryMonth("April")
		            .selectExpiryYear("2027")
		            .enterCVV("123")
		            .clickBookNow();
	 }
	 
	 @Test
		public void testSearchHotelButton() {
		 
		 BookingConfirmationPage obj5 = new BookingConfirmationPage(driver);
		 obj5.clickSearchHotelButton();
			
			Assert.assertTrue(driver.getCurrentUrl().contains("SearchHotel"), "TC015(Fail) - User didn't navigate to Hotel Search Page");
			System.out.println("TC015(Pass) - User navigated to Hotel Search page successfully");
			
		}
 
}
