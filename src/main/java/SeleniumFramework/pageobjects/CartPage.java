package SeleniumFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.UtilityComponent.UtilityComponent;

public class CartPage extends UtilityComponent{

	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);  
	}
 
   @FindBy(css=".cartSection h3")  
   List<WebElement> cartItems;
	
   @FindBy(css=".totalRow button")
   WebElement checkOutButton;
    
   public Boolean verifyProductDisplay(String ProductName)
   {
	 Boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(ProductName));
	 return match;
	   
	   
   }
   
   public CheckOutPage goToCheckout()
   {
	   checkOutButton.click();
	   CheckOutPage checkOut = new CheckOutPage(driver);
	   return checkOut;
   }
   
   
   
}
