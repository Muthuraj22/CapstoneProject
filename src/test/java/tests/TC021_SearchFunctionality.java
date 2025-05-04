package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.BookHotelPage;
import pages.BookedItineraryPage;
import pages.BookingConfirmationPage;
import pages.HotelSelectPage;
import pages.LoginPage;
import pages.SearchHotelPage;

public class TC021_SearchFunctionality extends ProjectSpecificationMethods {

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
            .selectLocationByValue("Sydney")
            .selectHotelByValue("Hotel Creek")
            .selectRoomTypeByValue("Standard")
            .selectNumberofRoomsByValue("2")
            .enterCheckInDate("25/04/2025")
            .enterCheckOutDate("26/04/2025")
            .selectAdultsPerRoomByValue("2")
            .selectChildPerRoomByValue("0")
            .clickSearchButton();

        HotelSelectPage hotelSelectPage = new HotelSelectPage(driver);
        BookHotelPage bookHotelPage = new BookHotelPage(driver);

        hotelSelectPage.clickContinue();

        bookHotelPage.enterFirstName("Muthu")
                     .enterLastName("Raj")
                     .enterBillingAddress("Thiruvottiyur, Chennai")
                     .enterCreditCardNumber("4111111111111111")
                     .selectCreditCardType("VISA")
                     .selectExpiryMonth("April")
                     .selectExpiryYear("2027")
                     .enterCVV("123")
                     .clickBookNow();

        BookingConfirmationPage confirmationPage = new BookingConfirmationPage(driver);
        confirmationPage.clickMyItineraryButton();
       
    }
    
    @Test
    public void verifySearchFunctionality() {
    	
    	BookedItineraryPage obj2 = new BookedItineraryPage(driver);
    	
    	WebElement orderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
    			By.xpath("(//input[@type='text' and contains(@id,'order_id_')])[2]")
    	    ));
    	    String orderId = orderInput.getAttribute("value");
    	    System.out.println("Searching for Order ID: " + orderId);

    	    
    	obj2.enterInSearchBar(orderId);  //"UFFT33Q427"
    	obj2.clickGoButton();
    	
    	
    	
    	 // Step 5: Verify the result shows the searched order
        WebElement displayedOrder = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//input[@value='" + orderId + "']") // checkbox or hidden input with order id
        ));

        Assert.assertTrue(displayedOrder.isDisplayed(), "TC021(Fail) -Searched Order ID is not displayed!");
        System.out.println("TC021(Pass) - Searched order ID is displayed");
    }


}
