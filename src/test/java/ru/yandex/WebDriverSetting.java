package ru.yandex;



import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public abstract class WebDriverSetting {


    public ChromeDriver driver;




    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/drivers/chromedriver76.exe");
        //System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Start driver...");
    }

    @AfterMethod
    public void close() {
        System.out.println("Close driver...");
        driver.getSessionStorage().clear();
        driver.getLocalStorage().clear();
        driver.close();
    }

}
