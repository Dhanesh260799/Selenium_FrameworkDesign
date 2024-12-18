package danish.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import danish.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements (driver,this);
	}
	
	
	//		 driver.findElement(By.cssSelector("a[class*='action__submit']")).click();
	@FindBy(css="a[class*='action__submit']")
	WebElement submit;
	
	// a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"India").build().perform();
	 @FindBy(css="input[placeholder='Select Country']")
	 WebElement country;
	 //driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
	 @FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	 WebElement selectCountry;
	 
	 By results = By.cssSelector(".ta-results");
	 
	 public void selectCountry(String countryName) {
		 Actions a = new Actions(driver);
		 a.sendKeys(country,countryName).build().perform();
		 waitForElementtoAppear(results);
		 selectCountry.click();

	 }
	 
	 public ConfirmationPage submitOrder() {
		 submit.click();
		 return new ConfirmationPage(driver);
	 }

}
