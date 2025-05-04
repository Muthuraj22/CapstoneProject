package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class HotelSelectPage extends ProjectSpecificationMethods{

    WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "radiobutton_0")
    private WebElement hotelRadioButton;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;
    
    @FindBy(id = "hotel_name_0")
    private WebElement hotelName;
    
    @FindBy(id = "location_0") 
    private WebElement location;
    
    @FindBy(id = "rooms_0")
    private WebElement rooms;
    
    @FindBy(id = "arr_date_0") 
    private WebElement arrivalDate;
    
    @FindBy(id = "dep_date_0")
    private WebElement departureDate;
    
    @FindBy(id = "no_days_0") 
    private WebElement numberOfDays;
    
    @FindBy(id = "room_type_0")
    private WebElement roomType;
    
    @FindBy(id = "price_night_0")
    private WebElement pricePerNight;
    
    @FindBy(id = "total_price_0")
    private WebElement totalPrice;
    


	public HotelSelectPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
	}


	public boolean isRadioButtonDisplayed() {
        return hotelRadioButton.isDisplayed();
    }

    public boolean isContinueButtonDisplayed() {
        return continueButton.isDisplayed();
    }

    public boolean isCancelButtonDisplayed() {
        return cancelButton.isDisplayed();
    }

    public BookHotelPage clickContinue() {
        hotelRadioButton.click();
        continueButton.click();
        return new BookHotelPage(driver); 
    }

    public SearchHotelPage clickCancel() {
        cancelButton.click();
        return new SearchHotelPage(driver);
    }
    
    public String getHotelName() {
        wait.until(driver -> !hotelName.getDomAttribute("value").isEmpty());
        return hotelName.getDomAttribute("value").trim();
    }

    public String getLocation() {
    	wait.until(driver -> !location.getDomAttribute("value").isEmpty());
        return location.getDomAttribute("value").trim();
    }

    public String getRooms() {
    	wait.until(driver -> !rooms.getDomAttribute("value").isEmpty());
        return rooms.getDomAttribute("value").trim();
    }

    public String getArrivalDate() {
    	wait.until(driver -> !arrivalDate.getDomAttribute("value").isEmpty());
        return arrivalDate.getDomAttribute("value").trim();
    }

    public String getDepartureDate() {
    	wait.until(driver -> !departureDate.getDomAttribute("value").isEmpty());
        return departureDate.getDomAttribute("value").trim();
    }

    public String getNumberOfDays() {
    	wait.until(driver -> !numberOfDays.getDomAttribute("value").isEmpty());
        return numberOfDays.getDomAttribute("value").trim();
    }

    public String getRoomType() {
    	wait.until(driver -> !roomType.getDomAttribute("value").isEmpty());
        return roomType.getDomAttribute("value").trim();
    }

    public String getPricePerNight() {
    	wait.until(driver -> !pricePerNight.getDomAttribute("value").isEmpty());
        return pricePerNight.getDomAttribute("value").trim();
    }

    public String getTotalPrice() {
    	wait.until(driver -> !totalPrice.getDomAttribute("value").isEmpty());
        return totalPrice.getDomAttribute("value").trim();
    }
}
    

