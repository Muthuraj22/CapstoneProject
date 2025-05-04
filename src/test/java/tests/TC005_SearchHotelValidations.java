package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.LoginPage;
import pages.SearchHotelPage;

public class TC005_SearchHotelValidations  extends ProjectSpecificationMethods{
	
	@BeforeTest
	public void setup() throws IOException {
		filepath = "C:\\Users\\mraj2\\eclipse-workspace\\AdactinHotelApp\\src\\test\\resources\\LoginData.properties";
		readFromFile(filepath);
	}

	@BeforeMethod
	public void login() {
		LoginPage obj = new LoginPage(driver);
		
		obj.enterUsername(prop.getProperty("Username"))
        .enterPassword(prop.getProperty("Password"))
        .clickLoginButton();
	}
	
	@DataProvider(name = "hotelSearchData")
    public Object[][] provideSearchData() {
        return new Object[][] {
        	// Valid data
            {"Sydney", "Hotel Creek", "Standard", "1", "20/04/2025", "22/04/2025", "2", "1", "success"},
            
            // Missing mandatory field - Location
            {"", "Hotel Creek", "Standard", "1", "20/04/2025", "22/04/2025", "2", "0", "error_location"},

            // Check-out date before Check-in date
            {"London", "Hotel Sunshine", "Deluxe", "2", "25/04/2025", "22/04/2025", "1", "1", "error_date"},

            // Missing number of rooms
            {"New York", "Hotel Hervey", "Super Deluxe", "", "20/04/2025", "22/04/2025", "3", "2", "error_room"},

            // Missing adults per room (mandatory)
            {"Melbourne", "Hotel Cornice", "Double", "3", "21/04/2025", "24/04/2025", "", "0", "error_adult"},

            // Missing all mandatory fields
            {"", "", "", "", "", "", "", "0", "error_all"},

            // Check-in and check-out date in wrong format
            {"Brisbane", "Hotel Creek", "Standard", "1", "2025-04-20", "2025-04-22", "2", "0", "error_date_format"},
            
            // Empty Check-in and check-out date
            {"Brisbane", "Hotel Creek", "Standard", "1", "", "", "2", "0", "error_date_empty"}
        };
        }
	
	@Test(dataProvider = "hotelSearchData")
    public void testHotelSearch(String location, String hotel, String roomType, String numRooms,String checkIn, String checkOut, String adults, String children, String expectedResult) {

        SearchHotelPage obj2 = new SearchHotelPage(driver);

        obj2.selectLocationByValue(location)
            .selectHotelByValue(hotel)
            .selectRoomTypeByValue(roomType)
            .selectNumberofRoomsByValue(numRooms)
            .enterCheckInDate(checkIn)
            .enterCheckOutDate(checkOut)
            .selectAdultsPerRoomByValue(adults)
            .selectChildPerRoomByValue(children);

        obj2.clickSearchButton();

        switch (expectedResult) {
            case "success":
                Assert.assertTrue(driver.getCurrentUrl().contains("SelectHotel"), "TC005(Fail) - Expected navigation to Select Hotel page.");
                System.out.println("TC005(Pass) - Valid search test passed.");
                break;
            case "error_location":
                Assert.assertTrue(driver.getPageSource().contains("Please Select a Location"), "TC005(Fail) - Expected location error.");
                System.out.println("TC005(Pass) - Location validation error handled.");
                break;
            case "error_date":
                Assert.assertTrue(obj2.isDateErrorDisplayed(), "TC005(Fail) - Expected date validation error.");
                System.out.println("TC005(Pass) - Date comparison error handled.");
                break;
            case "error_room":
                Assert.assertTrue(driver.getPageSource().contains("Please Select Total Number of Rooms"), "TC005(Fail) - xpected room selection error.");
                System.out.println("TC005(Pass) - Number of rooms validation error handled.");
                break;
            case "error_adult":
                Assert.assertTrue(driver.getPageSource().contains("Please Select Adults per Room"), "TC005(Fail) - Expected adult selection error.");
                System.out.println("TC005(Pass) - Adult per room validation error handled.");
                break;
            case "error_all":
                Assert.assertTrue(driver.getPageSource().contains("Please Select a Location"), "TC005(Fail) - Expected all fields error.");
                System.out.println("TC005(Pass) - All mandatory field error handled.");
                break;
            case "error_date_format":
                Assert.assertTrue(driver.getPageSource().contains("Invalid date format"), "TC005(Fail) - Expected date format error.");
                System.out.println("TC005(Pass) - Date format validation handled.");
                break;  //fail
            case "error_date_empty":
                Assert.assertTrue(driver.getPageSource().contains("Please Select Check-In Date"), "TC005(Fail) - Expected date error.");
                System.out.println("TC005(Pass) - Date validation handled.");
                break;
            default:
                Assert.fail("Unexpected test case result type: " + expectedResult);
        }
    }
		
}
