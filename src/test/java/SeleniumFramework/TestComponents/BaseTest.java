package SeleniumFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFramework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeBrowser() throws IOException
	{
		
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumFramework\\resources\\GlobalData.properties");
		prop.load(file);
		//String browser = prop.getProperty("browser");
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser")
				: prop.getProperty("browser");
		
		if(browser.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			if(browser.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
		}
		
		else if(browser.equalsIgnoreCase("edge")){
			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
        else if(browser.equalsIgnoreCase("firefox")){
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver ;
	}
	
	
	public List<HashMap<String, String>> getJsondataToMap(String filePath) throws IOException
	{
		//Read json data as String value
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);

		//String to HashMap using Jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		
		//Below line tells how we have to return data(List) and in format data should be converted(HashMap)
		
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		
		return data;
		
	}
	
	
	 public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
     {
   	  TakesScreenshot ts =  (TakesScreenshot)driver;  //change driver mode to photo mode.
   	  File source = ts.getScreenshotAs(OutputType.FILE);
   	
   	  File destination =new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png"); 
   	  FileUtils.copyFile(source, destination);
   	  
   	  return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
   	 
     }
     
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeBrowser();
		landingPage = new LandingPage(driver);		
		landingPage.goTo();
		
		return landingPage;
		
	}
	@AfterMethod()
	public void tearDown()
	{
		driver.close();
	}
}

