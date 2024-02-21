package Framework.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Framework.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	ExtentTest test;
	WebDriver driver;
	@Override  
	public void onTestStart(ITestResult result) {  
	// TODO Auto-generated method stub  
		extent=ExtentReporterNG.getReportObject();
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}  
	  
	@Override  
	
	public void onTestSuccess(ITestResult result) {  
	// TODO Auto-generated method stub  
		extentTest.get().log(Status.PASS, "test passed");
	}  
	  
	@Override  
	public void onTestFailure(ITestResult result) {  
	// TODO Auto-generated method stub  
		extentTest.get().fail(result.getThrowable());
	
	//screenshot,attach it to the report
	try {
		driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	String filePath=null;
	try {
		filePath = getScreenshot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}  
	  
	@Override  
	public void onTestSkipped(ITestResult result) {  
	// TODO Auto-generated method stub  
	
	}  
	  
	@Override  
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
	// TODO Auto-generated method stub  
	}  
	  
	@Override  
	public void onStart(ITestContext context) {  
	// TODO Auto-generated method stub  
	}  
	  
	@Override  
	public void onFinish(ITestContext context) {  
	// TODO Auto-generated method stub  
		
	extent.flush();
	}  

}
