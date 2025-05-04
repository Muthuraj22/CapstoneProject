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

public class TC006_SelectHotelButtons extends ProjectSpecificationMethods{

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
	    public void testRadioButtonAndButtonsDisplayed() {
	        HotelSelectPage obj = new HotelSelectPage(driver);

	        Assert.assertTrue(obj.isRadioButtonDisplayed(), "TC006(Fail) - Hotel radio button not visible.");
	        Assert.assertTrue(obj.isContinueButtonDisplayed(), "TC006(Fail) - Continue button not visible.");
	        Assert.assertTrue(obj.isCancelButtonDisplayed(), "TC006(Fail) = Cancel button not visible.");

	        System.out.println("TC006(Pass) - All elements on Hotel Selection page are visible.");
	    }

	}
       
