package Hooks;

import DriverClass.Driversetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static DriverClass.Driversetup.driver;

public class HooksBuinding {

    static Driversetup _driver = new Driversetup();
  //  static ContextClass _scenarioContext = new ContextClass();
    static  String failedMethod;
    static ITestResult result;
    private static ThreadLocal<String> currentTestMethod = new ThreadLocal<>();
    private static ThreadLocal<Scenario> currentScenario = new ThreadLocal<>();


    @Before
    public static void BeforeScenario()
    {
        _driver.LaunchBrowser();
        _driver.driver.manage().window().maximize();
    }
    @BeforeMethod
    public void beforeMethod(ITestResult result) {
        System.out.println("BeforeMethod executed: " + result.getMethod().getMethodName());
        currentTestMethod.set(result.getMethod().getMethodName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        System.out.println("AfterMethod executed: " + result.getMethod().getMethodName());
        // Conditionally remove the method name based on the test result
        if (result.getStatus() == ITestResult.SUCCESS) {
            currentTestMethod.remove();
        }
    }


    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("After scenario executed");
        if (scenario.isFailed()) {
            System.out.println("Scenario failed");
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String featureTitle = extractTitle(scenario.getId().split(";")[0]);
            String scenarioTitle = extractTitle(scenario.getName());
            String methodTitle = currentTestMethod.get();
            if (methodTitle == null) {
                methodTitle = "UnknownMethod";
            }
            String folderPath = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + featureTitle + File.separator + scenarioTitle;

            File directory = new File(folderPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String screenshotFileName = folderPath + File.separator + "error_" + featureTitle + "_" + scenarioTitle + "_" + methodTitle + "_" + timestamp + ".png";
            String sourceFileName = folderPath + File.separator + "error_" + featureTitle + "_" + scenarioTitle + "_" + methodTitle + "_" + timestamp + ".html";

            try {
                takeScreenshot(screenshotFileName);
                savePageSource(sourceFileName);
            } catch (IOException e) {
                System.out.println("Error capturing screenshot: " + e.getMessage());
            }
            System.out.println("failed steps " + " : " + scenario.getName() + methodTitle );
            System.out.println("Screenshot: " + screenshotFileName);
        }
        driver.quit();
    }

    private void takeScreenshot(String screenshotFileName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(screenshotFileName));
    }

    private void savePageSource(String sourceFileName) throws IOException {
        String pageSource = driver.getPageSource();
        FileUtils.writeStringToFile(new File(sourceFileName), pageSource, "UTF-8");
    }

    private String extractTitle(String title) {
        // Replace all non-alphanumeric characters with an underscore
        return title.replaceAll("[^a-zA-Z0-9]", "_");
    }
}


