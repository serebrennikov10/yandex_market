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

    @Test(description = "Поиск товаров из файла Excel")
    @Description(value = "Парсинг и поиск товаров из файла Excel")
    @TmsLink(value = "Third test")
    private void noteInFile() throws IOException {
        System.out.println("-----------------------------------------");
        System.out.println("------------Start Third test-------------");
        System.out.println("-----------------------------------------");
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage()
                .readFromExcel("ListNote.xlsx");
    }
}



