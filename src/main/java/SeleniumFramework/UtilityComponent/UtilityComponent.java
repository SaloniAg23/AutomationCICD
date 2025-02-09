package SeleniumFramework.UtilityComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFramework.pageobjects.CartPage;
import SeleniumFramework.pageobjects.OrderHistoryPage;

public class UtilityComponent {
	
	 WebDriver driver;
	
	public UtilityComponent(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}

	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement goToCartButton;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orders;
	
	public void waitForElementToAppear(By findBy) {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));	
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
   }

	public void waitForElementToDisappear(WebElement element) {
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	   }
	
	public CartPage goToCartPage()
	{
		goToCartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	
	public OrderHistoryPage goToOrderHistoryPage()
	{
		orders.click();
		OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
		return orderHistoryPage;
	}
}
