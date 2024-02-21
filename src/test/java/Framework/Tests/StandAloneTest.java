package Framework.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		//NEW COMMENTS ARE ADDED
		String productName="ZARA COAT 3";
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Explisite wait invoking
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		//login
		driver.findElement(By.id("userEmail")).sendKeys("12345678@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Aa12345678");
		driver.findElement(By.id("login")).click();
	
		//waiting for webw=elements to appear on the page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
		
		
		//selecting zara coat 3 and add the product to cart
		WebElement prod=products.stream().filter(p->p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//exclusively targeting added to cart tost message using explisite wait(using direct visibilityOfElementLocated method)
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait for spin to disappear(by using invisibilityOf method(faster) )-improves the performance
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//validate weather added product and cart product are same
		List<WebElement> cartProducts=driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		boolean match =cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		//checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//this step can be done by actions class- way of entering text in search box using Actions class
//		Actions a=new Actions(driver);
//		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		
		//same above step can be done using driver object also-entering text in search box using drive only typing 
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		//scrolling down page for visibility of submit button
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,900)");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//validating weather thankyou text is displayed 
		String thankyou=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(thankyou.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();		
	}

}
