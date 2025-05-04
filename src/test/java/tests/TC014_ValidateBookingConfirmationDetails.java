package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.ProjectSpecificationMethods;
import pages.BookHotelPage;
import pages.BookingConfirmationPage;
import pages.HotelSelectPage;
import pages.LoginPage;
import pages.SearchHotelPage;

public class TC014_ValidateBookingConfirmationDetails extends ProjectSpecificationMethods{

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
	            .enterCheckInDate("24/04/2025")
	            .enterCheckOutDate("25/04/2025")
	            .selectAdultsPerRoomByValue("2")
	            .selectChildPerRoomByValue("0")
	            .clickSearchButton();
	        
	        HotelSelectPage obj1 = new HotelSelectPage(driver);
	        BookHotelPage obj2 = new BookHotelPage(driver);

	        obj1.clickContinue();
		    obj2.enterFirstName("Muthu")
		            .enterLastName("Raj")
		            .enterBillingAddress("Chennai")
		            .enterCreditCardNumber("4111111111111111")
		            .selectCreditCardType("VISA")
		            .selectExpiryMonth("April")
		            .selectExpiryYear("2027")
		            .enterCVV("123")
		            .clickBookNow();
	 }
	 
	 @Test
	 public void validateBookingConfirmationDetails() {
		 BookingConfirmationPage obj3 = new BookingConfirmationPage(driver);

		    // Expected values
		    String expectedHotelName = "Hotel Creek";
		    String expectedLocation = "Sydney";
		    String expectedRoomType = "Standard";
		    String expectedArrivalDate = "24/04/2025";
		    String expectedDepartureDate = "25/04/2025";
		    String expectedRooms = "2 Room(s)";
		    String expectedAdultsPerRoom = "2 Adult(s)";
		    String expectedChildrenPerRoom = "0 Children";
		    String expectedPricePerNight = "AUD $ 175";
		    String expectedTotalPrice = "AUD $ 185";
		    String expectedGST = "AUD $ 18.5";
		    String expectedFinalBilledPrice = "AUD $ 203.5";
		    String expectedFirstName = "Muthu";
		    String expectedLastName = "Raj";
		    String expectedBillingAddress = "Chennai";

		    // Actual values from UI
		    String actualHotelName = obj3.getHotelName();
		    String actualLocation = obj3.getLocation();
		    String actualRoomType = obj3.getRoomType();
		    String actualArrivalDate = obj3.getArrivalDate();
		    String actualDepartureDate = obj3.getDepartureDate();
		    String actualRooms = obj3.getRooms();
		    String actualAdultsPerRoom = obj3.getAdultsPerRoom();
		    String actualChildrenPerRoom = obj3.getChildrenPerRoom();
		    String actualPricePerNight = obj3.getPricePerNight();
		    String actualTotalPrice = obj3.getTotalPrice();
		    String actualGST = obj3.getGST();
		    String actualFinalBilledPrice = obj3.getFinalBilledPrice();
		    String actualFirstName = obj3.getFirstName();
		    String actualLastName = obj3.getLastName();
		    String actualBillingAddress = obj3.getBillingAddress();

		   
		    // Assertions
		    SoftAssert softAssert = new SoftAssert();
	        
	        softAssert.assertEquals(actualHotelName, expectedHotelName, "TC014(Fail) - Hotel Name mismatch");
	        softAssert.assertEquals(actualLocation, expectedLocation, "TC014(Fail) - Location mismatch");
	        softAssert.assertEquals(actualRoomType, expectedRoomType, "TC014(Fail) - Room Type mismatch");
	        softAssert.assertEquals(actualArrivalDate, expectedArrivalDate, "TC014(Fail) - Arrival Date mismatch");
	        softAssert.assertEquals(actualDepartureDate, expectedDepartureDate, "TC014(Fail) - Departure Date mismatch");
	        softAssert.assertEquals(actualRooms, expectedRooms, "TC014(Fail) - Total Rooms mismatch");
	        softAssert.assertEquals(actualAdultsPerRoom, expectedAdultsPerRoom, "TC014(Fail) - Adults Per Room mismatch");
	        softAssert.assertEquals(actualChildrenPerRoom, expectedChildrenPerRoom, "TC014(Fail) - Children Per Room mismatch");
	        softAssert.assertEquals(actualPricePerNight, expectedPricePerNight, "TC014(Fail) - Price Per Night mismatch");
	        softAssert.assertEquals(actualTotalPrice, expectedTotalPrice, "TC014(Fail) - Total Price mismatch");
	        softAssert.assertEquals(actualGST, expectedGST, "TC014(Fail) - GST mismatch");
	        softAssert.assertEquals(actualFinalBilledPrice, expectedFinalBilledPrice, "TC014(Fail) - Final Billed Price mismatch");
	        softAssert.assertEquals(actualFirstName, expectedFirstName, "TC014(Fail) - First Name mismatch");
	        softAssert.assertEquals(actualLastName, expectedLastName, "TC014(Fail) - Last Name mismatch");
	        softAssert.assertEquals(actualBillingAddress, expectedBillingAddress, "TC014(Fail) - Billing Address mismatch");
	        
	        // Important: call assertAll() at the end
	        softAssert.assertAll();
	        
	        System.out.println("TC014(Pass) - Booking confirmation details match the expected values.");
	    }

}
