package SeleniumFramework.tests;

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

import SeleniumFramework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String ProductToAdd = "qwerty";
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication("saloniagrawalexam@gmail.com","DurgaMaa12@");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(ProductToAdd))
				.findFirst().orElse(null);
		System.out.println(prod.getText());
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ngx-spinner"))));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		cartItems.stream().forEach(a -> System.out.println(a.getText()));
		Boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(ProductToAdd));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		String countryName = "India";
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), countryName).build().perform();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(500,1000)");
		//a.sendKeys(Keys.END).build().perform();
		List<WebElement> countries = driver.findElements(By.xpath("//section[contains(@class,'ta-results')]//span"));

		countries.stream().forEach(country -> System.out.println(country.getText()));
		Optional<WebElement> countryname = countries.stream()
				.filter(country -> country.getText().equalsIgnoreCase(countryName)).findFirst();
		System.out.println(countryname.get().getText());
		countryname.get().click();
		WebElement bttn = driver.findElement(By.xpath("//a[text()='Place Order ']"));

		// Thread.sleep(2000);
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[contains(@class,'ta-results')]/button/span[text()='"+countryName+"']")));
		// driver.findElement(By.xpath("//section[contains(@class,'ta-results')]/button/span[text()='"+countryName+"')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Place Order ']")));
		bttn.click();
		a.sendKeys(Keys.HOME).build().perform();
		// wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("hero-primary")));
		String message = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		System.out.println("Assertion is correct");

	}

}
