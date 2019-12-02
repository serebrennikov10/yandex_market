package ru.yandex.two_test_package;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.MarketPage;
import ru.yandex.WebDriverSetting;

import java.io.IOException;

public class TwoTestPartTwo extends WebDriverSetting {

    @Test(description = "Второй тест. ч2")
    @Description(value = "Второй тест. ч2")
    @TmsLink(value = "Two test")
    public void outPopup() throws IOException {
        LOGGER.info("------------ T W O  T E S T (2) ------------");
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage("https://market.yandex.ru/")
                .searchNote("Ноутбук DIGMA EVE 101 (Intel Atom x5 Z8350")
                .openNote(1)
                .switchToNewTabs()
                .openSpec()
                .outFirstPopup();
    }
}
