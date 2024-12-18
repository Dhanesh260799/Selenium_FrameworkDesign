package danish.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import danish.TestComponents.BaseTest;
import danish.pageobjects.CartPage;
import danish.pageobjects.CheckoutPage;
import danish.pageobjects.ConfirmationPage;
import danish.pageobjects.LandingPage;
import danish.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {
	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationpage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingpage = launchApplication();
	}
	@Given("^Loggged in with username (.+) and password (.+)$")
	public void loggged_in_with_username_and_password(String username,String password) {
		productCatalogue = landingpage.loginApp(username,password);
	}
	
	
	 @When("^I add product (.+) to cart$")
	 public void I_add_product_to_cart(String productName) {
		 List<WebElement> products =  productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
	 }
	 @And("^Checkout (.+) and Submit the order$")
	 public void And_Checkout_and_Submit_the_order(String productName) {
		 CartPage cartPage = productCatalogue.GotoCartPage();
			
		 	Boolean match = cartPage.VerifyProductDisplay(productName);
			Assert.assertTrue(match);
			CheckoutPage checkoutpage = cartPage.goToCheckout();
			checkoutpage.selectCountry("india");
			
			 confirmationpage = checkoutpage.submitOrder();
	 }
	 @ Then("{string} message is displayed on confirmationPage")
	 public void message_is_displayed_on_confirmationPage(String string) {

		 String confirmMessage = confirmationpage.getConfirmationMessage();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		 driver.close();
	 }
	 @Then("{string} Error message is displayed")
	 public void error_message_is_displayed(String string) {
		
			Assert.assertEquals(string, landingpage.getErrorMessage());
			driver.close();
	 }
	 
	 
}
