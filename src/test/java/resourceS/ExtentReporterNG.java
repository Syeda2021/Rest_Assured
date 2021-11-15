package resourceS;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    static ExtentReports extent;

    public static ExtentReports getReportObject()
    {

        String path =System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Talent Tech API Test Automation ");
        reporter.config().setDocumentTitle("Regression API test Results");

        extent =new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Iqbal");
        return extent;

    }
}
