package Page;

import DriverClass.Driversetup;
import SeleniumUtilities.CustomAssert;
import SeleniumUtilities.Utilities;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class CreateUserPage {

    Driversetup _driver = new Driversetup();
    Utilities _utilities = new Utilities();
    Random _random = new Random();
    Faker faker = new Faker();
    String firstName;
    WebElement userProfile;
    public By _createUsers = By.xpath("//span[@title='Create User']");
    public By _firstName = By.xpath("//input[@name='firstName']");
    public By _lastName = By.xpath("//input[@name='lastName']");
    public By _userId = By.id("userId");
    public By _email = By.id("emailId");
    public By _phoneNumber = By.id("mobileNumber");
    public By _roleName = By.xpath("//select[@formcontrolname='roleId']");
    public By _platform = By.xpath("//select[@formcontrolname='platform']");
    public By _package = By.xpath("//select[@formcontrolname='package']");
    public By _subPackage = By.xpath("//select[@formcontrolname='subpackage']");
    public By _createBtn = By.xpath("//button[text()='Create']");
    public By _cancel = By.xpath("//button[text()='Cancel']");
    public By _assignPackage = By.xpath("(//span[@title='Assign Package to User'])[2]");
    public By _updateActiveUsers = By.xpath("//span[@title='Update User']");
    public By _blockUser = By.xpath("(//span[@title='Block User'])[2]");
    public By _nonActiveUser = By.xpath("//a[@id='icon-nonactivated-anim-tab']");
    public By _updateNonActive = By.xpath("//div[@id='icon-nonactivated']/table/tbody/tr/td[2]/span[@title='Update User']");
    public By _blockPopUp = By.xpath("//div[text()='Are you sure you want to block this user? ']");
    public By _userProfile = By.xpath("//span[@title='User Profile']");
    public By _backBtn = By.xpath("//button[text()='Back']");
    public By _blockedUsers = By.xpath("//a[@id='icon-blocked-anim-tab']");
    public By _unblockUser = By.xpath("//span[@title='Unblock User']");
    public By _unblockUserPopup = By.xpath("//div[text()='Are you sure you want to unblock this user? ']");
    public By _getUsername = By.xpath("//table[@class='table table-bordered mb-4']/tbody/tr/td[1]");


//    public String  GetUserName(){
//        if(_utilities.IsElementIsPresent(_getUsername))
//        {
//           return _utilities.SelectRandomly(_getUsername);
//        }else{
//            return null;
//        }
//    }
    public void ClickOnCreateUsers()
    {
        _utilities.WaitUntilTheElementIsAvilable(_createUsers);
        _utilities.ClickCatchStaleElementException(_createUsers);
    }
    public void VerifyUnblockPopup()
    {
        if(_utilities.IsElementIsPresent(_unblockUserPopup))
        {
            CustomAssert.captureScreenshotAndLog(_driver.driver, "unblock user popup", "pass","unblockUser popup");
        }
    }
    public void ClickOnUnblockUser()
    {
        if(_utilities.IsElementIsPresent(_unblockUser))
        {
            _utilities.WaitUntilTheElementIsAvilable(_unblockUser);
            _utilities.ClickCatchStaleElementException(_unblockUser);
        }
        else {
            System.out.println("there is not users on the blocked users list");
        }
    }
    public void ClickOnBlockedUsersPage()
    {
        _utilities.WaitUntilTheElementIsAvilable(_blockedUsers);
        _utilities.ClickCatchStaleElementException(_blockedUsers);
    }
    public void ClickOnBackBtn()
    {
        _utilities.WaitUntilTheElementIsAvilable(_backBtn);
        _utilities.ClickCatchStaleElementException(_backBtn);
    }
    public void ClickOnUserProfile()
    {
        _utilities.WaitUntilTheElementIsAvilable(_userProfile);
         userProfile = _utilities.SelectRandomly(_userProfile);
    }
    public void ClickOnEditNonActiveUser()
    {
        _utilities.WaitUntilTheElementIsAvilable(_updateNonActive);
        _utilities.ClickCatchStaleElementException(_updateNonActive);
    }
    public void ClickOnNonActiveUser()
    {
        _utilities.WaitUntilTheElementIsAvilable(_nonActiveUser);
        _utilities.ClickCatchStaleElementException(_nonActiveUser);
    }
    public void VerifyBlockPopup()
    {
        if(_utilities.IsElementIsPresent(_blockPopUp)){
            CustomAssert.captureScreenshotAndLog(_driver.driver,"block user popup message appeared", "pass", "blockUsrePopup");
        }
    }
    public void ClickOnBockUser()
    {
        _utilities.WaitUntilTheElementIsAvilable(_blockUser);
        _utilities.ClickCatchStaleElementException(_blockUser);
    }
    public void ClickOnEditActiveUsers()
    {
        _utilities.WaitUntilTheElementIsAvilable(_updateActiveUsers);
        _utilities.ClickCatchStaleElementException(_updateActiveUsers);
    }
    public void ClickOnAssignPackage(){
        _utilities.WaitUntilTheElementIsAvilable(_assignPackage);
        _utilities.ClickCatchStaleElementException(_assignPackage);
    }
    public void ClickOnCreateBtn()
    {
        _utilities.WaitUntilTheElementIsAvilable(_createBtn);
        _utilities.ClickCatchStaleElementException(_createBtn);
    }
    public void ClickOnCancelBtn()
    {
        _utilities.WaitUntilTheElementIsAvilable(_cancel);
        _utilities.ClickCatchStaleElementException(_cancel);
    }
    public void SelecteSubPackage()
    {
        while(true) {
            _utilities.WaitUntilTheElementIsAvilable(_subPackage);
            Select _select = new Select(_utilities.FindElement(_subPackage));
            _utilities.WaitUntilTheElementIsAvilable(_subPackage);
            List<WebElement> listOfElement = _driver.driver.findElements(_subPackage);
            int _count = listOfElement.size();
            if (_count <= 0) {
                SelectPackage();
            }
            else {
                int index = _random.nextInt(0, _count);
                _select.selectByIndex(index);
                break;
            }
        }
    }
    public void SelectPackage()
    {
        _utilities.WaitUntilTheElementIsAvilable(_package);
        _utilities.SelectDropdownRandomly(_package,null);
    }
    public void SelectPlatform()
    {
        _utilities.WaitUntilTheElementIsAvilable(_platform);
        _utilities.SelectDropdownRandomly(_platform,null);
    }
    public void SelectRole()
    {
        _utilities.WaitUntilTheElementIsAvilable(_roleName);
        _utilities.SelectDropdownRandomly(_roleName, null);
    }
    public void EnterPhoneNumber()
    {
        String phNumber = faker.phoneNumber().cellPhone(); // Generates a random cell phone number
        _utilities.WaitUntilTheElementIsAvilable(_phoneNumber);
        _utilities.SendKeysCatchStaleElementException(_phoneNumber, phNumber);
    }

    public void EnterEmail()
    {
        String email = firstName+"@.gmail.com";
        _utilities.WaitUntilTheElementIsAvilable(_email);
        _utilities.SendKeysCatchStaleElementException(_email,email);
    }

    public void EnterUserName()
    {
        String userId = firstName + _random.nextInt(0,100);
        _utilities.WaitUntilTheElementIsAvilable(_userId);
        _utilities.SendKeysCatchStaleElementException(_userId,userId);
    }
    public void EnterFirstName()
    {
         firstName = faker.name().firstName();
        _utilities.WaitUntilTheElementIsAvilable(_firstName);
        _utilities.SendKeysCatchStaleElementException(_firstName,firstName);
    }
    public void EnterLastName()
    {
        String lastName = faker.name().lastName();
        _utilities.WaitUntilTheElementIsAvilable(_lastName);
        _utilities.SendKeysCatchStaleElementException(_lastName,lastName);
    }

}
