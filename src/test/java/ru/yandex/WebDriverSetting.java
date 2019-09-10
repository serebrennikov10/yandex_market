package ru.yandex;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class WebDriverSetting {


    public ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/drivers/chromedriver76.exe");
        //System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Start driver...");
    }

    @After
    public void close() {
        System.out.println("Close driver...");
        driver.getSessionStorage().clear();
        driver.getLocalStorage().clear();
        driver.close();
    }

}
