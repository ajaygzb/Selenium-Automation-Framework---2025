package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Define elements using @FindBy annotation
    @FindBy(name = "search")
    private WebElement searchBox;

    @FindBy(id = "submit_search")
    private WebElement searchButton;

    // Constructor initializes elements using PageFactory
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10-sec timeout
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String product) {
        driver.navigate().to("https://www.automationexercise.com/products");
        
        // Wait for elements to be visible and interactable
        wait.until(ExpectedConditions.visibilityOf(searchBox)).sendKeys(product);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
}
