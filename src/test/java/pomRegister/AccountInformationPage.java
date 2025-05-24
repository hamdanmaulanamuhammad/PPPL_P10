package pomRegister;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccountInformationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By accountInfoHeader = By.xpath("//b[text()='Enter Account Information']");
    private By titleMr = By.id("id_gender1");
    private By passwordField = By.id("password");
    private By dayDropdown = By.id("days");
    private By monthDropdown = By.id("months");
    private By yearDropdown = By.id("years");
    private By newsletterCheckbox = By.id("newsletter");
    private By offersCheckbox = By.id("optin");
    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By companyField = By.id("company");
    private By addressField = By.id("address1");
    private By address2Field = By.id("address2");
    private By countryDropdown = By.id("country");
    private By stateField = By.id("state");
    private By cityField = By.id("city");
    private By zipcodeField = By.id("zipcode");
    private By mobileNumberField = By.id("mobile_number");
    private By createAccountButton = By.xpath("//button[text()='Create Account']");

    // Konstruktor
    public AccountInformationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Verifikasi header 'Enter Account Information' terlihat
    public boolean isAccountInfoHeaderVisible() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(accountInfoHeader));
        return header.isDisplayed();
    }

    // Mengisi semua detail akun
    public void fillAccountDetails(String password, String day, String month, String year,
                                   String firstName, String lastName, String company,
                                   String address, String address2, String country,
                                   String state, String city, String zipcode, String mobileNumber) throws InterruptedException {
        // Pilih judul
        driver.findElement(titleMr).click();
        Thread.sleep(500);

        // Kata sandi
        driver.findElement(passwordField).sendKeys(password);
        Thread.sleep(500);

        // Tanggal lahir
        new Select(driver.findElement(dayDropdown)).selectByValue(day);
        Thread.sleep(500);
        new Select(driver.findElement(monthDropdown)).selectByValue(month);
        Thread.sleep(500);
        new Select(driver.findElement(yearDropdown)).selectByValue(year);
        Thread.sleep(500);

        // Newsletter checkbox
        driver.findElement(newsletterCheckbox).click();
        Thread.sleep(500);

        // Offers checkbox
        try {
            driver.switchTo().frame("aswift_3");
            driver.switchTo().frame("ad_iframe");
            driver.findElement(By.id("dismiss-button")).click();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            driver.switchTo().defaultContent();
        }
        WebElement offers = wait.until(ExpectedConditions.presenceOfElementLocated(offersCheckbox));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", offers);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", offers);

        // Detail lainnya
        driver.findElement(firstNameField).sendKeys(firstName);
        Thread.sleep(300);
        driver.findElement(lastNameField).sendKeys(lastName);
        Thread.sleep(300);
        driver.findElement(companyField).sendKeys(company);
        Thread.sleep(300);
        driver.findElement(addressField).sendKeys(address);
        Thread.sleep(300);
        driver.findElement(address2Field).sendKeys(address2);
        Thread.sleep(300);
        new Select(driver.findElement(countryDropdown)).selectByVisibleText(country);
        Thread.sleep(300);
        driver.findElement(stateField).sendKeys(state);
        Thread.sleep(300);
        driver.findElement(cityField).sendKeys(city);
        Thread.sleep(300);
        driver.findElement(zipcodeField).sendKeys(zipcode);
        Thread.sleep(300);
        driver.findElement(mobileNumberField).sendKeys(mobileNumber);
        Thread.sleep(500);
    }

    // Klik tombol Create Account
    public void clickCreateAccountButton() throws InterruptedException {
        driver.findElement(createAccountButton).click();
        Thread.sleep(2000);
    }
}
