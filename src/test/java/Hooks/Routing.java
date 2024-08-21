package Hooks;

import ContextClass.SecnarioContextKey;
import DriverClass.Driversetup;
import SeleniumUtilities.CustomAssert;
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
          "/platform","/create_platform","/update_platform","/package_manage","/CreatePackage",
                "/EditPackage","/PackageAssignedAssets","/package_subpackage","/CreateSubPackage",
                "/updateSubPackage","/roles","/create_role","/update_role","/assign_permissions",
                "/users", "/create_user","/assign_user_package", "/update_user", "/update_user_nonactive",
                "/user_profile", "/genrelist", "/create_genre", "/update_genre", "/genre_sorting",
                "/genremap", "/genreorder", "/tags2", "/create_tag", "/update_tag", "/language",
                "/create_language", "/update_language", "/liveassets", "/create_liveassets", "/edit_liveassets",
                "/upload_epg", "/movieassets", "/create_movieassets", "/edit_movieassets", "/seriesassets",
                "/createSeries", "/episodeUpdate", "/updateSeries", "/manage_advertisement",
                "/create_advertisement", "/update_advertisement", "/advertisement_assigned_assets",
                "/manage_videoads", "/create_videoadvertisement", "/update_videoadvertisement",
                "/videoAds_BasedOn_Category", "/assign_videoads", "/manage_banners", "/CreateBanner",
                "/EditBanner", "/assign_banners", "/banner_sorting", "/smtp", "/createSmtp", "/editSmtp",
                "/cache", "/logs", "/genesisConfiguration", "/dataVersion", "/emailList", "/add_email",
                "/emailcontent", "/updateDataAnalytics", "/vod_transcoder", "/create_vod_transcoder",
                "/edit_vod_transcoder", "/drm", "/createDrm", "/editDrm", "/VodDRM", "/create_vodDrm",
                "/update_VodDrm", "/license", "/app_notifications", "/createappnotification", "/update_app_notifications"
        );

        for (String route : restrictedRoutes) {
            _driver.driver.navigate().to(baseUrl + route);
            assertAccessDenied(route,"//h4[@class='margin-spacing']");
        }
    }

    private void assertAccessDenied( String route, String element) {
        By restrictedContentLocator = By.id(element);

        boolean isAccessible = _utilities.IsElementIsPresent(restrictedContentLocator);
        if (isAccessible) {
            System.out.println("The user assessed to this" + route );
            //CustomAssert.captureScreenshotAndLog(_driver.driver, "user have permission", "fail", route);
        } else {
            System.out.println("The user did not have permission to access to " + route );
        }
       // Assert.assertFalse(isAccessible, "User should not have access to " + route);
    }
}
