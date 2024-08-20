package SeleniumUtilities;

import DriverClass.WebDriverExtensions;
import DriverClass.Driversetup;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Utilities {

    Driversetup _driver = new Driversetup();
    Random _random = new Random();
    Actions _action = new Actions(_driver.driver);
    JavascriptExecutor js = (JavascriptExecutor) _driver.driver;


    public void SelectDropdownRandomly(By element, String option)
    {
        Select _select = new Select(FindElement(element));

        if(option == null) {
            WaitUntilTheElementIsAvilable(element);
            List<WebElement> listOfElement = _driver.driver.findElements(element);
            int count = listOfElement.size();
            int index = _random.nextInt(0, count);
            _select.selectByIndex(index);
        }else{
            _select.selectByVisibleText(option);
        }
    }
    public void WaitUntilTheElementIsAvilable(By elementLocator) {
        WebDriverExtensions.waitForElement(_driver.driver, elementLocator, 30, 0.5);
    }
    public static ExpectedCondition<Boolean> passwordTextVisible(final By locator) {
        return driver -> !driver.findElement(locator).getAttribute("value").isEmpty();
    }

    public void SelectRandomElement(By element)
    {
        int i = 3;
        while(i>0){
            List<WebElement> list = _driver.driver.findElements(element);
            int getElement = _random.nextInt(list.size());
            WebElement selectedElement = list.get(getElement);
            try {
                _action.moveToElement(selectedElement, 8, 0).click().perform();
                break;
                //  list.get(getElement).click();
            } catch (StaleElementReferenceException e) {
//            System.out.println("Element is stale. Attempting to find and click again.");
//            list.get(_random.nextInt(list.size())).click();
                i--;
            }
        }
    }
    public String GetText(By element)
    {
        List<WebElement> listOfUsers = _driver.driver.findElements(element);
        int selectedUserIndex = _random.nextInt(listOfUsers.size());
        WebElement selectedUser = listOfUsers.get(selectedUserIndex);
        String selectedUserName = selectedUser.getText();
        return selectedUserName;
    }
    public WebElement SelectRandomly(By element) {
        List<WebElement> listOfUsers = _driver.driver.findElements(element);
        if (listOfUsers.isEmpty()) {
            throw new IllegalStateException("No users found to select.");
        }
        int selectedUserIndex = _random.nextInt(listOfUsers.size());
        WebElement selectedUser = listOfUsers.get(selectedUserIndex);
        selectedUser.click();
        return selectedUser;
    }

    public boolean IsElementIsPresent(By by)
    {
        try
        {
            FindElement(by);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
    public void SetUrl(String url)
    {
        _driver.driver.manage().window().maximize();
        _driver.driver.get(url);
    }
    public void ClickCatchStaleElementException(By element) {
        runWithInterruptHandling(() ->{
            Thread.sleep(1000);
            try {
                WaitUntilTheElementIsAvilable(element);
                if (FindElement(element).isDisplayed()) {
                    FindElement(element).click();
                }
            } catch (Exception ex) {
                if (ex instanceof StaleElementReferenceException) {
                    Refresh();
                    WaitUntilTheElementIsAvilable(element);
                    FindElement(element).click();
                }
                if (ex instanceof ElementClickInterceptedException) {
                    long timeout = System.currentTimeMillis() + 30000;
                    while (ex instanceof ElementClickInterceptedException && System.currentTimeMillis() < timeout) {
                        WaitUntilTheElementIsAvilable(element);
                        try {
                            Thread.sleep(1000);
                            js.executeScript("arguments[0].click();", FindElement(element));
                            return;
                        } catch (ElementClickInterceptedException ex1) {
                            if (System.currentTimeMillis() > timeout) {
                                throw new TimeoutException("Unable to click on element: " + element);
                            }
                        } catch (StaleElementReferenceException ex2) {
                            Refresh();
                            WaitUntilTheElementIsAvilable(element);
                            FindElement(element).click();
                            return;
                        } catch (TimeoutException ex2) {
                            throw new TimeoutException("Unable to click on element: " + element);
                        }
                    }

                }
            }
        });
    }

    public static void runWithInterruptHandling(InterruptibleTask task) {
        try {
            task.run();
        } catch (InterruptedException e) {
            // Handle the interruption, for example, by logging the error and restoring the interrupted status
            System.err.println("Thread was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupted status
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface InterruptibleTask {
        void run() throws Exception;
    }

    public void SendKeysCatchStaleElementException(By element, String key){
        runWithInterruptHandling(() ->{
            Thread.sleep(1000);
            try {
                WaitUntilTheElementIsAvilable(element);
                if (FindElement(element).isDisplayed()) {
                    FindElement(element).sendKeys(key);
                }
            } catch (Exception ex) {
                if(ex instanceof StaleElementReferenceException)
                {
                    Refresh();
                    WaitUntilTheElementIsAvilable(element);
                    FindElement(element).sendKeys(key);
                }
                if(ex instanceof  TimeoutException)
                {
                    throw new TimeoutException("Unable to send keys to element:"+ element);
                }
            }
        });
    }


    public WebElement FindElement (By element)
    {
        return _driver.driver.findElement(element);
    }

    public void Refresh ()
    {
        _driver.driver.navigate().refresh();
    }
}


