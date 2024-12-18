package danish.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import danish.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;
	
	// List <WebElement> productNames =driver.findElements(By.cssSelector("tr td:nth-child(3)"))
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	public Boolean VerifyOrderDisplay(String productName) {

		 Boolean match=productNames.stream().anyMatch(Product->Product.getText().equalsIgnoreCase(productName));
		 return match;
	}
}
