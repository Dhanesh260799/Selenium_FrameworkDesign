package danish.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import danish.TestComponents.BaseTest;
import danish.TestComponents.Retry;
import danish.pageobjects.CartPage;
import danish.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}) //,retryAnalyzer=Retry.class -for retrying to run
	public void loginErrorValidation() throws IOException, InterruptedException {

		//String productName = "ZARA COAT 3";
		landingpage.loginApp("dhanesh12@gmail.com", "elvi@1098");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.loginApp("dhanesh12@gmail.com", "Selvi@1098");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.GotoCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}