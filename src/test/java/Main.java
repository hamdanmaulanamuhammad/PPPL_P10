import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testBingSearch() throws InterruptedException {
        // Buka situs Bing
        driver.get("https://www.bing.com/");

        // Tunggu sederhana (bukan best practice)
        Thread.sleep(2000); // waktu tunggu 2 detik

        // Interaksi langsung dengan search bar
        WebElement searchBar = driver.findElement(By.id("sb_form_q"));
        searchBar.sendKeys("ninis");
        searchBar.sendKeys(Keys.ENTER);

        // Tunggu hasil pencarian muncul (pakai judul halaman)
        Thread.sleep(2000);
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains("ninis"));
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
//            driver.quit();
        }
    }
}
