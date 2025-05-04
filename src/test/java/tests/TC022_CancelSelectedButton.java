package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.BookedItineraryPage;
import pages.LoginPage;
import pages.SearchHotelPage;

public class TC022_CancelSelectedButton extends ProjectSpecificationMethods {

    String cancelledOrderId = "";

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
        .clickBookeditineraryLink();
    }
        
        @Test
        public void testCancelSelectedButtonVisibility() {
        	
        	BookedItineraryPage obj = new BookedItineraryPage(driver);
        	Assert.assertTrue(obj.isCancelSelectedButtonVisible(), "TC022(Fail) -Cancel Selected button is not visible.");
    	    System.out.println("TC022(Pass) -Cancel Selected Button is visible");
        }
        

}
