package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.logging.Level;

public class ThirdTest extends WebDriverSetting {

    @Test(description = "Поиск товаров из файла Excel")
    @Description(value = "Парсинг и поиск товаров из файла Excel")
    @TmsLink(value = "Third test")
    private void noteInFile() throws IOException {
        LOGGER.log(Level.INFO,"------------ T H I R D  T E S T ------------");
        //System.out.println("-----------------------------------------");
        //System.out.println("------------Start Third test-------------");
        //System.out.println("-----------------------------------------");
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage()
                .readAndSearchFromExcel("ListNote.xlsx");
    }
}



