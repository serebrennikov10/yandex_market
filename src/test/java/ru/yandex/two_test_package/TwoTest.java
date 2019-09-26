package ru.yandex.two_test_package;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.MarketPage;
import ru.yandex.WebDriverSetting;

public class TwoTest extends SetAttributes {

    @Test(description = "Второй тест. ч1")
    @Description(value = "Второй тест. ч1")
    @TmsLink(value = "Two test")
    public void twoTest(){
        System.out.println("-----------------------------------------");
        System.out.println("------------Start Two test(1)------------");
        System.out.println("-----------------------------------------");
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

    @Test(description = "Второй тест. ч2")
    @Description(value = "Второй тест. ч2")
    @TmsLink(value = "Two test")
    public void outPopup(){     //вывод текста из подсказки
        System.out.println("-----------------------------------------");
        System.out.println("------------Start Two test(2)------------");
        System.out.println("-----------------------------------------");
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage()
                .openNoteCategoryNow()
                .sortByPrice()
                .selectNote(1)
                .openNoteSpec();
        System.out.println("Ищу поле с подсказкой");
        driver.findElement(By.xpath("/html/body/div[1]/div[6]/div[1]/.//*[text()='?']/..")).click();
        System.out.println("Текст из подсказки:");
        System.out.println(driver.findElement(By.xpath("//div[contains(@class, 'popup_visibility_visible')]//div[@class='n-hint-button__article']")).getText());

    }

}
