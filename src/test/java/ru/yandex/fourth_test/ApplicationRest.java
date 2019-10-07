package ru.yandex.fourth_test;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.yandex.MarketPage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class ApplicationRest {
    Logger LOGGER;
    {
        LOGGER = Logger.getLogger(ApplicationRest.class.getName());
    }

    @Step("Отправляю запрос GET")
    public void getWeather(String region) throws IOException {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, region);
        String responseBody = response.getBody().asString();
        LOGGER.info(responseBody);
        outputResponseInFile(responseBody);
    }

    @Step("Вывожу response из файла")
    public void outputResponseInFile(String responseBody) throws IOException {
        try {
            FileOutputStream file = new FileOutputStream("./src/main/resources/FourthTest.html");
            file.write(responseBody.getBytes());
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        MarketPage.getBytesAnnotationWithArgs("/FourthTest.html");
    }
}