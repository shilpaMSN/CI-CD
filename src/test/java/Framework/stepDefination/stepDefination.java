package Framework.stepDefination;

import java.io.IOException;

import org.testng.Assert;

import Framework.TestComponent.BaseTest;
import Framework.pageObjects.LandingPage;
import Framework.pageObjects.ProductCatalogue;
import Framework.pageObjects.cartPage;
import Framework.pageObjects.checkOutPage;
import Framework.pageObjects.conformationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefination extends BaseTest {
	LandingPage landingpage;
	ProductCatalogue productCatalogue;
	conformationPage confirmation;
	checkOutPage checkOutpage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		//code
		landingpage=launchApplication();
	}
	
	@Given("^login in with username (.+) and password (.+)$")
	public void login_in_with_username_and_password(String username,String password)
	{
		 productCatalogue=landingPage.loginApplication(username,password);
	}
	
	 @When("^I add the product (.+) to cart$")
	 public void add_the_product_to_cart(String productname) throws InterruptedException
	 {
		 productCatalogue.getProductList();
			productCatalogue.getProductByName(productname);
			productCatalogue.addToCart(productname);
	 }
	 @When("^checkout (.+) and submit the order$")
	 public void checkout_and_submit_the_order(String productname)
	 {
		 cartPage cartpage=productCatalogue.goToCartPage();
			boolean match=cartpage.verifyProductDisplay(productname);
			Assert.assertTrue(match);       //validations will not go inside pageobjectfile(Assertions are not allowed inside PageObject))
			 checkOutpage=cartpage.goToCheckOut();
			checkOutpage.selectCountry("india");	
	 }
	 @Then("{string} message is displayed on confermation page") 
	 public void message_is_displayed_on_confermation_page(String string)
	 {
		 confirmation=checkOutpage.submit();
			confirmation.orderConfirm();
			String message=confirmation.thankYou();
			Assert.assertTrue(message.equalsIgnoreCase(string));
			driver.close();
	 }
	 
	 @Then("{string} message is displayed")
	 public void message_is_displayed(String string)
	 {
		 Assert.assertEquals(string,landingPage.errorMessage());
		 driver.close();
	 }
	

}
