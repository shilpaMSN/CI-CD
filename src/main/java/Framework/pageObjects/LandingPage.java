package Framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);      //initElements method of PageFactory will take instance varible driver as an argument and assign it to class variable using this keyword
	} 
	
//	WebElement email=driver.findElement(By.id("userEmail"));        //instead of using driver object here PageFactory can be used as shown below
//	WebElement password=driver.findElement(By.id("userPassword"));
//	WebElement loginButton=driver.findElement(By.id("login"));

	//Using PageFactory //webelements can be assigned to variables using pagefactory (mostly used these days)
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String errorMessage()
	{
		waitFordriverWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
