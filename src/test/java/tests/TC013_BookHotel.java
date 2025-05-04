package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.BookHotelPage;
import pages.HotelSelectPage;
import pages.LoginPage;
import pages.SearchHotelPage;

public class TC013_BookHotel extends ProjectSpecificationMethods{

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
	 public void testBookingWithMandatoryFields() {
		    BookHotelPage obj2 = new BookHotelPage(driver);

		    obj2.enterFirstName("Muthu")
		            .enterLastName("Raj")
		            .enterBillingAddress("Thiruvottiyur, Chennai")
		            .enterCreditCardNumber("4111111111111111")
		            .selectCreditCardType("VISA")
		            .selectExpiryMonth("April")
		            .selectExpiryYear("2027")
		            .enterCVV("123")
		            .clickBookNow();

		    // Wait for confirmation
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    boolean confirmationPresent = wait.until(ExpectedConditions.urlContains("BookingConfirm"));

		    Assert.assertTrue(confirmationPresent, "TC013(Fail) - Booking not successful.");
		    System.out.println("TC013(Pass) - Booking completed and navigated to confirmation page.");
		}

}
