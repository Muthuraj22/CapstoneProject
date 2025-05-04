package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class TC020_VerifyBookingCancellation extends ProjectSpecificationMethods {

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
    public void verifyBookingCancellation() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for itinerary page to load (adjust as per actual title if needed)
        wait.until(ExpectedConditions.titleContains("Adactin.com - Select Hotel"));

        // Ensure at least one order checkbox is present
        List<WebElement> orderCheckboxes = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
            		By.xpath("(//input[@type='button' and contains(@value,'Cancel')])[1]")
            )
        );

        Assert.assertTrue(orderCheckboxes.size() > 0, "TC020(Fail) -No booking checkboxes found!");

        WebElement firstOrderCheckbox = orderCheckboxes.get(0);
        cancelledOrderId = firstOrderCheckbox.getDomAttribute("value");
        System.out.println("Cancelling Order ID: " + cancelledOrderId);

        // Select the booking
        firstOrderCheckbox.click();

        // ✅ Accept the alert immediately
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert text: " + alert.getText());
            alert.accept();
        } catch (Exception e) {
            Assert.fail("Alert not handled properly", e);
        }

        // ✅ Wait for cancellation to reflect
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.xpath("//input[@value='" + cancelledOrderId + "']")));

        // ✅ Confirm the order is no longer present
        List<WebElement> remainingOrderIds = driver.findElements(By.xpath("//input[contains(@id,'order_id_')]"));
        boolean orderStillExists = remainingOrderIds.stream()
            .anyMatch(order -> order.getAttribute("value").equals(cancelledOrderId));

        Assert.assertFalse(orderStillExists, "TC020(Fail) - Cancelled booking still appears in the itinerary!");
        System.out.println("TC020(Pass) - Booking with Order ID '" + cancelledOrderId + "' has been successfully cancelled.");
    }

}