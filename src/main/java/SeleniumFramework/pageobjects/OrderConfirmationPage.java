package SeleniumFramework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.UtilityComponent.UtilityComponent;

public class OrderConfirmationPage extends UtilityComponent{

	WebDriver driver;
	public OrderConfirmationPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);  
	}

	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement msg;
	
	By messageVisible = By.xpath("//h1[@class='hero-primary']");
	
	public String confirmMessage()
	{
       Actions a = new Actions(driver);
       a.sendKeys(Keys.HOME).build().perform();
       waitForElementToAppear(messageVisible);
       String message = msg.getText();
       return message;
	}
}
