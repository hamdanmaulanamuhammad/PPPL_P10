import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DragDrop {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testDragNDrop() throws InterruptedException{
        driver.get("https://the-internet.herokuapp.com/");

        Thread.sleep(2000); // waktu tunggu 2 detik

        driver.findElement(By.linkText("Drag and Drop")).click();

        WebElement element_A = driver.findElement(By.xpath("//div[@id='column-a']"));

        WebElement element_B = driver.findElement(By.xpath("//div[@id='column-b']"));

        Actions act = new Actions(driver);

        act.dragAndDrop(element_A, element_B).perform();

        Thread.sleep(2000);

        act.dragAndDrop(element_B, element_A).perform();

        Thread.sleep(2000);

    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(5000);
            driver.quit();
        }
    }

}
