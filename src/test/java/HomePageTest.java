import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Author: mohamed.khattab
 * Time:   7/2/2020
 * Year:   2020
 * URL:
 */
public class HomePageTest {
     WebDriver driver;
    String baseUrl = "http://demo.nopcommerce.com/";
    String chromeDriverDir = "/drivers/chrome/windows/chromedriver.exe";

    /**
     * //declare driver ==>chrome
     * //selenium maven
     * // chrome driver
     * //webdriver manager
     * //**************
     * //Navigate  //
     * //inspect Elements
     * //action on Elements
     */
    @BeforeClass
    void tearUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + chromeDriverDir);
//        driver = new ChromeDriver();
//        WebDriverManager.chromedriver().version("").setup();
//        WebDriverManager.chromedriver().version("").setup();
        //WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.navigate().to(baseUrl);

    }

    @Test
    void userNavigateSuccessfullyTest() throws InterruptedException {
        String currentUrl=driver.getCurrentUrl();
        Assert.assertEquals(baseUrl,currentUrl);
        System.out.println(currentUrl);
    Faker fakeData=new Faker();

        //Log in
       // WebElement loginBtn=driver.findElement(By.linkText("Log in"));
       // loginBtn.click();

        WebElement registerBtn=driver.findElement(By.className("ico-register"));
        registerBtn.click();

        WebElement genderBtn=driver.findElement(By.id("gender-male"));
        genderBtn.click();

        WebElement firstNameBtn=driver.findElement(By.name("FirstName"));
        firstNameBtn.sendKeys("Mohamed");

        WebElement lastNameBtn=driver.findElement(By.name("LastName"));
        lastNameBtn.sendKeys("Khattab");
        String randomEmail=fakeData.name().firstName();
        randomEmail=randomEmail+"@test.com";
        WebElement emailText=driver.findElement(By.name("Email"));

        emailText.sendKeys(randomEmail);
        Select dayDropDown= new Select(driver.findElement(By.name("DateOfBirthDay")));
        dayDropDown.selectByVisibleText("9");


        Select monthDropDown= new Select(driver.findElement(By.name("DateOfBirthMonth")));
        monthDropDown.selectByIndex(5);

           WebElement optionList=driver.findElement(By.name("DateOfBirthYear"));
           Select selectedOptions = new Select(optionList);
           selectedOptions.selectByVisibleText("1990");
//           selectedOptions.selectByValue("1");
//        Select yearDropDown= new Select(driver.findElement(By.name("DateOfBirthDay")));
//        yearDropDown.selectByIndex(5);


      //  Thread.sleep(5000);

        WebElement pass=driver.findElement(By.name("Password"));
        pass.sendKeys("12345678");

        WebElement repeatedPass=driver.findElement(By.name("ConfirmPassword"));
        repeatedPass.sendKeys("12345678");

        WebElement registerSubmit=driver.findElement(By.id("register-button"));
        registerSubmit.click();

//        WebDriverWait explictWait= new WebDriverWait(driver,20);
        WebElement contbtn=driver.findElement(By.name("register-continue"));
//        contbtn.click();
     //   contbtn=explictWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("register-continue")));
         //explictWait.until(ExpectedConditions.visibilityOf(contbtn)).click();

        Wait<WebDriver> fluentWait= new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

         fluentWait.until(ExpectedConditions.visibilityOf(contbtn)).click();


        //waiting
        //implict wait
        // 20 second
        //error

        // wait all of the elements 200ms
        // Explict wait
        // specific element 200ms


    }

    @AfterClass
    void tearDown() {
     //   driver.quit();
    }
}
