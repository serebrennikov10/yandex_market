package ru.yandex;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public abstract class WebDriverSetting {


    public ChromeDriver driver;
    //public FirefoxDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/drivers/chromedriver76.exe");
        //System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
       // driver = new FirefoxDriver();
        driver.manage().window().maximize();
        System.out.println("Start driver...");
    }


/*    @BeforeMethod
    public void setUp(String browser) {

        System.setProperty("webdriver.chrome.driver", "./src/test/drivers/chromedriver76.exe");
        //System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        //driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        System.out.println("Start driver...");
    }*/

    @AfterMethod
    public void close() {
        System.out.println("Close driver...");
        driver.getSessionStorage().clear();
        driver.getLocalStorage().clear();
        //driver.close();
    }

}
