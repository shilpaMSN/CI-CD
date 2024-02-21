package Framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponent;

public class conformationPage extends AbstractComponent {
	WebDriver driver;
	public conformationPage(WebDriver driver) {
	
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
	@FindBy(css=".action__submit")
	WebElement submitButton;
	
	By submitVisibility=By.cssSelector(".action__submit");
	
	@FindBy(css=".hero-primary")
	WebElement thankyText;
	
	public void orderConfirm()
	{
		scroll();
		waitForWebElementToAppear(submitVisibility);
		submitButton.click();
	}
	
	public String thankYou()
	{
		String text=thankyText.getText();
		return text;
	}
	
}


