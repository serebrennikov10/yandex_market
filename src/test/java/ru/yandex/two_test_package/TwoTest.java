package ru.yandex.two_test_package;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.MarketPage;

import java.io.IOException;

public class TwoTest extends SetAttributes {

    @Test(description = "Второй тест. ч1")
    @Description(value = "Второй тест. ч1")
    @TmsLink(value = "Two test")
    public void twoTest() throws IOException {
        System.out.println("-----------------------------------------");
        System.out.println("------------Start Two test(1)------------");
        System.out.println("-----------------------------------------");
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage()
                .openNoteCategoryNow()
                .openNoteSpec(0);
        Note note1 = new Note();
        setNewAttributes(note1);

        marketPage.openPage()
                .openNoteCategoryNow()
                .openNoteSpec(1);
        Note note2 = new Note();
        setNewAttributes(note2);

        equals(note1, note2);
    }



    @Test(description = "Второй тест. ч2")
    @Description(value = "Второй тест. ч2")
    @TmsLink(value = "Two test")
    public void outPopup() throws IOException {     //вывод текста из подсказки
        System.out.println("-----------------------------------------");
        System.out.println("------------Start Two test(2)------------");
        System.out.println("-----------------------------------------");

        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage()
                .openNoteCategoryNow()
                .sortByPrice()
                .openNoteSpec(0)
                .outFirstPopup();


    }

}
