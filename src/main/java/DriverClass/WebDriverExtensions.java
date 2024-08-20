package DriverClass;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverExtensions {
    public static WebElement waitForElement(WebDriver webDriver, By elementBy, int timeout, double pollingInterval) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(timeout));
        webDriverWait.pollingEvery(Duration.ofSeconds((long) pollingInterval));

        WebElement result = webDriverWait.until(driver -> {
            try {
                WebElement webElement = driver.findElement(elementBy);
                if (webElement != null && webElement.isDisplayed()) {
                    return webElement;
                }
                return null;
            } catch (NoSuchElementException ex) {
                return null;
            }
        });
        return result;
    }

    // Overloaded method with default parameters
    public static WebElement waitForElement(WebDriver webDriver, By elementBy) {
        return waitForElement(webDriver, elementBy, 190, 0.5);
    }
}

