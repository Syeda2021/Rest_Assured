package resourceS;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    ExtentTest test;
    ExtentReports extent=ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {
        test= extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {

        extentTest.get().fail(result.getThrowable());

    }

    public void onTestSkipped(ITestResult result) {


    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {


    }

    public void onStart(ITestContext context) {


    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }


}
