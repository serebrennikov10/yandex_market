package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import java.io.IOException;

public class FirstTest extends WebDriverSetting {

    @Test(description = "Первый тест")
    @Description(value = "Первый тест")
    @TmsLink(value = "First test")
    public void test () throws IOException {
        LOGGER.info("------------F I R S T  T E S T------------");
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage("https://market.yandex.ru/");
        marketPage.selectNewRegionOnPage();
        marketPage.openNoteCategoryNow()
                .selectFilterByCost("0", "30000")
                .selectFilterByBrand("HP")
                .selectFilterByBrand("Lenovo")
                .selectFilterByColor("черный")
                .selectFilterByColor("белый")
                .sortByPrice()
                .findNotePriceMinAndMax()
                .outputInfoInList()
                .outputInfoInMap();
    }
}
