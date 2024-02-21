package Framework.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.TestComponent.BaseTest;
import Framework.TestComponent.ReTry;
import Framework.pageObjects.ProductCatalogue;
import Framework.pageObjects.cartPage;
public class ErrorValidation extends BaseTest{
	String ProductName="ZARA COAT 3";
	@Test(groups= {"ErrorHandling"},retryAnalyzer=ReTry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		
		ProductCatalogue productCatalogue=landingPage.loginApplication("123456789@gmail.com","Aa12345678");
		Assert.assertEquals("Incorrect email or passwrd.",landingPage.errorMessage() );
	}
	
	//@Test(groups= {"ErrorHandling"},retryAnalyzer=ReTry.class)
	public void productErrorValidation() throws IOException, InterruptedException {
		String ProductName="ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.loginApplication("12345678@gmail.com","Aa12345678");
		productCatalogue.getProductList();
		productCatalogue.getProductByName(ProductName);
		productCatalogue.addToCart(ProductName);
		cartPage cartpage=productCatalogue.goToCartPage();
		boolean match=cartpage.verifyProductDisplay(ProductName);
		Assert.assertTrue(match);       //validations will not go inside pageobjectfile(Assertions are not allowed inside PageObject))
	}

			
	
}
