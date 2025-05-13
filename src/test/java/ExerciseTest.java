import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ExerciseTest {
    @Test
    public void registerTest(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        Assertions.assertEquals("Automation Exercise", driver.getTitle());

        WebElement sign = driver.findElement(By.xpath("//a[@href='/login']"));

        Actions action = new Actions(driver);

        sign.click();
        Assertions.assertEquals("Automation Exercise - Signup / Login", driver.getTitle());

        WebElement usersign = driver.findElement(By.xpath("//input[@data-qa='signup-name']"));

        WebElement emailsign = driver.findElement(By.xpath("//input[@data-qa='signup-email']"));
        WebElement form = driver.findElement(By.xpath("//form[@action='/signup']"));

        usersign.sendKeys("muantap");
        emailsign.sendKeys("akujelasbuanget2@mail.com");

        form.submit();

        Assertions.assertEquals("Automation Exercise - Signup", driver.getTitle());

        WebElement lanang = driver.findElement(By.xpath("//label[@for='id_gender1']"));
        lanang.click();

        WebElement pass = driver.findElement(By.xpath("//input[@type='password']"));
        pass.sendKeys("wokeking123");

        WebElement limolas = driver.findElement(By.xpath("//select[@data-qa='days']//option[@value='15']"));
        limolas.click();
        WebElement february = driver.findElement(By.xpath("//select[@data-qa='months']//option[@value='2']"));
        february.click();
        WebElement songonem = driver.findElement(By.xpath("//select[@data-qa='years']//option[@value='1996']"));
        songonem.click();
        WebElement firstname = driver.findElement(By.id("first_name"));
        firstname.sendKeys("muantap");
        WebElement lastname = driver.findElement(By.id("last_name"));
        lastname.sendKeys("buanget");
        WebElement company = driver.findElement(By.id("company"));
        company.sendKeys("PT. fdsjkla");
        WebElement address = driver.findElement(By.id("address1"));
        address.sendKeys("Jl. Merapi No. 100, P.O. Box 12345, PT. XYZ Corporation");
        WebElement address2 = driver.findElement(By.id("address2"));
        address2.sendKeys("Jl. Monjali No. 100, P.O. Box 12345, PT. XYZ Corporation");
        WebElement israel = driver.findElement(By.xpath("//select[@data-qa='country']//option[@value='Israel']"));
        israel.click();
        WebElement state = driver.findElement(By.id("state"));
        state.sendKeys("Tel-aviv");
        WebElement city = driver.findElement(By.id("city"));
        city.sendKeys("Sleman");
        WebElement zipcode = driver.findElement(By.id("zipcode"));
        zipcode.sendKeys("122456456");
        WebElement mobile = driver.findElement(By.id("mobile_number"));
        mobile.sendKeys("121235486482558");
        WebElement submit = driver.findElement(By.xpath("//button[@data-qa='create-account']"));
        submit.click();

        Assertions.assertEquals("Automation Exercise - Account Created", driver.getTitle());

        WebElement continuebtn = driver.findElement(By.xpath("//a[@data-qa='continue-button']"));
        continuebtn.click();

        Assertions.assertTrue(driver.getPageSource().contains("Logged in as"));

        WebElement deleteacc = driver.findElement(By.xpath("//a[@href='/delete_account']"));
        deleteacc.click();

        WebElement ctnbtn = driver.findElement(By.xpath("//a[@data-qa='continue-button']"));
        ctnbtn.click();

    }
}