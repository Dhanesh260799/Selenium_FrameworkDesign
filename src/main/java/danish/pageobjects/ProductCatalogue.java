package danish.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import danish.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		//initialization
		super(driver);
		
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
//wait.until(ExpectedConditions.invisibility(driver.findElement(By.cssSelector(".ng-animating")))); 

	@FindBy(css=".ng-animating")
	WebElement spinner;

	
	
	By productsby = By.cssSelector(".mb-3");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector(".toast-message"); //class="ng-tns-c4-8 toast-message ng-star-inserted"
	//div[@aria-label='Product Added To Cart']
	//Action Method
	
	public List<WebElement> getProductList() {
		 waitForElementtoAppear(productsby);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product->
product.findElement(By.cssSelector("b")).getText().equals(productName)).
				findFirst().orElse(null);
		return prod;

	}
	public void addProductToCart(String productName) {
		WebElement prod =  getProductByName(productName);
		prod.findElement(addTocart).click();
		waitForElementtoAppear(toastMessage);
		waitForElementtoDisappear(spinner);
	}
	
	
	
	
	
	
	

}

