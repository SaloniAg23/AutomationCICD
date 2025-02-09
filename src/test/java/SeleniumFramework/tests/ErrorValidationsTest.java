package SeleniumFramework.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import SeleniumFramework.TestComponents.BaseTest;
import SeleniumFramework.pageobjects.CartPage;
import SeleniumFramework.pageobjects.CheckOutPage;
import SeleniumFramework.pageobjects.LandingPage;
import SeleniumFramework.pageobjects.OrderConfirmationPage;
import SeleniumFramework.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import SeleniumFramework.TestComponents.RetryTest;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorChecks"},retryAnalyzer=RetryTest.class)
	public void LoginErrorValidation() throws IOException {
	
        landingPage.loginApplication("saloni@gmail.com","DurgaMaa12@");
		//Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());
	}
	
		@Test
		public void submitOrderTesting() throws IOException {
		
			String ProductToAdd = "qwerty";		
	        ProductCatalogue  productCatalogue = landingPage.loginApplication("preetyagrawal08@gmail.com","Iamqueen@12");
			
		   	List <WebElement> products = productCatalogue.getProducts();
			productCatalogue.addProductToCart(ProductToAdd);
			CartPage cartPage =productCatalogue.goToCartPage();
		
			Boolean match = cartPage.verifyProductDisplay("qwertyyyy");
			Assert.assertFalse(match);
			
			

		}

	}



