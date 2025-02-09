package SeleniumFramework.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFramework.TestComponents.BaseTest;
import SeleniumFramework.pageobjects.CartPage;
import SeleniumFramework.pageobjects.CheckOutPage;
import SeleniumFramework.pageobjects.LandingPage;
import SeleniumFramework.pageobjects.OrderConfirmationPage;
import SeleniumFramework.pageobjects.OrderHistoryPage;
import SeleniumFramework.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {

	String ProductToAdd = "qwerty";
	@Test(dataProvider="getData",groups={"purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {
	
		
		String countryName = "India";
				
        ProductCatalogue  productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
		
	   	List <WebElement> products = productCatalogue.getProducts();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage =productCatalogue.goToCartPage();
	
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		
		CheckOutPage checkoutPage =cartPage.goToCheckout();
		checkoutPage.getCountry(countryName);
		OrderConfirmationPage orderConfirmationPage = checkoutPage.placeOrderButton();
		
    	String message = orderConfirmationPage.confirmMessage();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		System.out.println("Assertion is correct");
		

	}

      @Test(dependsOnMethods= {"submitOrder"})
    	public void checkOrder()
    	{
    	  
    	  ProductCatalogue  productCatalogue = landingPage.loginApplication("saloniagrawalexam@gmail.com","DurgaMaa12@");
    	  OrderHistoryPage orderHistoryPage = productCatalogue.goToOrderHistoryPage();
    	 
    	 Boolean matchOrder = orderHistoryPage.checkforOrderPresent(ProductToAdd);
    	 Assert.assertTrue(matchOrder);
    	}
      
     
      
      @DataProvider
       public Object[][] getData() throws IOException
       {
    	  
    	List<HashMap<String,String>> data = getJsondataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumFramework\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
    	  
    	  
       }
   }
      
      
      
//      @DataProvider
//      public Object[][] getData()
//      {
//    	 Object[][] object =  new Object[][] {{"saloniagrawalexam@gmail.com","DurgaMaa12@","qwerty"},
//    		                                  {"preetyagrawal08@gmail.com","Iamqueen@12","Banarsi Saree"}};
//    	 return object ;
//      }
      
      
     // @DataProvider
//      public Object[][] getData()
//      {
//    	  HashMap<String,String> map = new HashMap<String,String>();
//    	  map.put("email", "saloniagrawalexam@gmail.com");
//    	  map.put("password", "DurgaMaa12@");
//    	  map.put("product", "qwerty");
//    	  
//    	  HashMap<String,String> map2 = new HashMap<String,String>();
//    	  map2.put("email", "preetyagrawal08@gmail.com");
//    	  map2.put("password", "Iamqueen@12");
//    	  map2.put("product", "Banarsi Saree");
//    	  
//		  return new Object[][] {{map},{map2}}; 
//    	  
//    	  
//      }

