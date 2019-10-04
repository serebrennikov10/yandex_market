package ru.yandex;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import ru.yandex.two_test_package.TwoTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class WebDriverSetting {
    public WebDriver driver;
    public Logger LOGGER;
    {
        LOGGER = Logger.getLogger(TwoTest.class.getName());
    }


    @Attachment(value = "{nameScreen}", type = "image/png")
    protected byte[] saveScreenshotPNG(WebDriver driver, String nameScreen) throws IOException {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Вложение", type = "application/json", fileExtension = ".txt")
    public byte [] getBytesAnnotationWithArgs(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/resources", resourceName));
    }




    @Step("Удаление старых сриншотов")
    @BeforeTest
    void deleteScreenshots(){
        try {
            FileUtils.deleteDirectory(new File("./target/screenshots/"));
            FileUtils.deleteDirectory(new File("./src/main/resources/screenshots/"));
            System.out.println("Screenshots removed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("Инициализация настроек драйвера")
    public void browserDriver(String browser){}
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
                System.setProperty("webdriver.gecko.driver", "./src/main/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("chrome")) {
                System.out.println("Browser: " + browser);
                System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
            } else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer"))
                    || (browser.equalsIgnoreCase("internet explorer"))) {
                System.out.println("Browser: " + browser);
                System.setProperty("webdriver.ie.driver", "./src/main/resources/drivers/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
            } else {
                System.out.println("incorrect browser: " + browser);
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            browserDriver(browser);
        }
        catch (IOException e) {
            System.err.println("ERROR: File not found!");
        }


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Start driver...");
    }

    @Step("Закрытие драйвера")
    @AfterClass
    public void close() {
        System.out.println("Close driver...");
        //driver.getSessionStorage().clear();
        //driver.getLocalStorage().clear();
        driver.close();
    }

}
