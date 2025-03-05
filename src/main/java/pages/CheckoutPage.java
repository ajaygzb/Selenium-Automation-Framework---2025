package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // Locate the "Place Order" button using @FindBy annotation
    @FindBy(xpath = "//a[text()='Place Order']")
    private WebElement placeOrderButton;

    // Constructor initializes elements using PageFactory
    public CheckoutPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10-sec timeout
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void placeOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));

        // Scroll into view to prevent click interception
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", placeOrderButton);

        placeOrderButton.click();
    }
}
