package SeleniumFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.UtilityComponent.UtilityComponent;

public class ProductCatalogue extends UtilityComponent {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{ 
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	   @FindBy(css=".mb-3")  
	   List<WebElement> products;
	   
	   @FindBy(xpath="//ngx-spinner")
	   WebElement Spinner;
	   
	   By productsBy = By.cssSelector(".mb-3");
	   By ByProductName = By.cssSelector("b");
	   By addToCart = By.cssSelector(".card-body button:last-of-type");
	   By toastMessage = By.id("toast-container");
	   
	   public List<WebElement> getProducts() {
		   
		   waitForElementToAppear(productsBy);
		  
			return products;
		   
		 
	   }
	   
	   public WebElement getProductByName(String ProductName)
	   {
		   WebElement prod = getProducts().stream()
					.filter(product -> product.findElement(ByProductName).getText().equalsIgnoreCase(ProductName))
					.findFirst().orElse(null);
		   
		   return prod;
	   }
	   
	   
	   public void addProductToCart(String ProductName) {
		   
		   getProductByName(ProductName).findElement(addToCart).click();
		   waitForElementToAppear(toastMessage);
		   waitForElementToDisappear(Spinner);
	   }
}
