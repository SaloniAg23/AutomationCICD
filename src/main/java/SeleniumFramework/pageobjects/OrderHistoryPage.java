package SeleniumFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.UtilityComponent.UtilityComponent;

public class OrderHistoryPage extends UtilityComponent{
	
     WebDriver driver;
	
	public OrderHistoryPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);  // Used to trigger all the @FindBy using driver .
		                                        // Written inside constructor so that initialization 
		                                        // of WebElements is done first as constructor is called first.
    }
	
      @FindBy(xpath="//tr[@class='ng-star-inserted']/td[2]")
      List<WebElement> ordersList;
      
      
      public Boolean checkforOrderPresent(String ProductName)
      {
    	  Boolean matchOrder = ordersList.stream().anyMatch(order->order.getText().equalsIgnoreCase(ProductName));
          return matchOrder;   
      }
      
      
 
 
 
 
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

