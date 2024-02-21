package Framework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);      //initElements method of PageFactory will take instance varible driver as an argument and assign it to class variable using this keyword
	} 

	//Using PageFactory 
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By waitForTost=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForWebElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod=products.stream().filter(p->p.findElement(By.cssSelector("b")).getText()
				.equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addToCart(String productName) throws InterruptedException
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForWebElementToAppear(waitForTost);
		waitForWebElementToDisAppear(spinner);
		
	}

	
	
	
}
