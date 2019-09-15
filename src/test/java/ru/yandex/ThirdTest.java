package ru.yandex;

import io.qameta.allure.Description;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ThirdTest extends WebDriverSetting {
/*    private WebDriver driver;
        public ThirdTest(WebDriver driver) {
        this.driver = driver;
    }*/

    @Test(priority = 1, description = "Открытие страницы")
    @Description(value = "Тест проверяет открытие страницы")
    public void openYandex(){
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage();
    }

/*    @Test(priority = 2, description = "Поиск товара")
    @Description(value = "Тест проверяет поиск через поисковую строку")
    public void findElementsInYandex(){


            WebElement searchInputLine = driver.findElement(By.id("header-search"));
            searchInputLine.sendKeys("Ноутбук HP 17-ca0125ur");
            searchInputLine.sendKeys(Keys.ENTER);
    }*/

    @Test(priority = 2, description = "Поиск товара")
    public void readFromExcel() throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream("src/main/resources/ListNote.xlsx"));
        XSSFSheet myExcelSheet = myExcelBook.getSheet("book1");
        for(int i=0;i<3;i++) {
            XSSFRow row = myExcelSheet.getRow(i);
            String noteName = row.getCell(0).getStringCellValue();
            System.out.println("Ищу: "+noteName);
            WebElement searchInputLine = driver.findElement(By.id("header-search"));
            searchInputLine.clear();
            searchInputLine.sendKeys(noteName);
            searchInputLine.sendKeys(Keys.ENTER);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            captureScreen();

        }
    }

    public void captureScreen() {
        String path;
        try {
            //WebDriver augmentedDriver = new Augmenter().augment(driver);
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path = "./target/screenshots/" + screenshot.getName();
            FileUtils.copyFile(screenshot, new File(path));
        }
        catch(IOException e) {
            e.getMessage();
        }
        //return path;
    }









}
