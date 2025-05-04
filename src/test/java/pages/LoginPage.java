package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class LoginPage extends ProjectSpecificationMethods{
	
	private WebDriverWait Wait;
	
	@FindBy(id = "username")
    private WebElement Username;
	
	@FindBy(id = "password")
    private WebElement Password;
	
	@FindBy(id = "login")
    private WebElement loginButton;

	@FindBy(xpath= "//td/div[@class='auth_error']")
	private WebElement loginError;
	
	@FindBy(id = "username_span")
    private WebElement UsernameErrorMsg;
	
	@FindBy(id = "password_span")
    private WebElement PasswordErrorMsg;
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}
	
	
	public boolean isLoginButtonVisible() {						//Visibility
		return loginButton.isDisplayed();
	}
	
	public boolean isLoginButtonClickable() {					//Clickability
		return loginButton.isEnabled();
	}
	
	public LoginPage enterUsername(String username) {			//Entering Username
		Username.sendKeys(username);
		return this;
	}
	
	public LoginPage enterPassword(String password) {			//Entering Password
		Password.sendKeys(password);
		return this;
	}
	
	public SearchHotelPage clickLoginButton() {					//Clicking Login Button
		loginButton.click();
		return new SearchHotelPage(driver);
	}


/*	public boolean isErrorMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOf(loginError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    } */
    


	public boolean isUsernameErrorDisplayed() {
		return driver.getPageSource().contains("Enter Username");
	}

	public boolean isPasswordErrorDisplayed() {
	    return driver.getPageSource().contains("Enter Password");
	}

	public boolean isErrorMessageDisplayed() {
	    return driver.getPageSource().contains("Invalid Login details or Your Password might have expired."); 
	}
	

	
}
