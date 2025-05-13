import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class KeyPress {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testKeyPresses() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/");

        // Click on Key Presses Link
        driver.findElement(By.linkText("Key Presses")).click();

        // Ambil elemen input atau body halaman untuk mengirim key
        WebElement inputField = driver.findElement(By.id("target"));

        // Ketik H
        inputField.sendKeys("H");
        Thread.sleep(1000);

        // Ketik M
        inputField.sendKeys("M");
        Thread.sleep(1000);

        // Ketik A
        inputField.sendKeys("A");
        Thread.sleep(1000);

        // Coba juga dengan special keys
        inputField.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);

        inputField.sendKeys(Keys.TAB);
        Thread.sleep(1000);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(5000);
            driver.quit();
        }
    }
}