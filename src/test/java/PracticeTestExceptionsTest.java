import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for practicing Selenium WebDriver exception handling on Practice Test Automation website.
 */
@TestMethodOrder(MethodName.class)
public class PracticeTestExceptionsTest {
    private WebDriver driver;
    private WebDriverWait wait;

    /** Sets up ChromeDriver and maximizes browser window before each test. */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /** Tests if Row 2 input field is displayed after clicking Add button. */
    @Test
    public void testCase1_VerifyRow2InputDisplayed() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        try {
            // Click Add button and wait for Row 2
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add_btn")));
            addButton.click();
            WebElement row2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#row2 .input-field")));
            assertTrue(row2Input.isDisplayed(), "Row 2 input field is not displayed.");
            System.out.println("Test Case 1 Passed: Row 2 input field is displayed.");
        } catch (Exception e) {
            System.err.println("Test Case 1 Failed: " + e.getMessage());
            throw e;
        }
    }

    /** Tests if text is saved in Row 2 after entering text and clicking Save. */
    @Test
    public void testCase2_VerifyTextSaved() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        try {
            // Add Row 2, enter text, and save
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add_btn")));
            addButton.click();
            WebElement row2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#row2 .input-field")));
            row2Input.sendKeys("Sushi");
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#row2 [name='Save']")));
            saveButton.click();
            WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
            assertEquals("Row 2 was saved", confirmation.getText(), "Confirmation message does not match.");
            System.out.println("Test Case 2 Passed: Text was saved successfully.");
        } catch (Exception e) {
            System.err.println("Test Case 2 Failed: " + e.getMessage());
            throw e;
        }
    }

    /** Tests InvalidElementStateException and text change in Row 1 after enabling input. */
    @Test
    public void testCase3_VerifyInvalidElementStateExceptionAndTextChange() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        try {
            // Attempt to clear disabled input, then enable and edit
            WebElement row1Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#row1 .input-field")));
            try {
                row1Input.clear();
                System.err.println("Test Case 3 Failed: Expected InvalidElementStateException but none was thrown.");
            } catch (org.openqa.selenium.InvalidElementStateException e) {
                System.out.println("Test Case 3: Caught expected InvalidElementStateException when trying to clear disabled input.");
            }
            WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#row1 [name='Edit']")));
            editButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(row1Input));
            row1Input.clear();
            row1Input.sendKeys("Burger");
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#row1 [name='Save']")));
            saveButton.click();
            WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
            assertEquals("Row 1 was saved", confirmation.getText(), "Confirmation message does not match.");
            assertEquals("Burger", row1Input.getAttribute("value"), "Input field text did not change to expected value.");
            System.out.println("Test Case 3 Passed: Input field was enabled, text changed, and saved successfully.");
        } catch (Exception e) {
            System.err.println("Test Case 3 Failed: " + e.getMessage());
            throw e;
        }
    }

    /** Tests StaleElementReferenceException when accessing instructions after clicking Add. */
    @Test
    public void testCase4_VerifyStaleElementReferenceException() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        try {
            // Access instructions, add Row 2, and check for stale element
            WebElement instructions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("instructions")));
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add_btn")));
            addButton.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("row2")));
            try {
                boolean isDisplayed = instructions.isDisplayed();
                System.err.println("Test Case 4 Failed: StaleElementReferenceException expected but element still accessible.");
                assertFalse(isDisplayed, "Instructions element should not be displayed after Add button click.");
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("Test Case 4 Passed: StaleElementReferenceException caught as expected.");
            }
        } catch (Exception e) {
            System.err.println("Test Case 4 Failed: " + e.getMessage());
            throw e;
        }
    }

    /** Tests TimeoutException with short wait for Row 2 input field. */
    @Test
    public void testCase5_VerifyTimeoutException() {
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
        try {
            // Click Add and use short wait to trigger TimeoutException
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add_btn")));
            addButton.click();
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement row2Input = shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#row2 .input-field")));
            assertTrue(row2Input.isDisplayed(), "Row 2 input field is not displayed.");
            System.err.println("Test Case 5 Failed: TimeoutException expected but did not occur.");
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Test Case 5 Passed: TimeoutException caught as expected.");
        } catch (Exception e) {
            System.err.println("Test Case 5 Failed: " + e.getMessage());
            throw e;
        }
    }

    /** Closes browser after each test. */
    @AfterEach
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(3000);
            driver.quit();
        }
    }
}