package Framework.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Framework.pageObjects.LandingPage;

public class BaseTest {

	public LandingPage landingPage;
	public static WebDriver driver;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream str = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Framework\\resources\\Global.properties");
		prop.load(str);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		
		//String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			
			ChromeOptions option=new ChromeOptions();
			if(browserName.contains("headless"))
			{
			option.addArguments("headless");	
			}
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1400,900));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	//utility method which converts json to hashmap

	public List<HashMap<String,String>> getJsonDataToMap() throws IOException
	{
		String jsonContent=FileUtils.readFileToString(new File("C:\\Eclipse-workspace\\SeleniumFreameworkDesign\\src\\test\\java\\Framework\\data\\PurchaseOrder.json"),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	
	
	
	// returning array type of dataprovider method
//	@DataProvider
//	public Object[][] data()
//	{
//		return new Object[][] {{"12345678@gmail.com","Aa12345678","ZARA COAT 3"},{"chubbicheeks@gmail.com","Abcd1234","ADIDAS ORIGINAL"}};
//		
//	}

	// returning HashMap type of dataprovider method
	@DataProvider
	public Object[][] data1() {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("email", "12345678@gmail.com");
		map.put("password", "Aa12345678");
		map.put("ProductName", "ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<String, String>();

		map1.put("email", "chubbicheeks@gmail.com");
		map1.put("password", "Abcd1234");
		map1.put("ProductName", "ADIDAS ORIGINAL");

		return new Object[][] { { map }, { map1 } };
	}
	
	//utility to take screenshot 
	
	public String getScreenshot(String testname,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File("C:\\Eclipse-workspace\\SeleniumFreameworkDesign"+"//reports//"+testname+".png");
		FileUtils.copyFile(source, file);
		return "C:\\Eclipse-workspace\\SeleniumFreameworkDesign"+"//reports//"+testname+".png";
	}
	
	
	
	
	
	
	//dataprovider for json to hashmap utility (NOT WORKING- ISSUES-UNKNOWN- ALL SYNTAX PERFECT)
 
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data=getJsonDataToMap();
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}	
	
	


	}

