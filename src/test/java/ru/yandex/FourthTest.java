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

public class FourthTest extends WebDriverSetting{

    /*    @Attachment(value = "Вложение", type = "application/json", fileExtension = ".txt")
        public static byte[] getBytes(String resourceName) throws IOException {
            return Files.readAllBytes(Paths.get(".src/mainTest/resources", resourceName));
        }*/
    /*@Step("Вывожу response:")
    public static void getResponse(String response) throws IOException {
    }*/
    @Test(description = "Четвертый тест. Отправка запроса.")
    @Description(value = "метод GET")
    @TmsLink(value = "Fourth test")
    public void GetWeather() throws IOException {
        System.out.println("-----------------------------------------");
        System.out.println("------------Start Fourth test------------");
        System.out.println("-----------------------------------------");
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.sendRequestGET("Belgorod");
/*        RestAssured.baseURI =  "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET,  "/Moscow");
        String responseBody =  response.getBody().asString();
        System.out.println("Response:" +  responseBody);
        //getResponse(responseBody);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./src/main/resources/FourthTest.html");
            fileOutputStream.write(responseBody.getBytes());
            fileOutputStream.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }*/


    }

}
