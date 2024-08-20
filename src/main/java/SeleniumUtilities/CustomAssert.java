package SeleniumUtilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import static DriverClass.Driversetup.driver;

public class CustomAssert {
    public static String screenshotFilePath;
    public static void captureScreenshotAndLog(WebDriver driver, String message, String resultStatus,String imgName) {
        Screenshort(imgName);
        // Log message and file paths
        System.out.println("Assertion " + resultStatus + " : " + message);
        System.out.println("Screenshot: " + screenshotFilePath);
    }

    public static void Screenshort(String imgName )
    {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotFileName = imgName + "_" + timestamp + ".png";
        String sourceFileName = imgName + "_" + timestamp + ".html";
        String currentDirectory = System.getProperty("user.dir");
        String newFolderName = "screenshots";
        String ScreenshoetFolderPath = currentDirectory + File.separator + newFolderName;
        File newFolder = new File(ScreenshoetFolderPath);
        if (!newFolder.exists()) {
            if (newFolder.mkdir()) {
                System.out.println("Folder created successfully: " + ScreenshoetFolderPath);
            } else {
                System.out.println("Failed to create folder: " + ScreenshoetFolderPath);
                return;
            }
        }

        screenshotFilePath = ScreenshoetFolderPath +File.separator+ screenshotFileName;
        String sourceFilePath = ScreenshoetFolderPath + File.separator + sourceFileName;

        // Capture screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(screenshotFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Capture page source
        String pageSource = driver.getPageSource();
        try {
            FileUtils.writeStringToFile(new File(sourceFilePath), pageSource, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void TakeFaileScreenshot(String iname) {
        Screenshort(iname);
    }

}
