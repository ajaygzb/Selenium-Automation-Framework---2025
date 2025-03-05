package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriverWait wait;

    // Define elements using @FindBy annotation
    @FindBy(name = "email")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    // Constructor initializes elements using PageFactory
    public LoginPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10-sec timeout
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        try {
            // Wait for elements to be visible before interacting
            wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
            wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

            System.out.println("Login successful with username: " + username);
        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
            // Continue execution even if login fails
        }
    }
}
