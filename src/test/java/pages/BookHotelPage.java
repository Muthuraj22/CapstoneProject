package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class BookHotelPage extends ProjectSpecificationMethods {

    private WebDriverWait wait;

    @FindBy(id = "hotel_name_dis") private WebElement hotelName;
    @FindBy(id = "location_dis") private WebElement location;
    @FindBy(id = "room_type_dis") private WebElement roomType;
    @FindBy(id = "room_num_dis") private WebElement numberOfRooms;
    @FindBy(id = "total_days_dis") private WebElement totalDays;
    @FindBy(id = "price_night_dis") private WebElement pricePerNight;
    @FindBy(id = "total_price_dis") private WebElement totalPrice;
    @FindBy(id = "gst_dis") private WebElement gst;
    @FindBy(id = "final_price_dis") private WebElement finalBilledPrice;

    @FindBy(id = "first_name") private WebElement firstName;
    @FindBy(id = "last_name") private WebElement lastName;
    @FindBy(id = "address") private WebElement billingAddress;
    @FindBy(id = "cc_num") private WebElement creditCardNumber;
    @FindBy(id = "cc_type") private WebElement creditCardType;
    @FindBy(id = "cc_exp_month") private WebElement expiryMonth;
    @FindBy(id = "cc_exp_year") private WebElement expiryYear;
    @FindBy(id = "cc_cvv") private WebElement cvvNumber;

    @FindBy(id = "book_now") private WebElement bookNowButton;

    public BookHotelPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Value Getters
    public String getHotelName() { return wait.until(ExpectedConditions.visibilityOf(hotelName)).getDomProperty("value"); }
    public String getLocation() { return location.getDomProperty("value"); }
    public String getRoomType() { return roomType.getDomProperty("value"); }
    public String getNumberOfRooms() { return numberOfRooms.getDomProperty("value"); }
    public String getTotalDays() { return totalDays.getDomProperty("value"); }
    public String getPricePerNight() { return pricePerNight.getDomProperty("value"); }
    public String getTotalPrice() { return totalPrice.getDomProperty("value"); }
    public String getGST() { return gst.getDomProperty("value"); }
    public String getFinalBilledPrice() { return finalBilledPrice.getDomProperty("value"); }

    // Input Methods
    public BookHotelPage enterFirstName(String fName) {
        wait.until(ExpectedConditions.visibilityOf(firstName)).clear();
        firstName.sendKeys(fName);
        return this;
    }

    public BookHotelPage enterLastName(String lName) {
        wait.until(ExpectedConditions.visibilityOf(lastName)).clear();
        lastName.sendKeys(lName);
        return this;
    }

    public BookHotelPage enterBillingAddress(String addressText) {
        wait.until(ExpectedConditions.visibilityOf(billingAddress)).clear();
        billingAddress.sendKeys(addressText);
        return this;
    }

    public BookHotelPage enterCreditCardNumber(String ccNum) {
        wait.until(ExpectedConditions.visibilityOf(creditCardNumber)).clear();
        creditCardNumber.sendKeys(ccNum);
        return this;
    }

    public BookHotelPage selectCreditCardType(String type) {
        try {
            new Select(creditCardType).selectByVisibleText(type);
        } catch (NoSuchElementException e) {
            // silently fail to allow error validation
        }
        return this;
    }

    public BookHotelPage selectExpiryMonth(String month) {
        try {
            new Select(expiryMonth).selectByVisibleText(month);
        } catch (NoSuchElementException e) {
            // skip invalid
        }
        return this;
    }

    public BookHotelPage selectExpiryYear(String year) {
        try {
            new Select(expiryYear).selectByVisibleText(year);
        } catch (NoSuchElementException e) {
            // skip invalid
        }
        return this;
    }

    public BookHotelPage enterCVV(String cvv) {
        wait.until(ExpectedConditions.visibilityOf(cvvNumber)).clear();
        cvvNumber.sendKeys(cvv);
        return this;
    }

    public BookingConfirmationPage clickBookNow() {
        bookNowButton.click();
        return new BookingConfirmationPage(driver);
    }

    // Error Message Getters
    public String getFirstNameError() {
        return getErrorTextById("first_name_span");
    }

    public String getLastNameError() {
        return getErrorTextById("last_name_span");
    }

    public String getAddressError() {
        return getErrorTextById("address_span");
    }

    public String getCreditCardNumberError() {
        return getErrorTextById("cc_num_span");
    }

    public String getCreditCardTypeError() {
        return getErrorTextById("cc_type_span");
    }

    public String getExpiryMonthError() {
        return getErrorTextById("cc_expiry_span");
    }

    public String getCVVError() {
        return getErrorTextById("cc_cvv_span");
    }

    private String getErrorTextById(String id) {
        try {
            return driver.findElement(By.id(id)).getText();
        } catch (NoSuchElementException e) {
            return "";  // Avoids test break on missing error
        }
    }
}

