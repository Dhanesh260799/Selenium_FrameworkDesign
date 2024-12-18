package danish.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import danish.pageobjects.CartPage;
import danish.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//Pagefactory
 	@FindBy(css="[routerlink*='cart']")
	WebElement cartheader;
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
 	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	//driver.findElement(By.cssSelector("[routerlink*='myorders']")).click(); //regular expression
	
	public void waitForElementtoAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementtoAppear(WebElement findby) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	
	public CartPage GotoCartPage() {
		cartheader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	public OrderPage GotoOrderPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	public void waitForElementtoDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

		wait.until(ExpectedConditions.invisibilityOf(ele)); 
	}

}
