package Framework.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Framework.AbstractComponents.AbstractComponent;

public class cartPage extends AbstractComponent {
	
	
	WebDriver driver;
	
	public cartPage(WebDriver driver)
	{	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public boolean verifyProductDisplay(String ProductName)
	{
		boolean match =cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	
	
	public checkOutPage goToCheckOut()
	{
		checkout.click();
		checkOutPage CheckOut= new checkOutPage(driver);
		return CheckOut;
		
	}
	

}
