package Page;

import ContextClass.SecnarioContextKey;
import DriverClass.Driversetup;
import SeleniumUtilities.CustomAssert;
import SeleniumUtilities.Utilities;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RolePermissionPage {

    Driversetup _driver = new Driversetup();
    Utilities _utilities = new Utilities();
    JavascriptExecutor js = (JavascriptExecutor)_driver.driver;
    Actions action = new Actions(_driver.driver);


    public By _name = By.xpath("//div[@class='mt-2 mr-2']");
    public By _platform = By.xpath("//span[text()='Platform']");
    public By _package = By.xpath("//span[text()='Package']");
    public By _role = By.xpath("//span[text()='Role']");
    public By _user = By.xpath("//span[text()='Users']");
    public By _metadata = By.xpath("//span[text()='Metadata']");
    public By _assets = By.xpath("//span[text()='Assets']");
    public By _settings = By.xpath("//span[text()='Settings']");
    public By _dashboard = By.xpath(" //span[text()=' Dashboard ']");
    public By _createUser = By.xpath("//h4[@class='margin_spacing']");
    public By _assignPackage = By.xpath("//h4[text()='Assigned Package List for:']");
    public By _userList = By.xpath("//h4[@class='margin_spacing']");

    public String Title()
    {
        _utilities.WaitUntilTheElementIsAvilable(_createUser);
        return _utilities.FindElement(_createUser).getText();
    }

    public void IfTheAlertMessageAppeared()
    {
        if (isAlertPresent(_driver.driver))
        {
            CustomAssert.captureScreenshotAndLog(_driver.driver,"Do not have permission message appeared", "fail","permissionWarningMesssage");
        }
    }
    public static boolean isAlertPresent(WebDriver driver) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void GetUrl()
    {
       String url =  SecnarioContextKey.jwtURL;
      _driver.driver.get(url);
    }

    public void VerifyDashboard(String result)
    {
        if(_utilities.IsElementIsPresent(_dashboard))
        {
            _utilities.ClickCatchStaleElementException(_dashboard);
            CustomAssert.captureScreenshotAndLog(_driver.driver,"Dashboard is present",result, "Dashboard");

        }
    }
    public void VerifySettings(String result)
    {
        if(_utilities.IsElementIsPresent(_settings))
        {
            _utilities.ClickCatchStaleElementException(_settings);
            CustomAssert.captureScreenshotAndLog(_driver.driver,"Settings is present",result, "Settings");
        }
    }
    public void VerifyAsset(String result)
    {
        if(_utilities.IsElementIsPresent(_assets))
        {
            _utilities.ClickCatchStaleElementException(_assets);
            CustomAssert.captureScreenshotAndLog(_driver.driver,"Asset is present",result, "Asset");
        }
    }
    public void VerifyMetadata(String result)
    {
        if(_utilities.IsElementIsPresent(_metadata))
        {
            _utilities.ClickCatchStaleElementException(_metadata);
            CustomAssert.captureScreenshotAndLog(_driver.driver,"metadata is present",result, "metadata");
        }
    }
    public String VerifyUser(String result)
    {

        if(_utilities.IsElementIsPresent(_user))
        {
            _utilities.ClickCatchStaleElementException(_user);
            CustomAssert.captureScreenshotAndLog(_driver.driver,"user is present",result, "user");
            return "present";
        } else {
            return null;
        }
    }
    public void VerifyRole(String result)
    {
        if(_utilities.IsElementIsPresent(_role))
        {
            _utilities.ClickCatchStaleElementException(_role);
            CustomAssert.captureScreenshotAndLog(_driver.driver,"Role is present",result, "Role");
        }
    }
    public void VerifyPackage(String result)
    {
        if(_utilities.IsElementIsPresent(_package))
        {
            _utilities.ClickCatchStaleElementException(_package);
            CustomAssert.captureScreenshotAndLog(_driver.driver,"package is present",result, "package");
        }
    }
    public void VerifyPlatform(String result)
    {
        if(_utilities.IsElementIsPresent(_platform))
        {
            _utilities.ClickCatchStaleElementException(_platform);
            CustomAssert.captureScreenshotAndLog(_driver.driver,"Platform is present",result, "plattform");
        }
    }


    public String VerifyUserName()
    {
        _utilities.WaitUntilTheElementIsAvilable(_name);
        return _utilities.FindElement(_name).getText();
    }


}
