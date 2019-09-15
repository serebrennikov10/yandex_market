package ru.yandex;



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class WebDriverSetting {


    //public ChromeDriver driver;
    //public FirefoxDriver driver;
    //public InternetExplorerDriver driver;
    public WebDriver driver;

    @BeforeTest
    public void deleteScreenshots(){
        try {
            FileUtils.deleteDirectory(new File("./target/screenshots/"));
            System.out.println("Screenshots removed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void setUp() {
        FileInputStream file;
        Properties property = new Properties();
        try {
            file = new FileInputStream("src/main/resources/config.properties");
            property.load(file);
            String browser = property.getProperty("browser");
            if ((browser.equalsIgnoreCase("firefox")) || (browser.equalsIgnoreCase("ff"))) {
                System.out.println("Browser: " + browser);
                System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("chrome")) {
                System.out.println("Browser: " + browser);
                System.setProperty("webdriver.chrome.driver", "./src/test/drivers/chromedriver76.exe");
                driver = new ChromeDriver();
            } else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer"))
                    || (browser.equalsIgnoreCase("internet explorer"))) {
                System.out.println("Browser: " + browser);
                System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
            } else {
                System.out.println("incorrect browser: " + browser);
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            System.err.println("ERROR: File not found!");
        }


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

    @AfterClass
    public void close() {
        System.out.println("Close driver...");
        //driver.getSessionStorage().clear();
        //driver.getLocalStorage().clear();
        driver.close();
    }

}
