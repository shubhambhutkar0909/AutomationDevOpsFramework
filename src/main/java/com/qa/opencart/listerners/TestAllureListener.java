package com.qa.opencart.listerners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.opencart.factory.DriverFactory;

public class TestAllureListener extends DriverFactory implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // Capture and attach screenshot to Allure report
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        if (driver == null) {
            System.out.println("WebDriver instance is null. Cannot take screenshot.");
            return new byte[0];
        }

        try {
            if (driver instanceof TakesScreenshot) {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } else {
                System.out.println("Driver does not support screenshots.");
            }
        } catch (Exception e) {
            System.out.println("Error while capturing screenshot: " + e.getMessage());
        }
        return new byte[0];
    }

    // Save log text to Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // Save HTML to Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Test execution started: " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Test execution finished: " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test started: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test passed: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test failed: " + getTestMethodName(iTestResult));

        try {
            WebDriver driver = getDriver();
            if (driver != null) {
                if (driver instanceof RemoteWebDriver) {
                    System.out.println("Remote WebDriver detected. Taking screenshot...");
                } else {
                    System.out.println("Local WebDriver detected. Taking screenshot...");
                }

                // Capture screenshot and attach it to the Allure report
                byte[] screenshot = saveScreenshotPNG(driver);
                if (screenshot.length > 0) {
                    System.out.println("Screenshot successfully captured and attached.");
                } else {
                    System.out.println("Screenshot capture failed.");
                }
            } else {
                System.out.println("WebDriver instance is null. Skipping screenshot capture.");
            }
        } catch (Exception e) {
            System.out.println("Error while capturing failure screenshot: " + e.getMessage());
        }

        // Log failure in Allure
        saveTextLog(getTestMethodName(iTestResult) + " failed. Screenshot has been taken.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test skipped: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but within success percentage: " + getTestMethodName(iTestResult));
    }
}
