package tests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.ProjectSpecificationMethods;
import pages.BookHotelPage;
import pages.HotelSelectPage;
import pages.LoginPage;
import pages.SearchHotelPage;

public class TC012_BookWithEmptyField extends ProjectSpecificationMethods {

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
            .enterCheckInDate("20/04/2025")
            .enterCheckOutDate("22/04/2025")
            .selectAdultsPerRoomByValue("2")
            .selectChildPerRoomByValue("0")
            .clickSearchButton();

        new HotelSelectPage(driver).clickContinue();
    }

    @DataProvider(name = "invalidBookingData")
    public Object[][] invalidBookingData() {
        return new Object[][] {
            {"", "", "", "", "", "", "", "",
             "Please Enter your First Name", "Please Enter you Last Name", "Please Enter your Address",
             "Please Enter your 16 Digit Credit Card Number", "Please Select your Credit Card Type",
             "Please Select your Credit Card Expiry Month", "Please Enter your Credit Card CVV Number"},

            {"Muthu", "", "", "", "", "", "", "",
             "", "Please Enter you Last Name", "Please Enter your Address",
             "Please Enter your 16 Digit Credit Card Number", "Please Select your Credit Card Type",
             "Please Select your Credit Card Expiry Month", "Please Enter your Credit Card CVV Number"},

            {"", "Raj", "", "", "", "", "", "",
             "Please Enter your First Name", "", "Please Enter your Address",
             "Please Enter your 16 Digit Credit Card Number", "Please Select your Credit Card Type",
             "Please Select your Credit Card Expiry Month", "Please Enter your Credit Card CVV Number"},

            {"Muthu", "Raj", "", "", "", "", "", "",
             "", "", "Please Enter your Address",
             "Please Enter your 16 Digit Credit Card Number", "Please Select your Credit Card Type",
             "Please Select your Credit Card Expiry Month", "Please Enter your Credit Card CVV Number"},

            {"Muthu", "Raj", "Chennai", "", "VISA", "April", "2026", "",
             "", "", "", "Please Enter your 16 Digit Credit Card Number", "",
             "", "Please Enter your Credit Card CVV Number"},

            {"Muthu", "Raj", "Chennai", "4111111111111111", "", "April", "2026", "123",
             "", "", "", "", "Please Select your Credit Card Type",
             "", ""}, 

            {"Muthu", "Raj", "Chennai", "4111111111111111", "VISA", "", "2026", "123",
             "", "", "", "", "", "Please Select your Credit Card Expiry Month", ""},

            {"Muthu", "Raj", "Chennai", "4111111111111111", "VISA", "April", "2026", "",
             "", "", "", "", "", "", "Please Enter your Credit Card CVV Number"},

            {"Muthu", "Raj", "Chennai", "4111111111111111", "VISA", "April", "", "123",
             "", "", "", "", "", "", ""},

            {"Muthu", "Raj", "", "4111111111111111", "VISA", "April", "2026", "123",
             "", "", "Please Enter your Address", "", "", "", ""}  
        };  
    }

    @Test(dataProvider = "invalidBookingData")
    public void validateEmptyBookingFields(
        String firstName, String lastName, String address,
        String ccNum, String ccType, String expMonth, String expYear, String cvv,
        String expFirstNameError, String expLastNameError, String expAddressError,
        String expCCNumError, String expCCTypeError, String expExpMonthError, String expCVVError) {

        BookHotelPage obj = new BookHotelPage(driver);

        obj.enterFirstName(firstName)
            .enterLastName(lastName)
            .enterBillingAddress(address)
            .enterCreditCardNumber(ccNum)
            .selectCreditCardType(ccType)
            .selectExpiryMonth(expMonth)
            .selectExpiryYear(expYear)
            .enterCVV(cvv)
            .clickBookNow();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(obj.getFirstNameError(), expFirstNameError);
        softAssert.assertEquals(obj.getLastNameError(), expLastNameError);
        softAssert.assertEquals(obj.getAddressError(), expAddressError);
        softAssert.assertTrue(obj.getCreditCardNumberError().contains(expCCNumError));
        softAssert.assertEquals(obj.getCreditCardTypeError(), expCCTypeError);
        softAssert.assertEquals(obj.getExpiryMonthError(), expExpMonthError);
        softAssert.assertEquals(obj.getCVVError(), expCVVError);

        softAssert.assertAll();
        System.out.println("TC012(Pass) - User unable to book with empty fields");
    }
}
