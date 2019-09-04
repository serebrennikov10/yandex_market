package ru.yandex;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

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
        //driver.close();
        driver.getSessionStorage().clear();
        driver.getLocalStorage().clear();
        System.out.println("Close driver...");
    }

}
