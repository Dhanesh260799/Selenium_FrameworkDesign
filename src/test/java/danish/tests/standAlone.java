package danish.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import danish.pageobjects.LandingPage;

public class standAlone {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// WebDriverManager.chromedriver().setup();

		// implicit wait
		// used for sync issues
		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client/");
	//	LandingPage landingpage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("dhanesh12@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selvi@1098");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build()
				.perform();
		// List<WebElement> options =
		// driver.findElements(By.cssSelector("button[type='button'] span"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		// options.stream().filter(option->option.getText().equals("India")).findFirst().get().click();
		// Thread.sleep(5);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));

		driver.findElement(By.cssSelector("a[class*='action__submit']")).click();
		// System.out.println(driver.findElement(By.xpath("//table/tbody/tr/td/label[2]")).getText());
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

	}

}
