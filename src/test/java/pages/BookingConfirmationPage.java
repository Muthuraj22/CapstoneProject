package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class BookingConfirmationPage extends ProjectSpecificationMethods{
	
	private WebDriverWait wait;
	
	 @FindBy(id = "hotel_name")
	    WebElement hotelName;

	    @FindBy(id = "location")
	    WebElement location;

	    @FindBy(id = "room_type")
	    WebElement roomType;

	    @FindBy(id = "arrival_date")
	    WebElement arrivalDate;

	    @FindBy(id = "departure_text")
	    WebElement departureDate;

	    @FindBy(id = "total_rooms")
	    WebElement rooms;

	    @FindBy(id = "adults_room")
	    WebElement adultsPerRoom;

	    @FindBy(id = "children_room")
	    WebElement childrenPerRoom;

	    @FindBy(id = "price_night")
	    WebElement pricePerNight;

	    @FindBy(id = "total_price")
	    WebElement totalPrice;

	    @FindBy(id = "gst")
	    WebElement gst;

	    @FindBy(id = "final_price")
	    WebElement finalBilledPrice;

	    @FindBy(id = "first_name")
	    WebElement firstName;

	    @FindBy(id = "last_name")
	    WebElement lastName;

	    @FindBy(id = "address")
	    WebElement billingAddress;
	    
	    @FindBy(id = "search_hotel")
	    WebElement searchHotelButton;
	    
	    @FindBy(id = "my_itinerary")
	    WebElement myItineraryButton;
	    
	    @FindBy(id = "logout")
	    WebElement LogoutButton;
	
	
	
	public BookingConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
	
	 public String getHotelName() {
	        return hotelName.getDomAttribute("value");
	    }

	    public String getLocation() {
	        return location.getDomAttribute("value");
	    }

	    public String getRoomType() {
	        return roomType.getDomAttribute("value");
	    }

	    public String getArrivalDate() {
	        return arrivalDate.getDomAttribute("value");
	    }

	    public String getDepartureDate() {
	        return departureDate.getDomAttribute("value");
	    }

	    public String getRooms() {
	        return rooms.getDomAttribute("value");
	    }

	    public String getAdultsPerRoom() {
	        return adultsPerRoom.getDomAttribute("value");
	    }

	    public String getChildrenPerRoom() {
	        return childrenPerRoom.getDomAttribute("value");
	    }

	    public String getPricePerNight() {
	        return pricePerNight.getDomAttribute("value");
	    }

	    public String getTotalPrice() {
	        return totalPrice.getDomAttribute("value");
	    }

	    public String getGST() {
	        return gst.getDomAttribute("value");
	    }

	    public String getFinalBilledPrice() {
	        return finalBilledPrice.getDomAttribute("value");
	    }

	    public String getFirstName() {
	        return firstName.getDomAttribute("value");
	    }

	    public String getLastName() {
	        return lastName.getDomAttribute("value");
	    }

	    public String getBillingAddress() {
	        return billingAddress.getDomAttribute("value");
	    }

	    public SearchHotelPage clickSearchHotelButton() {
	    	searchHotelButton.click();
	    	return new SearchHotelPage(driver);
	    }

		public BookedItineraryPage clickMyItineraryButton() {
			myItineraryButton.click();
			return new BookedItineraryPage(driver);	
		}

		public void clickLogoutButton() {
			LogoutButton.click();
			
		}
}
