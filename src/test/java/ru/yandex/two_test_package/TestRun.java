package ru.yandex.two_test_package;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.yandex.MarketPage;
import ru.yandex.WebDriverSetting;

public class TestRun extends WebDriverSetting {

    @Test
    public void twoTest(){
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage()
                .openNoteCategoryNow()
                .selectNote(0)
                .openNoteSpec();


        Note note1 = new Note();
        note1.setTimeWork(driver.findElement(By.xpath(".//*[text()='Время работы']/../..")).getText());
        note1.setValueBattery(driver.findElement(By.xpath(".//*[text()='Емкость аккумулятора']/../..")).getText());
        note1.setValueBatteryPower(driver.findElement(By.xpath(".//*[text()='Емкость аккумулятора (Вт*ч)']/../..")).getText());
        note1.setCells(driver.findElement(By.xpath(".//*[text()='Количество ячеек батареи']/../..")).getText());
        note1.setTypeBattery(driver.findElement(By.xpath(".//*[text()='Тип аккумулятора']/../..")).getText());

        System.out.println(note1.getTimeWork());
        System.out.println(note1.getTypeBattery());
        System.out.println(note1.getValueBatteryPower());
        System.out.println(note1.getCells());
        System.out.println(note1.getTypeBattery());





    }


}
