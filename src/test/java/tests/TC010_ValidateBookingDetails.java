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

public class TC010_ValidateBookingDetails extends ProjectSpecificationMethods{

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
	    }
	 
	 @Test
	    public void testContinueAfterSelectingHotel() {
	        HotelSelectPage obj1 = new HotelSelectPage(driver);
	        BookHotelPage obj2 = new BookHotelPage(driver);
	        obj1.clickContinue();
	        
	        Assert.assertTrue(driver.getCurrentUrl().contains("BookHotel"), "TC008 - Did not navigate to booking page.");
	        System.out.println("TC008 - Navigation to Booking Page after selecting hotel is successful.");
	        
	        
	        System.out.println("Hotel: " + obj2.getHotelName());
	        System.out.println("Location: " + obj2.getLocation());
	        System.out.println("Room Type: " + obj2.getRoomType());
	        System.out.println("Rooms: " + obj2.getNumberOfRooms());
	        System.out.println("Days: " + obj2.getTotalDays());
	        
	        
	        
	        Assert.assertEquals(obj2.getHotelName(), "Hotel Creek", "TC010(Fail) - Hotel name mismatch");
	        Assert.assertEquals(obj2.getLocation(), "Sydney", "TC010(Fail) - Location mismatch");
	        Assert.assertEquals(obj2.getRoomType(), "Standard", "TC010(Fail) - Room type mismatch");
	        Assert.assertEquals(obj2.getNumberOfRooms().trim(), "2 Room(s)", "TC010(Fail) - Number of rooms mismatch");
	        Assert.assertEquals(obj2.getTotalDays().trim(), "2 Day(s)", "TC010(Fail) - Total days mismatch");

	        System.out.println("TC010(Pass) - All booking details are correctly displayed.");
	        
	 }

}
