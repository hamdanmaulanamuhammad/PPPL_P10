package pomRegister;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By slider = By.id("slider");
    private By signupLoginButton = By.xpath("//a[contains(text(),'Signup / Login')]");

    // Konstruktor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Verifikasi halaman utama terlihat
    public boolean isHomePageVisible() {
        WebElement homeSlider = wait.until(ExpectedConditions.visibilityOfElementLocated(slider));
        return homeSlider.isDisplayed();
    }

    // Klik tombol Signup/Login
    public void clickSignupLoginButton() {
        driver.findElement(signupLoginButton).click();
    }
}
