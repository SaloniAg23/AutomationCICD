package SeleniumFramework.pageobjects;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.UtilityComponent.UtilityComponent;

public class CheckOutPage extends UtilityComponent {

	
WebDriver driver;
	
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);  
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//section[contains(@class,'ta-results')]//span")
	List<WebElement> countries;
	
	@FindBy(xpath="//a[text()='Place Order ']")
	WebElement bttn;
	
	By placeOrder = By.xpath("//a[text()='Place Order ']");
	
	
   public void selectCountry(String countryName) {
		
		countries.stream().forEach(country -> System.out.println(country.getText()));
		Optional<WebElement> countryname = countries.stream()
				 .filter(country -> country.getText().equalsIgnoreCase(countryName)).findFirst();
		System.out.println(countryname.get().getText());
		countryname.get().click();
	}
	
	public void getCountry(String countryName ) {
		
        Actions a = new Actions(driver);
		
		a.sendKeys(country,countryName).build().perform();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(500,1000)");
	
	    selectCountry(countryName);
	}
	
	
	
	public OrderConfirmationPage placeOrderButton()
	{
		waitForElementToAppear(placeOrder);
		bttn.click();
		
		OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
		return orderConfirmationPage;
		
	}
}


