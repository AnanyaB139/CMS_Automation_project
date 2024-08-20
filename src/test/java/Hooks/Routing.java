package Hooks;

import ContextClass.SecnarioContextKey;
import DriverClass.Driversetup;
import SeleniumUtilities.Utilities;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

public class Routing {

    Driversetup _driver = new Driversetup();
    Utilities _utilities = new Utilities();
    public void testRestrictedAccess() {
        String baseUrl = SecnarioContextKey.jwtURL;
        // List of routes that should be restricted
        List<String> restrictedRoutes = List.of(
                "/platform",
                "/create_platform",
                "/update_platform",
                "/package_manage",
                "/CreatePackage",
                "/EditPackage",
                "/PackageAssignedAssets",
                "/package_subpackage",
                "/CreateSubPackage",
                "/updateSubPackage",
                "/roles",
                "/create_role",
                "/update_role",
                "/assign_permissions",
                "/users",
                "/create_user",
                "/assign_user_package"

        );

        for (String route : restrictedRoutes) {
            _driver.driver.navigate().to(baseUrl + route);
            assertAccessDenied(route);
        }
    }

    private void assertAccessDenied(String route) {
        // Customize this locator based on what should be visible if access is granted
        By restrictedContentLocator = By.id("userList");

        boolean isAccessible = _utilities.IsElementIsPresent(restrictedContentLocator);

        // Log the result and assert the outcome
        if (isAccessible) {
            System.out.println("ERROR: Access to " + route + " should be denied, but it is accessible.");
        } else {
            System.out.println("SUCCESS: Access to " + route + " is correctly denied.");
        }

        // Assert that the element is not present, meaning access is denied
        Assert.assertFalse(isAccessible, "User should not have access to " + route);
    }
}
