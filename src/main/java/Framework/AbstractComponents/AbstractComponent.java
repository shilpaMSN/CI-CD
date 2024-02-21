package Framework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.pageObjects.OrderPage;
import Framework.pageObjects.cartPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderPageHeader;

	public AbstractComponent(WebDriver driver) {
		this.driver=driver; 
	}

	public void waitForWebElementToAppear(By findby)
	{
		
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findby)) ;
	}
	
	public void waitFordriverWebElementToAppear(WebElement findby)
	{
		
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findby)) ;
	}
	
	public void waitForWebElementToDisAppear(WebElement ele) throws InterruptedException
	{
		//4seconds_Application lag (slow)- use Thread.sleep(1000);
//	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
//	wait.until(ExpectedConditions.invisibilityOf(ele));
		Thread.sleep(1000);	
	}
	
	public cartPage goToCartPage()
	{
		cartHeader.click();
		cartPage cartpage=new cartPage(driver);
		return cartpage;
	}
	
	public void scroll()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,900)");
	}
	 
	public OrderPage goToOrdersPage()
	{
		orderPageHeader.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
	}

}
