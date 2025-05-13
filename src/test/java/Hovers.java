import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Hovers {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testHover() throws InterruptedException{
        driver.get("https://the-internet.herokuapp.com/");

        Thread.sleep(2000); // waktu tunggu 2 detik

        driver.findElement(By.linkText("Hovers")).click();

        List<WebElement> users = driver.findElements(By.xpath("//img[@alt='User Avatar']"));

        Actions act = new Actions(driver);

        for(WebElement user:users) {

            act.moveToElement(user).perform();

            Thread.sleep(1000);
        }


    }
    @AfterEach
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(5000);
            driver.quit(); // aktifkan jika ingin browser langsung ditutup
        }
    }

}
