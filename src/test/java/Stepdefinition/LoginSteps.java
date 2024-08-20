package Stepdefinition;

import ContextClass.SecnarioContextKey;
import DriverClass.Driversetup;
import Page.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    LoginPage _login = new LoginPage();
    Driversetup _driver = new Driversetup();

    @Given("I navigate to the CMS login page")
    public void i_navigate_to_the_cms_login_page() {
        String Url = SecnarioContextKey.jwtURL;
        _driver.driver.get(Url);
    }
    @When("I enter the correct username and password")
    public void i_enter_the_correct_username_and_password() {
        _login.EnterUserName(SecnarioContextKey.userName);
        _login.EnterPassword(SecnarioContextKey.password);
        _login.ClickOnLoginBtn();
    }
    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        _login.VerifyDashboard("pass");
    }
}
