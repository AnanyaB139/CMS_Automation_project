package Stepdefinition;

import Page.RolePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Objects;

public class RolesSteps {

    SharedLoginSteps _login = new SharedLoginSteps();
    RolePage _role = new RolePage();
    boolean iscreated = true;
    String _roleName;
    @Given("user login to the cms")
    public void user_login_to_the_cms() {
        _login.NavigateToUrl();
        _login.EnterUsernamePassword();
    }
    @Given("navigate to roles and click on + button")
    public void navigate_to_roles_and_click_on_button() {
        _role.CLickOnRoleMenu();
        _role.ClickOnCreateRole();
    }
    @When("user enter the {string} and clicks on the create button")
    public void userEnterTheAndClicksOnTheCreateButton(String roleName) {
        _roleName = roleName;
        _role.EnterRole(roleName);
        _role.ClickOnCreateBtn();
        _roleName = _role.IfAlertMessagePresent(roleName);

    }
    @Then("role name should be created successfully.")
    public void role_name_should_be_created_successfully() {
        _role.ClearSearchField();
       _role.SearchRoles(_roleName);
       List<String> actualRole =  _role.RolesList(_roleName);
       for(String element : actualRole){
           if(Objects.equals(element, _roleName)){
               Assert.assertEquals(element, _roleName);
               System.out.println("role has been created, actual and expected role name are equal");
               iscreated = true;
           }
       }
        if (!iscreated) {
            System.out.println("role name has not been created, and the intended and actual names differ.");
        }
    }
}
