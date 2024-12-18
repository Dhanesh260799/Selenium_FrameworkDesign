package danish.TestComponents;

import org.testng.ITestListener;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import danish.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	//to overcome concurrency issue while running in parrallel
	//we use ThreadLocal Class
	//test object has to be made Threadsafe, in that case no matter even if you run in concurrency
	//each object creation have its own Thread, so it wont interrupt the other overriding variable
	//this is possible with the help of ThreadLocal class
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		
		
		//when we try to run the test parallely generally we face concurrency issue
		//since the below test object holds both the test to pass the value
		//when it pass the value of fail for error valida test, at that time it will be assigned to submit order test and gives failure
		//mulitple times, mutiple test trying to access one single variable which is keeping overridden
		
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //which will set an object to this ThreadLocal
		//whenever it is set an object to the THreadLocal, it will create an Unique Thread ID(create map for each test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		((ExtentTest) extentTest.get()).fail(result.getThrowable());
		// if test paas or fail result will get stored in variable result
		// so here just use getthrowabble method it will throw the error message

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().
					getField("driver").get(result.getInstance()); //
			// step
			// gives
			// us
			// driver
			// note getTestClass - goes to xml classes
			// getRealClass - goes to xml class they we have class name - it navigates to
			// real class to acces the methods
			// getField - to get the driver acces
			// fields are classes not methods
			// this will give life to the sc reenshot
		} catch (Exception e) {
			e.printStackTrace();
		}
		// explains multiple exception you can write as exception in simple

		// Screenhot if test fails //Attach in report
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated catch block
		// try catch block is nothing but
		// if Screenshot does not exists it will print in the o/p no paht present
		// one it is there sends to the filepath
		// filepath takes the screenshot from the localsystem
		// attach it to the extentreport
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}

	public void onFinish(ITestContext context) {
		extent.flush();// report generation
	}

}
