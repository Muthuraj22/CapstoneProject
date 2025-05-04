package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.BookedItineraryPage;
import pages.LoginPage;
import pages.SearchHotelPage;

public class TC024_CancelMultipleBooking extends ProjectSpecificationMethods {

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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Wait for checkboxes to be visible
            WebElement secondCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//input[@type='checkbox'])[2]")
            ));
            
            WebElement thirdCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//input[@type='checkbox'])[3]")
            ));
            
            WebElement sixthCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//input[@type='checkbox'])[6]")
            ));

            // Get order ID to verify later
            String orderId1 = secondCheckbox.getAttribute("value");
            System.out.println("Cancelling Order ID: " + orderId1);
            
            String orderId2 = thirdCheckbox.getAttribute("value");
            System.out.println("Cancelling Order ID: " + orderId2);
            
            String orderId3 = sixthCheckbox.getAttribute("value");
            System.out.println("Cancelling Order ID: " + orderId3);

            // Tick the checkbox
            secondCheckbox.click();
            thirdCheckbox.click();
            sixthCheckbox.click();
            
            
            obj.clickCancelSelectedButton();
            
         // Handle the alert
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();

            // Wait for the page to refresh or update
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//input[@type='checkbox' and @value='" + orderId1 + "']")
            ));

            // Try to locate the second booking again to ensure it's removed
            List<WebElement> remaining = driver.findElements(
                By.xpath("//input[@type='checkbox' and @value='" + orderId1 + "']")
            );
            Assert.assertTrue(remaining.size() == 0, "TC024(Fail) - Order ID " + orderId1 + " was not cancelled!");
            System.out.println("TC024(Pass) - Order ID " + orderId1 + " cancelled successfully.");
            
         // Try to locate the third booking again to ensure it's removed
            Assert.assertTrue(remaining.size() == 0, "TC024(Fail) - Order ID " + orderId2 + " was not cancelled!");
            System.out.println("TC024(Pass) - Order ID " + orderId2 + " cancelled successfully.");
            
         // Try to locate the sixth booking again to ensure it's removed
            Assert.assertTrue(remaining.size() == 0, "TC024(Fail) - Order ID " + orderId3 + " was not cancelled!");
            System.out.println("TC024(Pass) - Order ID " + orderId3 + " cancelled successfully.");
        }

}
