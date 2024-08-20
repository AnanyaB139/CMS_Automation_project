package DriverClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driversetup {
    public static  WebDriver driver;

    public  void LaunchBrowser()
    {

        //WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//       switch (webdriver.toLowerCase())
//       {
//           case "chrome":WebDriverManager.chromedriver().setup();
//               driver = new ChromeDriver();
//               break;
//           case "edge": WebDriverManager.edgedriver().setup();
//           driver = new EdgeDriver();
//               break;
//           default:
//               System.out.println("Browser type not supported: " + webdriver);
//               break;
//       }
    }
}
