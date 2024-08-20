package Stepdefinition;

import ContextClass.SecnarioContextKey;
import DriverClass.Driversetup;
import Page.LoginPage;

public class SharedLoginSteps {

    Driversetup _driver = new Driversetup();
    LoginPage _login = new LoginPage();

    public void NavigateToUrl()
    {
        String Url =  SecnarioContextKey.jwtURL;
        _driver.driver.get(Url);
    }

    public void EnterUsernamePassword()
    {
        String username =  SecnarioContextKey.userName;
        String password =  SecnarioContextKey.password;
        _login.EnterUserName(username);
        _login.EnterPassword(password);
        _login.ClickOnLoginBtn();
    }

    public void GivenLoginDetails(String username, String password)
    {
        _login.EnterUserName(username);
        _login.EnterPassword(password);
        _login.ClickOnLoginBtn();
    }

}
