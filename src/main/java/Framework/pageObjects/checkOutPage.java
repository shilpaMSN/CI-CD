package Framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponent;

public class checkOutPage extends AbstractComponent {

	WebDriver driver;
	public checkOutPage(WebDriver driver) {
		 super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement enterCountry;
	
	By results=By.cssSelector(".ta-results");
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement submit;
	
	public void selectCountry(String country)
	{
		enterCountry.sendKeys(country);
		waitForWebElementToAppear(results);
		
	}
	public conformationPage submit() {
		submit.click();
		conformationPage conformationpage=new conformationPage(driver);
		return conformationpage;
		
	}
	

}
