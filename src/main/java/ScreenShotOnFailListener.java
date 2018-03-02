import Pages.BookmarksPage;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;


import java.io.ByteArrayInputStream;


public class ScreenShotOnFailListener implements ITestListener/*, IInvokedMethodListener*/ {

    WebDriver driver;

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        screenshot(iTestResult);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        screenshot(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        screenshot(iTestResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        screenshot(iTestResult);
    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    public void screenshot(ITestResult result){
        this.driver = ((TestBase)result.getInstance()).app.getDriver();
        System.out.println("Method: afterInvocation(IInvokedMethod method, ITestResult testResult)");
        Allure.addAttachment("Screenshot",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}