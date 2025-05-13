import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterUserTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testUserRegistration() throws InterruptedException {
        // 1-2. Launch browser and navigate to URL
        driver.get("http://automationexercise.com");
        Thread.sleep(1000);

        // 3. Verify that home page is visible successfully
        WebElement homeSlider = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("slider"))
        );
        Assertions.assertTrue(homeSlider.isDisplayed(), "Home page is not visible");

        // 4. Click on 'Signup / Login' button
        WebElement signupLoginButton = driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]"));
        signupLoginButton.click();
        Thread.sleep(1000);

        // 5. Verify 'New User Signup!' is visible
        WebElement newUserSignupText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New User Signup!']"))
        );
        Assertions.assertTrue(newUserSignupText.isDisplayed(), "'New User Signup!' is not visible");

        // 6. Enter name and email address
        String name = "TestUser" + System.currentTimeMillis();
        String email = "testuser" + System.currentTimeMillis() + "@example.com";

        WebElement nameField = driver.findElement(By.xpath("//input[@data-qa='signup-name']"));
        WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='signup-email']"));

        nameField.sendKeys(name);
        Thread.sleep(500);
        emailField.sendKeys(email);
        Thread.sleep(500);

        // 7. Click 'Signup' button
        WebElement signupButton = driver.findElement(By.xpath("//button[@data-qa='signup-button']"));
        signupButton.click();
        Thread.sleep(1000);

        // 8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        WebElement accountInfoHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Enter Account Information']"))
        );
        Assertions.assertTrue(accountInfoHeader.isDisplayed(), "'ENTER ACCOUNT INFORMATION' is not visible");

        // 9. Fill details: Title, Name, Email, Password, Date of birth
        // Select title
        WebElement titleMr = driver.findElement(By.id("id_gender1"));
        titleMr.click();
        Thread.sleep(500);

        // Password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Password123");
        Thread.sleep(500);

        // Date of birth
        Select dayDropdown = new Select(driver.findElement(By.id("days")));
        dayDropdown.selectByValue("15");
        Thread.sleep(500);

        Select monthDropdown = new Select(driver.findElement(By.id("months")));
        monthDropdown.selectByValue("6");
        Thread.sleep(500);

        Select yearDropdown = new Select(driver.findElement(By.id("years")));
        yearDropdown.selectByValue("1990");
        Thread.sleep(500);

        // 10. Select checkbox 'Sign up for our newsletter!'
        WebElement newsletterCheckbox = driver.findElement(By.id("newsletter"));
        newsletterCheckbox.click();
        Thread.sleep(500);

        // 11. Select checkbox 'Receive special offers from our partners!'
        try {
            // Coba tutup iklan jika ada
            driver.switchTo().frame("aswift_3");
            driver.switchTo().frame("ad_iframe");
            driver.findElement(By.id("dismiss-button")).click();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            driver.switchTo().defaultContent();
        }

        WebElement offersCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("optin")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", offersCheckbox);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", offersCheckbox);

        // 12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        WebElement firstNameField = driver.findElement(By.id("first_name"));
        firstNameField.sendKeys("Test");
        Thread.sleep(300);

        WebElement lastNameField = driver.findElement(By.id("last_name"));
        lastNameField.sendKeys("User");
        Thread.sleep(300);

        WebElement companyField = driver.findElement(By.id("company"));
        companyField.sendKeys("Test Company");
        Thread.sleep(300);

        WebElement addressField = driver.findElement(By.id("address1"));
        addressField.sendKeys("123 Test Street");
        Thread.sleep(300);

        WebElement address2Field = driver.findElement(By.id("address2"));
        address2Field.sendKeys("Apartment 4B");
        Thread.sleep(300);

        Select countryDropdown = new Select(driver.findElement(By.id("country")));
        countryDropdown.selectByVisibleText("United States");
        Thread.sleep(300);

        WebElement stateField = driver.findElement(By.id("state"));
        stateField.sendKeys("California");
        Thread.sleep(300);

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Los Angeles");
        Thread.sleep(300);

        WebElement zipcodeField = driver.findElement(By.id("zipcode"));
        zipcodeField.sendKeys("90001");
        Thread.sleep(300);

        WebElement mobileNumberField = driver.findElement(By.id("mobile_number"));
        mobileNumberField.sendKeys("1234567890");
        Thread.sleep(500);

        // 13. Click 'Create Account button'
        WebElement createAccountButton = driver.findElement(By.xpath("//button[text()='Create Account']"));
        createAccountButton.click();
        Thread.sleep(2000);

        // 14. Verify that 'ACCOUNT CREATED!' is visible
        WebElement accountCreatedText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@data-qa='account-created']/b"))
        );
        Assertions.assertTrue(accountCreatedText.isDisplayed() &&
                        accountCreatedText.getText().equals("ACCOUNT CREATED!"),
                "'ACCOUNT CREATED!' is not visible");

        // 15. Click 'Continue' button
        WebElement continueButton = driver.findElement(By.xpath("//a[@data-qa='continue-button']"));
        continueButton.click();
        Thread.sleep(2000);

        // Handle potential ad popup
        try {
            driver.switchTo().frame("aswift_1");
            driver.switchTo().frame("ad_iframe");
            driver.findElement(By.id("dismiss-button")).click();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            // No ad or unable to handle it, continue with test
            driver.switchTo().defaultContent();
        }

        // 16. Verify that 'Logged in as username' is visible
        WebElement loggedInText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Logged in as')]"))
        );
        Assertions.assertTrue(loggedInText.isDisplayed() &&
                        loggedInText.getText().contains(name),
                "'Logged in as username' is not visible");

        // 17. Click 'Delete Account' button
        WebElement deleteAccountButton = driver.findElement(By.xpath("//a[contains(text(), 'Delete Account')]"));
        deleteAccountButton.click();
        Thread.sleep(2000);

        // 18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        WebElement accountDeletedText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@data-qa='account-deleted']/b"))
        );
        Assertions.assertTrue(accountDeletedText.isDisplayed() &&
                        accountDeletedText.getText().equals("ACCOUNT DELETED!"),
                "'ACCOUNT DELETED!' is not visible");

        WebElement finalContinueButton = driver.findElement(By.xpath("//a[@data-qa='continue-button']"));
        finalContinueButton.click();
        Thread.sleep(1000);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(3000); // Pause before closing browser
            driver.quit();
        }
    }
}