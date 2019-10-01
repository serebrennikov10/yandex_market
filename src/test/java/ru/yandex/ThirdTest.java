package ru.yandex;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import org.apache.commons.io.FileUtils;
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

//    @Attachment(value = "Page screenshot", type = "image/png")
//    private byte[] saveScreenshotPNG (WebDriver driver) throws IOException {
//        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//    }
/*    @Attachment(value = "Вложение", type = "image/png", fileExtension = ".png")
    static byte[] getBytesAnnotationWithArgs(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("target/screenshots", resourceName));
    }*/

    @Step("Поиск товаров из файла")
    private void noteInFile(String note){}
    //public static void readFile() throws IOException {
    //getBytesAnnotationWithArgs("src/mainTest/resources/ListNote.xlsx");
    //getBytesAnnotationWithArgs("screenshot0.png");
    //getBytesAnnotationWithArgs("screenshot1.png");
    //getBytesAnnotationWithArgs("screenshot2.png");
    //}
    @Test(description = "Поиск товаров из файла Excel")
    @Description(value = "Парсинг и поиск товаров из файла Excel")
    @TmsLink(value = "Third test")
    public void readFromExcel() throws IOException {
        System.out.println("-----------------------------------------");
        System.out.println("------------Start Third test-------------");
        System.out.println("-----------------------------------------");
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage();
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
            marketPage.setListStyleVisibility();
            noteInFile(noteName);
            saveScreenshotPNG (driver);
            captureScreen(i);
        }
    }

    private void captureScreen(Integer i) {
        String path;
        try {
            //WebDriver augmentedDriver = new Augmenter().augment(driver);
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path = "./src/main/resources/screenshots/" + "screenshot" + i + ".png";
            FileUtils.copyFile(screenshot, new File(path));
        }
        catch(IOException e) {
            e.getMessage();
        }
    }

}



