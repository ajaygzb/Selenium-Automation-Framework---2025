// EcommerceTest.java (Updated to use DataProvider and Report Generation)
package tests;

import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchPage;
import pages.CartPage;
import pages.CheckoutPage;

public class EcommerceTest extends BaseTest {
    
    @Test(dataProvider = "userData")
    public void testEcommerceFlow(String username, String password, String product) {
        LoginPage loginPage = new LoginPage(getDriver());
        SearchPage searchPage = new SearchPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        
        loginPage.login(username, password);
        searchPage.searchProduct(product);
        cartPage.addToCart();
        cartPage.proceedToCheckout();
        checkoutPage.placeOrder();
    }
}
