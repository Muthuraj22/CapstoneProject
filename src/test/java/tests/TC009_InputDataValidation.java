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

public class TC009_InputDataValidation extends ProjectSpecificationMethods{

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
	            .enterCheckInDate("24/04/2025")
	            .enterCheckOutDate("25/04/2025")
	            .selectAdultsPerRoomByValue("2")
	            .selectChildPerRoomByValue("0")
	            .clickSearchButton();
	    }

	    @Test
	    public void validateSelectedHotelDetails() {
	        HotelSelectPage selectHotel = new HotelSelectPage(driver);

	        String expectedHotelName = "Hotel Creek";
	        String expectedLocation = "Sydney";
	        String expectedRooms = "1 Rooms";
	        String expectedArrival = "24/04/2025";
	        String expectedDeparture = "25/04/2025";
	        String expectedNumDays = "1 Days";
	        String expectedRoomType = "Standard";
	        String expectedPricePerNight = "AUD $ 125";
	        String expectedTotalPrice = "AUD $ 135";

	        System.out.println("Hotel Name: " + selectHotel.getHotelName());
	        System.out.println("Location: " + selectHotel.getLocation());
	        System.out.println("Rooms: " + selectHotel.getRooms());
	        
	        // Fetch actual values from the page
	        Assert.assertEquals(selectHotel.getHotelName(), expectedHotelName, "TC009(Fail) - Hotel name mismatch");
	        Assert.assertEquals(selectHotel.getLocation(), expectedLocation, "TC009(Fail) - Location mismatch");
	        Assert.assertEquals(selectHotel.getRooms(), expectedRooms, "TC009(Fail) - Rooms mismatch");
	        Assert.assertEquals(selectHotel.getArrivalDate(), expectedArrival, "TC009(Fail) - Arrival date mismatch");
	        Assert.assertEquals(selectHotel.getDepartureDate(), expectedDeparture, "TC009(Fail) - Departure date mismatch");
	        Assert.assertEquals(selectHotel.getNumberOfDays(), expectedNumDays, "TC009(Fail) - Number of days mismatch");
	        Assert.assertEquals(selectHotel.getRoomType(), expectedRoomType, "TC009(Fail) - Room type mismatch");
	        Assert.assertEquals(selectHotel.getPricePerNight(), expectedPricePerNight, "TC009(Fail) - Price per night mismatch");
	        Assert.assertEquals(selectHotel.getTotalPrice(), expectedTotalPrice, "TC009(Fail) - Total price mismatch");

	        System.out.println("TC009(Pass) - All hotel details are correctly reflected.");
	    }

}
