package Stepdefinition;
import Page.CreateUserPage;
import Page.RolePermissionPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import static org.testng.AssertJUnit.assertTrue;


public class RolePermissionSteps {
    RolePermissionPage _role = new RolePermissionPage();
    SharedLoginSteps _login = new SharedLoginSteps();
    CreateUserPage user = new CreateUserPage();

    @Given("user login to the cms by giving {string} and {string}")
    public void user_login_to_the_cms_by_giving_and(String username, String password) {
        _login.NavigateToUrl();
         _login.GivenLoginDetails(username,password);
    }
    @When("I verify the {string} and  navigate to the side menu bar")
    public void iVerifyTheAndNavigateToTheSideMenuBar(String username) {
        String actualUserName = _role.VerifyUserName().trim();
        String expectedUserName = username.trim();

        // Debug statements
        System.out.println("Actual Username: " + actualUserName);
        System.out.println("Expected Username: " + expectedUserName);

        assertTrue(actualUserName.equalsIgnoreCase(expectedUserName));
    }
    @Then("I should see the user module and able to read, write, modify, and delete in the user module")
    public void iShouldSeeTheUserModuleAndAbleToReadWriteModifyAndDeleteInTheUserModule() {
        String _results =   _role.VerifyUser("pass");
        if(_results == null)
        {
            Assert.fail("the user module is not present in the cms");
        }
        user.ClickOnCreateUsers();
        validatePageTitle("Create User");
        String name =user.GetUserName();
        user.ClickOnAssignPackage();
        validatePageTitle("Assigned Package List for:" + name);
        user.ClickOnEditActiveUsers();
        validatePageTitle("Update Details:");
        user.ClickOnBockUser();
        _role.IfTheAlertMessageAppeared();
        user.VerifyBlockPopup();
        user.ClickOnNonActiveUser();
        user.ClickOnEditNonActiveUser();
        validatePageTitle("Update Details:");
        user.ClickOnUserProfile();
        validatePageTitle("User Profile:");
        user.ClickOnBockUser();
        _role.IfTheAlertMessageAppeared();
        user.VerifyBlockPopup();
        user.ClickOnBlockedUsersPage();
        _role.IfTheAlertMessageAppeared();
        user.ClickOnUnblockUser();
        user.VerifyUnblockPopup();
    }
    private void validatePageTitle(String expectedTitle) {
        _role.IfTheAlertMessageAppeared();

        String actualTitle = _role.Title();
        Assert.(actualTitle, expectedTitle);
        try{
           user.ClickOnCancelBtn();
        }
        catch (Exception e)
        {
           user.ClickOnBackBtn();
        }

    }
    @Then("I should not see any other modules in the side menu")
    public void i_should_not_see_any_other_modules_in_the_side_menu() {
           _role.VerifyPlatform("fail");
           _role.VerifyPackage("fail");
           _role.VerifyRole("fail");
           _role.VerifyMetadata("fail");
           _role.VerifyAsset("fail");
           _role.VerifySettings("fail");
           _role.VerifyDashboard("pass");

    }
}
