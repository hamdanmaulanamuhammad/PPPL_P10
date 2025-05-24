package pomRegister;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterUserTest {
    private WebDriver driver;
    private HomePage homePage;
    private SignupLoginPage signupLoginPage;
    private AccountInformationPage accountInformationPage;
    private AccountCreatedPage accountCreatedPage;
    private LoggedInPage loggedInPage;
    private AccountDeletedPage accountDeletedPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        signupLoginPage = new SignupLoginPage(driver);
        accountInformationPage = new AccountInformationPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        loggedInPage = new LoggedInPage(driver);
        accountDeletedPage = new AccountDeletedPage(driver);
    }

    @Test
    public void testUserRegistration() throws InterruptedException {
        // 1-2. Buka browser dan navigasi ke URL
        driver.get("http://automationexercise.com");
        Thread.sleep(1000);

        // 3. Verifikasi halaman utama terlihat
        assertTrue(homePage.isHomePageVisible(), "Halaman utama tidak terlihat");

        // 4. Klik tombol 'Signup / Login'
        homePage.clickSignupLoginButton();
        Thread.sleep(1000);

        // 5. Verifikasi teks 'New User Signup!' terlihat
        assertTrue(signupLoginPage.isNewUserSignupVisible(), "'New User Signup!' tidak terlihat");

        // 6. Masukkan nama dan email
        String name = "TestUser" + System.currentTimeMillis();
        String email = "testuser" + System.currentTimeMillis() + "@example.com";
        signupLoginPage.enterSignupDetails(name, email);

        // 7. Klik tombol Signup
        signupLoginPage.clickSignupButton();

        // 8. Verifikasi teks 'ENTER ACCOUNT INFORMATION' terlihat
        assertTrue(accountInformationPage.isAccountInfoHeaderVisible(), "'ENTER ACCOUNT INFORMATION' tidak terlihat");

        // 9-12. Mengisi detail akun
        accountInformationPage.fillAccountDetails(
                "Password123", "15", "6", "1990",
                "Test", "User", "Test Company",
                "123 Test Street", "Apartment 4B", "United States",
                "California", "Los Angeles", "90001", "1234567890"
        );

        // 13. Klik tombol Create Account
        accountInformationPage.clickCreateAccountButton();

        // 14. Verifikasi teks 'ACCOUNT CREATED!' terlihat
        assertTrue(accountCreatedPage.isAccountCreatedVisible(), "'ACCOUNT CREATED!' tidak terlihat");

        // 15. Klik tombol Continue
        accountCreatedPage.clickContinueButton();

        // Handle iklan jika ada
        try {
            driver.switchTo().frame("aswift_1");
            driver.switchTo().frame("ad_iframe");
            driver.findElement(By.id("dismiss-button")).click();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            driver.switchTo().defaultContent();
        }

        // 16. Verifikasi teks 'Logged in as username' terlihat
        assertTrue(loggedInPage.isLoggedInVisible(name), "'Logged in as username' tidak terlihat");

        // 17. Klik tombol Delete Account
        loggedInPage.clickDeleteAccountButton();

        // 18. Verifikasi teks 'ACCOUNT DELETED!' terlihat dan klik tombol Continue
        assertTrue(accountDeletedPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' tidak terlihat");
        accountDeletedPage.clickContinueButton();
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(3000);
            driver.quit();
        }
    }
}