package danish.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import com.fasterxml.jackson.core.type.TypeReference;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.nio.charset.StandardCharsets;
import org.openqa.selenium.chrome.ChromeOptions;

import com.fasterxml.jackson.databind.ObjectMapper;

//import danish.data.TypeReference;
import danish.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initializeDriver() throws IOException {

		// properties class
		Properties prop = new Properties();
		// user dir - starts from the selenium framework path and gets redirected to
		// internal path
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\danish\\resources\\GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// Above one Ternary operator
		// String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) { 
			//browserName.equalsIgnoreCase("chrome")
			//contains check whether it has any value same or not
			//equalsIgnorecase will check the exact match
			// WebDriverManager.chromedriver().setup(); need to install jars in
			// maven(webdrivermanager)
			
			ChromeOptions options = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver",
					"C://Users//dhane.DESKTOP-DUPTFG5//Downloads//chromedriver-win64 (2)//chromedriver-win64//New folder//chromedriver.exe");
			if(browserName.contains("headless")){
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
			//1440,900 is recommended for full screen view
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// firefox //Gecko Driver
		} else if (browserName.equalsIgnoreCase("edge")) {
			// edge
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\dhane.DESKTOP-DUPTFG5\\Downloads\\edgedriver_win64 (1)\\New folder\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to HashMap - need to download Jackson Databind jars

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");

		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException

	{
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.navigateUrl();
		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void close() {
		driver.close();

	}

}
