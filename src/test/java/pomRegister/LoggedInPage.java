package pomRegister;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoggedInPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By loggedInText = By.xpath("//a[contains(text(), 'Logged in as')]");
    private By deleteAccountButton = By.xpath("//a[contains(text(), 'Delete Account')]");

    // Konstruktor
    public LoggedInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Verifikasi teks 'Logged in as username' terlihat
    public boolean isLoggedInVisible(String username) {
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInText));
        return text.isDisplayed() && text.getText().contains(username);
    }

    // Klik tombol Delete Account
    public void clickDeleteAccountButton() throws InterruptedException {
        driver.findElement(deleteAccountButton).click();
        Thread.sleep(2000);
    }
}