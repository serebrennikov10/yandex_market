package ru.yandex.two_test_package;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.MarketPage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TwoTest extends SetAttributes {



    @Test(description = "Второй тест. ч1")
    @Description(value = "Второй тест. ч1")
    @TmsLink(value = "Two test")
    public void twoTest() throws IOException {
        LOGGER.log(Level.INFO,"------------ T W O  T E S T (1) ------------");
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        Note note1 = new Note();
        marketPage.openPage()
                .openNoteCategoryNow()
                .openNoteSpecNow(1);
        setNewAttributes(note1);

        Note note2 = new Note();
        marketPage.openPage()
                .openNoteCategoryNow()
                .openNoteSpecNow(2);
        setNewAttributes(note2);

        equals(note1, note2);
    }



    @Test(description = "Второй тест. ч2")
    @Description(value = "Второй тест. ч2")
    @TmsLink(value = "Two test")
    public void outPopup() throws IOException {     //вывод текста из подсказки
        LOGGER.log(Level.INFO,"------------ T W O  T E S T (2) ------------");
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage()
                .openNoteCategoryNow()
                .sortByPrice()
                .openNoteSpecNow(1)
                .outFirstPopup();

    }

}
