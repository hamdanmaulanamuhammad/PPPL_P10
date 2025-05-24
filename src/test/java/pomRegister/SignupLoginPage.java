package pomRegister;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignupLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By newUserSignupText = By.xpath("//h2[text()='New User Signup!']");
    private By nameField = By.xpath("//input[@data-qa='signup-name']");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");

    // Konstruktor
    public SignupLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Verifikasi teks 'New User Signup!' terlihat
    public boolean isNewUserSignupVisible() {
        WebElement signupText = wait.until(ExpectedConditions.visibilityOfElementLocated(newUserSignupText));
        return signupText.isDisplayed();
    }

    // Mengisi nama dan email
    public void enterSignupDetails(String name, String email) throws InterruptedException {
        driver.findElement(nameField).sendKeys(name);
        Thread.sleep(500);
        driver.findElement(emailField).sendKeys(email);
        Thread.sleep(500);
    }

    // Klik tombol Signup
    public void clickSignupButton() throws InterruptedException {
        driver.findElement(signupButton).click();
        Thread.sleep(1000);
    }
}
