package ru.yandex.two_test_package;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.MarketPage;
import ru.yandex.WebDriverSetting;

import java.io.IOException;

public class TwoTest extends WebDriverSetting {

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

    @Test(description = "Второй тест. ч1")
    @Description(value = "Второй тест. ч1")
    @TmsLink(value = "Two test")
    public void twoTest() throws IOException {
        LOGGER.info("------------ T W O  T E S T (1) ------------");
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        Note note1 = new Note();
        marketPage.openPage("https://market.yandex.ru/")
                //.openNoteCategoryNow()
                .searchNote("Ноутбук DELL Inspiron 3780")
                .openNote(2)
                .switchToNewTabs()
                .openSpec()
                .setNewAttributes(note1)
                .switchToOldTabs();

        Note note2 = new Note();
        marketPage.searchNote("Ноутбук Acer ASPIRE 3 (A315-21)")
                .openNote(2)
                .switchToNewTabs()
                .openSpec()
                .setNewAttributes(note2)
                .switchToOldTabs()
                .equals(note1, note2);
    }

}