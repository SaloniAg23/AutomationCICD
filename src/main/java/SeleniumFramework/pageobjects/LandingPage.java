package SeleniumFramework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.UtilityComponent.UtilityComponent;

public class LandingPage extends UtilityComponent{

	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);  // Used to trigger all the @FindBy using driver .
		                                        // Written inside constructor so that initialization 
		                                        // of WebElements is done first as constructor is called first.
		                                         
	}
 
   @FindBy(id="userEmail")  // construct a query using driver -->driver.findElement(By.id("userEmail"))
   WebElement userEmail;
	
   @FindBy(id="userPassword")  // construct a query using driver -->driver.findElement(By.id("userPassword"))
   WebElement userPass;
   
   @FindBy(id="login")  
   WebElement submit;
   
   @FindBy(xpath="//div[@role='alert']")
   WebElement alertMessage;
   
   By error = By.xpath("//div[@role='alert']");
   
   public ProductCatalogue loginApplication(String Name , String Password) {
   
   userEmail.sendKeys(Name);
   userPass.sendKeys(Password);
   submit.click();
   
   ProductCatalogue  productCatalogue = new ProductCatalogue(driver);
   return productCatalogue;
   
}
   
   public void goTo() {
	   
	   driver.get("https://rahulshettyacademy.com/client");
   }
   
   public String getErrorMessage() {
   
   waitForElementToAppear(error);
   String alertMsg =  alertMessage.getText();
   return alertMsg;
   }
}