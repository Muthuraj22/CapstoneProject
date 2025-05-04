package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchHotelPage {

	private WebDriverWait Wait;
	private WebDriver driver;
	
	@FindBy(id = "location")  
	private WebElement LocationDropdown;
	
	@FindBy(id = "hotels")  
	private WebElement HotelDropdown;
	
	@FindBy(id = "room_type")  
	private WebElement RoomTypeDropdown;
	
	@FindBy(id = "room_nos")  
	private WebElement NumberofRoomsDropdown;
	
	@FindBy(id = "datepick_in")  
	private WebElement CheckInDate;
	
	@FindBy(id = "datepick_out")  
	private WebElement CheckOutDate;
	
	@FindBy(id = "adult_room")  
	private WebElement AdultsPerRoomDropdown;
	
	@FindBy(id = "child_room")  
	private WebElement ChildPerRoomDropdown;
	
	@FindBy(xpath = "//input[@id='Submit']")  
	private WebElement SearchButton;
	
	@FindBy(id = "location_span")  
	private WebElement errorlocation;
	
	@FindBy(id = "num_room_span")  
	private WebElement errorNumberofRooms;
	
	@FindBy(id = "checkin_span")  
	private WebElement errorEmptyCheckin;
	
	@FindBy(id = "checkout_span")  
	private WebElement errorEmptyCheckout;
	
	@FindBy(id = "adults_room_span")  
	private WebElement errorAdultsPerRoom;
	
	@FindBy(xpath = "//a[@href='BookedItinerary.php']")  
	private WebElement BookedItineraryLink;
	
	
	
	
	public SearchHotelPage(WebDriver driver) {
		this.driver = driver;
		this.Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}
	
	public SearchHotelPage selectLocationByValue(String value) {
	    Select select = new Select(LocationDropdown);
	    select.selectByValue(value);
	    return this;
	}
	
	public SearchHotelPage selectHotelByValue(String value) {
	    Select select = new Select(HotelDropdown);
	    select.selectByValue(value);
	    return this;
	}
	
	public SearchHotelPage selectRoomTypeByValue(String value) {
	    Select select = new Select(RoomTypeDropdown);
	    select.selectByValue(value);
	    return this;
	}
	public SearchHotelPage selectNumberofRoomsByValue(String value) {
	    Select select = new Select(NumberofRoomsDropdown);
	    select.selectByValue(value);
	    return this;
	}
	
	public SearchHotelPage enterCheckInDate(String checkindate) {
		CheckInDate.clear();
		CheckInDate.sendKeys(checkindate);
		return this;
	}
	
	public SearchHotelPage enterCheckOutDate(String checkoutdate) {
		CheckOutDate.clear();
		CheckOutDate.sendKeys(checkoutdate);
		return this;
	}
	
	public SearchHotelPage selectAdultsPerRoomByValue(String value) {
	    Select select = new Select(AdultsPerRoomDropdown);
	    select.selectByValue(value);
	    return this;
	}
	
	public SearchHotelPage selectChildPerRoomByValue(String value) {
	    Select select = new Select(ChildPerRoomDropdown);
	    select.selectByValue(value);
	    return this;
	}
	
	public HotelSelectPage clickSearchButton() {
		SearchButton.click();
		return new HotelSelectPage(driver);
	}
	
	public boolean isDateErrorDisplayed() {
        return errorEmptyCheckin.isDisplayed();
    }
	
	public BookedItineraryPage clickBookeditineraryLink() {
		BookedItineraryLink.click();
		return new BookedItineraryPage(driver);
	}
}
