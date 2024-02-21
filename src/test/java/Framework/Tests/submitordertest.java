package Framework.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.TestComponent.BaseTest;
import Framework.TestComponent.ReTry;
import Framework.pageObjects.LandingPage;
import Framework.pageObjects.OrderPage;
import Framework.pageObjects.ProductCatalogue;
import Framework.pageObjects.cartPage;
import Framework.pageObjects.checkOutPage;
import Framework.pageObjects.conformationPage;

public class submitordertest extends BaseTest{
	String ProductName="ZARA COAT 3";
	@Test(dataProvider="getData",groups={"Purchase"},retryAnalyzer=ReTry.class)
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
		productCatalogue.getProductList();
		productCatalogue.getProductByName(input.get("ProductName"));
		productCatalogue.addToCart(input.get("ProductName"));
		cartPage cartpage=productCatalogue.goToCartPage();
		boolean match=cartpage.verifyProductDisplay(input.get("ProductName"));
		Assert.assertTrue(match);       //validations will not go inside pageobjectfile(Assertions are not allowed inside PageObject))
		checkOutPage checkOutpage=cartpage.goToCheckOut();
		checkOutpage.selectCountry("india");
		conformationPage confirmation=checkOutpage.submit();
		confirmation.orderConfirm();
		String message=confirmation.thankYou();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
				
	}
	


	//to verify product is displayed in orders page
		//@Test(dependsOnMethods= {"submitOrder"})
		public void orderHistoryTest() throws IOException, InterruptedException {
			
			ProductCatalogue productCatalogue=landingPage.loginApplication("12345678@gmail.com","Aa12345678");
			OrderPage orderpage=productCatalogue.goToOrdersPage();
			Assert.assertTrue(orderpage.verifyOrderDisplay(ProductName));
			
		}
		
		}
