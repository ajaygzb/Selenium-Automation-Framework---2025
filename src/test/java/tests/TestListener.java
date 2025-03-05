package tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestListener implements ITestListener {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed: " + result.getName());

        // Capture and attach screenshot for passed tests
        captureScreenshot(result, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail("Test Failed: " + result.getName());

        // Capture and attach screenshot for failed tests
        captureScreenshot(result, "FAIL");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Method to capture and attach screenshot
    private void captureScreenshot(ITestResult result, String status) {
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            driver = ((BaseTest) testClass).getDriver();
            String screenshotPath = takeScreenshot(result.getName(), status);
            if (screenshotPath != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            }
        }
    }

    // Method to take a screenshot and save it
    private String takeScreenshot(String testName, String status) {
        if (driver == null) {
            return null;
        }
        String screenshotDir = "test-output/screenshots/";
        String screenshotPath = screenshotDir + testName + "_" + status + ".png";

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(Paths.get(screenshotDir)); // Ensure directory exists
            Files.copy(srcFile.toPath(), Paths.get(screenshotPath));
            return screenshotPath;
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
