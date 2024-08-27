package Page;

import DriverClass.Driversetup;
import SeleniumUtilities.CustomAssert;
import SeleniumUtilities.Utilities;
import com.github.javafaker.Faker;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static DriverClass.Driversetup.driver;

public class RolePage {
    Utilities _seleniumUtilities = new Utilities();
    Faker _faker = new Faker();
    boolean isDeleted = true;
    Driversetup _driver = new Driversetup();
    public By _role = By.xpath("//span[text()='Role']");
    public By _createRole = By.xpath("//span[@title='Create Role']/a");
    public By _enterRole = By.xpath("//input[@type='text']");
    public By _createBtn = By.xpath("//button[text()='Create']");
    public By _cancelBtn = By.xpath("//button[text()='Cancel']");
    public By _rolesList = By.xpath("//table[@class='table table-bordered mb-4']/tbody/tr/td[1]");
    public By _searchField = By.xpath("//input[@type='search']");
    public By _edit = By.xpath("//span[@title='Edit Role']");
    public By _assignPermission = By.xpath("//span[@title='Assign Permission']");
    public By _delete = By.xpath("//span[@title='Delete Role']");
   // public By _pageTitle = By.xpath("//h1[@class='margin_spacing' and @style='text-align: initial;']/text()[1]");
    public By _button = By.xpath("//button[@type='button']");
    public By _migrateRoleOPtions = By.xpath("//select[@class='swal2-select']");
    public By _migrateBtn = By.xpath("//button[text()='Migrate']");
    public By _yesPopup = By.xpath("//button[text()='Yes']");
    public By _successMessage = By.xpath("//div[contains(text(),'Role deleted successfully!')]");
   public void VarifySuccessMessage(){
       if(_seleniumUtilities.isSuccessMessagePresent(driver, _successMessage) == true){
           CustomAssert.captureScreenshotAndLog(driver,"Message appeared", "pass","SuccessMessage");
       }
       else {
           System.out.println(" success popup did not appear");
       }
   }
    public void ClickOnYesBtn(){
        _seleniumUtilities.WaitUntilTheElementIsAvilable(_yesPopup);
        _seleniumUtilities.ClickCatchStaleElementException(_yesPopup);
    }
    public void ClickOnMigrateBtn(){
        _seleniumUtilities.WaitUntilTheElementIsAvilable(_migrateBtn);
        _seleniumUtilities.ClickCatchStaleElementException(_migrateBtn);
    }
    public void SelectRoleFromMigrateRoles(){
        _seleniumUtilities.WaitUntilTheElementIsAvilable(_migrateRoleOPtions);
        _seleniumUtilities.SelectDropdownRandomly(_migrateRoleOPtions, null);
    }
    public List<String> RolesList(String rolename){
       return _seleniumUtilities.GetDataFromList(_rolesList);
    }
    public void ClickOnDelete(String roleName){
        _seleniumUtilities.WaitUntilTheElementIsAvilable(By.xpath("//td[text()='" + roleName + "']//following-sibling::td/span[@title='Delete Role']"));
        _seleniumUtilities.ClickCatchStaleElementException(By.xpath("//td[text()='" + roleName + "']//following-sibling::td/span[@title='Delete Role']"));
    }
    public void ClickOnAssignPermission(){
        _seleniumUtilities.WaitUntilTheElementIsAvilable(_assignPermission);
        _seleniumUtilities.ClickCatchStaleElementException(_assignPermission);
    }
    public void ClickOnEdit(){
        _seleniumUtilities.WaitUntilTheElementIsAvilable(_edit);
        _seleniumUtilities.ClickCatchStaleElementException(_edit);
    }
    public void SearchRoles(String role){
      _seleniumUtilities.WaitUntilTheElementIsAvilable(_searchField);
      _seleniumUtilities.SendKeysCatchStaleElementException(_searchField,role);
    }
    public void ClearSearchField(){
        _seleniumUtilities.WaitUntilTheElementIsAvilable(_searchField);
        _seleniumUtilities.FindElement(_searchField).clear();
    }
    public void ClickOnCancelBtn(){
        _seleniumUtilities.WaitUntilTheElementIsAvilable(_cancelBtn);
        _seleniumUtilities.ClickCatchStaleElementException(_cancelBtn);
    }
    public void CLickOnRoleMenu(){
        _seleniumUtilities.WaitUntilTheElementIsAvilable(_role);
        _seleniumUtilities.ClickCatchStaleElementException(_role);
    }
    public void ClickOnCreateRole(){
        _seleniumUtilities.WaitUntilTheElementIsAvilable(_createRole);
        _seleniumUtilities.ClickCatchStaleElementException(_createRole);
    }
    public void EnterRole(String roleName){
        _seleniumUtilities.WaitUntilTheElementIsAvilable(_enterRole);
        _seleniumUtilities.SendKeysCatchStaleElementException(_enterRole, roleName);
    }
    public void ClickOnCreateBtn(){
        _seleniumUtilities.WaitUntilTheElementIsAvilable(_createBtn);
        _seleniumUtilities.ClickCatchStaleElementException(_createBtn);
    }
    public String IfAlertMessagePresent(String roleName)
    {
        String newRole = roleName;
        if(_seleniumUtilities.Alert() == true){
            _seleniumUtilities.WaitUntilTheElementIsAvilable(_enterRole);
            _seleniumUtilities.FindElement(_enterRole).clear();
            newRole =  ReCreateRole(roleName);
            DeleteRole(roleName);
        }
        return newRole;
    }
    public void DeleteRole(String roleName){
        SearchRoles(roleName);
        ClickOnDelete(roleName);
        SelectRoleFromMigrateRoles();
        ClickOnMigrateBtn();
        ClickOnYesBtn();
        ClearSearchField();
        SearchRoles(roleName);
        if(_seleniumUtilities.IsElementIsPresent(By.xpath("//paragraph[text()='No matching records found']"))){
            CustomAssert.captureScreenshotAndLog(driver, "The data is not deleted form the table", "fail", "NotDeletedData");
        }
        else{
           List<String> list =  _seleniumUtilities.GetDataFromList(_rolesList);
           for(String element : list){
               if(roleName.equals(element)){
                   System.out.println( roleName + "  :" + "the data is not been deleted");
                   isDeleted = false;
                   break;
               }
           }
            if (isDeleted) {
                System.out.println(roleName + "  :" + "the data has been successfully deleted");
            }
        }
    }
    public String ReCreateRole(String roleName){
       // ClickOnCreateRole();
        String role = _faker.name().firstName();
        EnterRole(roleName + role);
        ClickOnCreateBtn();
        return roleName + role;
    }







}
