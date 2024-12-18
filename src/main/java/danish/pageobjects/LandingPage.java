 package danish.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import danish.AbstractComponents.AbstractComponent;



public class LandingPage extends AbstractComponent  {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}

//	WebElement userEmail = driver.findElement(By.id("userEmail")).sendKeys("dhanesh12@gmail.com");
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//WebElement Password =driver.findElement(By.id("userPassword")).sendKeys("Selvi@1098");
	@FindBy(id="userPassword")
	WebElement PasswordEle;
	
	
//	WebElement Submit=driver.findElement(By.id("login")).click();
	
	@FindBy(id="login")
	WebElement Submit;
	
	
//Incorrect email or password.
	@FindBy(css=".toast-message")
	WebElement errorMessage;
	//div[aria-label='Incorrect email or password.']
	//class="ng-tns-c4-5 toast-message ng-star-inserted"
	//div[@aria-label='Incorrect email or password.']
	//need to create Action method
	//div[aria-label='Incorrect email or password.']
	public ProductCatalogue loginApp(String email,String password) {
		userEmail.sendKeys(email);
		PasswordEle.sendKeys(password);
		Submit.click();
		ProductCatalogue productCatalogue = new  ProductCatalogue(driver);
		return productCatalogue;
	}
	public String getErrorMessage() {
		waitForWebElementtoAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	public void navigateUrl() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	

}

