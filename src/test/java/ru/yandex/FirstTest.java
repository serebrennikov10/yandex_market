package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class FirstTest extends WebDriverSetting {

    @Test(priority = 1, description = "Первый тест")
    @Description(value = "Первый тест")
    @TmsLink(value = "First test")
    public void test (){
        System.out.println("-----------------------------------------");
        System.out.println("------------Start First test-------------");
        System.out.println("-----------------------------------------");
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage();
        marketPage.selectNewRegionOnPage();
        marketPage.openNoteCategoryNow();
        marketPage.selectFilterByCost("0", "30000");
        marketPage.selectFilterByBrand("HP");
        marketPage.selectFilterByBrand("Lenovo");
        marketPage.selectFilterByColor("черный");
        marketPage.selectFilterByColor("белый");
        marketPage.sortByPrice();
        marketPage.differenceLaptops();
        marketPage.outputInfoAllNotebook();
        marketPage.outputInfoInMap();
    }
}
