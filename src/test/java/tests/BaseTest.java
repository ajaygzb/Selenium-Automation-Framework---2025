package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeClass
    public void setup() {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Open browser maximized
        options.addArguments("--disable-notifications"); // Disable pop-up notifications
        driver.set(new ChromeDriver(options));

        // Navigate to the website
        getDriver().get("https://www.automationexercise.com/login");
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterClass
    public void teardown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        return new Object[][] {
            {"ajaygzb10@gmail.com", "password", "Blue Top"}
            ,{"ajaygzb10@@gmail.com", "password", "Men Tshirt"}
        };
    }
}
