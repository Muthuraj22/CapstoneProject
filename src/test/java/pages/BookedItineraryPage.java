package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class BookedItineraryPage extends ProjectSpecificationMethods{
	
	private WebDriverWait wait;
	
	 @FindBy(id = "order_id_text")
	 WebElement searchBar;
	 
	 @FindBy(id = "search_hotel_id")
	 WebElement goButton;

	 @FindBy(xpath = "//input[@value='Cancel Selected']")
	 WebElement CancelSelectedButton;
	 
	
	
	
	
	
	public BookedItineraryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void enterInSearchBar(String order) {
		searchBar.clear();
		searchBar.sendKeys(order);
	}
	
	public void clickGoButton() {
		goButton.click();
	}
	
	public boolean isCancelSelectedButtonVisible() {
        return CancelSelectedButton.isDisplayed();
    }
	
	public BookedItineraryPage clickCancelSelectedButton() {
		CancelSelectedButton.click();
		return this;
	}
}
