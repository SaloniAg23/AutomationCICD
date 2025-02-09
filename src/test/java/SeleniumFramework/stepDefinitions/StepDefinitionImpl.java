package SeleniumFramework.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFramework.TestComponents.BaseTest;
import SeleniumFramework.pageobjects.CartPage;
import SeleniumFramework.pageobjects.CheckOutPage;
import SeleniumFramework.pageobjects.LandingPage;
import SeleniumFramework.pageobjects.OrderConfirmationPage;
import SeleniumFramework.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogue  productCatalogue;
	public CartPage cartPage;
	public OrderConfirmationPage orderConfirmationPage ;
	
	@Given("I landed on Ecommerce Website")
	public void i_landed_on_ecommerce_website() throws IOException {
		
		landingPage = launchApplication();
	}
	
	@Given("^I want to login with username (.+) and password (.+)$")
	public void i_want_to_login_with_username_and_password(String username , String password) {
		
		  productCatalogue = landingPage.loginApplication(username,password);
	}
	
	@When("^I want to add (.+) to cart$")
	public void i_want_to_add_product_to_cart(String productName) {
		
		List <WebElement> products = productCatalogue.getProducts();
		productCatalogue.addProductToCart(productName);
		
	}
	
	@When("^Checkout the (.+) and submit order$")
	public void checkout_product_and_submit_order(String productName) {
		
		cartPage =productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutPage =cartPage.goToCheckout();
		checkoutPage.getCountry("india");
		orderConfirmationPage = checkoutPage.placeOrderButton();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void verify_confirmation_message(String string) {
		
		String confirmMessage = orderConfirmationPage.confirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		System.out.println("Assertion is correct");
	}
	
	@When("^Logged in  with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username , String password) {
		
		productCatalogue = landingPage.loginApplication(username,password);
	}
	
	@Then("{string} message is displayed")
	public void error_msg_is_displayed(String string) {
		
		Assert.assertEquals(string, landingPage.getErrorMessage());
	}
}
