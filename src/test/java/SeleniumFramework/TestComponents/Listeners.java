package SeleniumFramework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumFramework.resources.ExtentReporterNG;


public class Listeners extends BaseTest implements ITestListener {

	ExtentReports report = ExtentReporterNG.getReportObject();
	ExtentTest test; // This holds an entry for the testcases in the report
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) // result hold the information of the test which is going to be executed.
	{
		
		
		test = report.createTest(result.getMethod().getMethodName());
		
		extentTest.set(test); // unique thread is assigned to each test for execution .(It overcomes the parallel run /concurrency issue)
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		//test.log(Status.PASS, "Test case is successfully passed.");
		extentTest.get().log(Status.PASS, "Test case is successfully passsed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable()); // If it reaches this block then fail it and allow it to throw error message contained in result.
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());  // get the driver from the result
		} catch (Exception e1) {
			
			e1.printStackTrace();
		
		String path = null;
		try {
			 path = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//this will take the file from our local system and attach to the Extent report using "test" object.
		
		//test.addScreenCaptureFromPath(path,result.getMethod().getMethodName());
		extentTest.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}
