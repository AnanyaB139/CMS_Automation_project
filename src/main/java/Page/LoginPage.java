package Page;

import ContextClass.SecnarioContextKey;
import DriverClass.Driversetup;
import SeleniumUtilities.CustomAssert;
import SeleniumUtilities.Utilities;
import org.openqa.selenium.By;

public class LoginPage {

    Driversetup _driver = new Driversetup();
    Utilities _utilities = new Utilities();
    public By _userName = By.xpath("//input[@id='username']");
    public By _password = By.xpath("//input[@id='password']");
    public By _loginbtn = By.xpath("//button[@type='submit']");
    public By _dashboard = By.xpath(" //span[text()=' Dashboard ']");


    public void VerifyDashboard(String result)
    {
        if(_utilities.IsElementIsPresent(_dashboard))
        {
            CustomAssert.captureScreenshotAndLog(_driver.driver,"Dashboard is present",result, "Dashboard");
        }
    }
    public void EnterUserName(String username)
    {
        _utilities.WaitUntilTheElementIsAvilable(_userName);
        _utilities.SendKeysCatchStaleElementException(_userName, username);
    }
    public void EnterPassword(String password)
    {
        _utilities.WaitUntilTheElementIsAvilable(_password);
        _utilities.SendKeysCatchStaleElementException(_password,password );
    }

    public void ClickOnLoginBtn()
    {
        _utilities.WaitUntilTheElementIsAvilable(_loginbtn);
        _utilities.ClickCatchStaleElementException(_loginbtn);
    }

}
