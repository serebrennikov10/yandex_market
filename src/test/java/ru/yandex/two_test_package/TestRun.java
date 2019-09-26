package ru.yandex.two_test_package;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.MarketPage;
import ru.yandex.WebDriverSetting;

public class TestRun extends SetAttributes {

    @Test(description = "Второй тест")
    @Description(value = "Второй тест")
    @TmsLink(value = "Two test")
    public void twoTest(){
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage()
                .openNoteCategoryNow()
                .selectNote(0)
                .openNoteSpec();
        Note note1 = new Note();
        setNewAttributes(note1);

        marketPage.openPage()
                .openNoteCategoryNow()
                .selectNote(1)
                .openNoteSpec();
        Note note2 = new Note();
        setNewAttributes(note2);

        equals(note1, note2);
    }



}
