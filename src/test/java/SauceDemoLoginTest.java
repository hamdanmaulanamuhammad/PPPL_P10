import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SauceDemoLoginTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testValidLogin() throws InterruptedException {
        // Open the website
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(1000); // Pause to see initial page

        // Find login form elements
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))
        );
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Enter username
        usernameField.sendKeys("standard_user");
        Thread.sleep(1000); // Pause to see username entered

        // Enter password
        passwordField.sendKeys("secret_sauce");
        Thread.sleep(1000); // Pause to see password entered

        // Click login button
        loginButton.click();

        // Verify successful login by checking for products page
        WebElement productsHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("title"))
        );
        Thread.sleep(1000);

        // Assert that we're on the products page
        Assertions.assertEquals("Products", productsHeader.getText(),
                "Login failed: Products page not displayed");
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(3000);
            driver.quit();
        }
    }
}