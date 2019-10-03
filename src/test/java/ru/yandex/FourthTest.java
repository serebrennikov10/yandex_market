package ru.yandex;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;

public class FourthTest extends WebDriverSetting{

    @Test(description = "Четвертый тест. Отправка запроса.")
    @Description(value = "метод GET")
    @TmsLink(value = "Fourth test")
    public void GetWeather() throws IOException {
        LOGGER.log(Level.INFO,"------------ F O U R T H  T E S T  ------------");
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.sendRequestGET("Belgorod");
    }

}
