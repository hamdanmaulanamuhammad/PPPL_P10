package pomRegister;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccountDeletedPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By accountDeletedText = By.xpath("//h2[@data-qa='account-deleted']/b");
    private By continueButton = By.xpath("//a[@data-qa='continue-button']");

    // Konstruktor
    public AccountDeletedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Verifikasi teks 'ACCOUNT DELETED!' terlihat
    public boolean isAccountDeletedVisible() {
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(accountDeletedText));
        return text.isDisplayed() && text.getText().equals("ACCOUNT DELETED!");
    }

    // Klik tombol Continue
    public void clickContinueButton() throws InterruptedException {
        driver.findElement(continueButton).click();
        Thread.sleep(1000);
    }
}