package pomRegister;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccountCreatedPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By accountCreatedText = By.xpath("//h2[@data-qa='account-created']/b");
    private By continueButton = By.xpath("//a[@data-qa='continue-button']");

    // Konstruktor
    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Verifikasi teks 'ACCOUNT CREATED!' terlihat
    public boolean isAccountCreatedVisible() {
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedText));
        return text.isDisplayed() && text.getText().equals("ACCOUNT CREATED!");
    }

    // Klik tombol Continue
    public void clickContinueButton() throws InterruptedException {
        driver.findElement(continueButton).click();
        Thread.sleep(2000);
    }
}