package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // Define elements using @FindBy annotation
    @FindBy(xpath = "//a[text()='Add to cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a[text()='Proceed To Checkout']")
    private WebElement checkoutButton;

    @FindBy(linkText = "View Cart")
    private WebElement viewCartButton;

    // Constructor initializes elements using PageFactory
    public CartPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10-sec timeout
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        
        // Scroll into view to prevent interception
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", addToCartButton);
        
        addToCartButton.click();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}
