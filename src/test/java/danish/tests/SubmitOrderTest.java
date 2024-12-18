package danish.tests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import danish.TestComponents.BaseTest;
import danish.pageobjects.CartPage;
import danish.pageobjects.CheckoutPage;
import danish.pageobjects.ConfirmationPage;
import danish.pageobjects.LandingPage;
import danish.pageobjects.OrderPage;
import danish.pageobjects.ProductCatalogue;
import org.openqa.selenium.TakesScreenshot;



public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups={"purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException { 
		
		//String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue= landingpage.loginApp(input.get("email"),input.get("password"));
		List<WebElement> products =  productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.GotoCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
	
	
		 String confirmMessage = confirmationpage.getConfirmationMessage();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})//test strategy
	public void OrderHistoryTest() {
		
		//Validating "ZARA COAT 3"
		ProductCatalogue productCatalogue= landingpage.loginApp("dhanesh12@gmail.com", "Selvi@1098");
		OrderPage orderPage=productCatalogue.GotoOrderPage();
		//orderPage.VerifyOrderDisplay(productName);
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
	
		List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\danish\\data\\PurchaseOrder.json");
		
		
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
	
	 
	
	
	//another method array tow dimensional 
	//@DataProvider
	//public object[][] getData()
	//{
	// return new object[][]{{"dhanesh12@gmail.com","Selvi@1098","ZARA COAT 3"},{"danish12@gmail.com","Selvi@091","ADIDAS ORIGINAL"}};
	//}
	
	//two dimensional array instead of this use hashmap
	//hashmap
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email","dhanesh12@gmail.com");
//	map.put("password","Selvi@1098");
//	map.put("product","ZARA COAT 3");
//
//	HashMap map1 = new HashMap();
//	map1.put("email","danish12@gmail.com");
//	map1.put("password","Selvi@091");
//	map1.put("product","ADIDAS ORIGINAL");
	
	
	
}
