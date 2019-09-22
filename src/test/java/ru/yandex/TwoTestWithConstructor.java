package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.List;

public class TwoTestWithConstructor extends WebDriverSetting {

    public class Note {
        String timeWork, valueBattery, valueBatteryPower, cells, typeBattery;

        public Note(String timeWork, String valueBattery){
            this.timeWork = timeWork;
            this.valueBattery = valueBattery;
/*            this.valueBatteryPower = valueBatteryPower;
            this.cells = cells;
            this.typeBattery = typeBattery;*/
        }

        public Note(String timeWork, String valueBattery, String valueBatteryPower){
            this.timeWork = timeWork;
            this.valueBattery = valueBattery;
            this.valueBatteryPower = valueBatteryPower;
/*            this.cells = cells;
            this.typeBattery = typeBattery;*/
        }
    }

    public void setAttributes(Note note){
        String timeWork = null;
        String valueBattery = null;
        String valueBatteryPower = null;
        String cells = null;
        String typeBattery = null;
        String path = "/html/body/div[1]/div[6]/div[1]/.//*[text()='Питание']/..";

        List<WebElement> blockPowerList = driver.findElements(By.xpath(path));
        for (WebElement blockPowerElements:blockPowerList) {
            try {
                timeWork = blockPowerElements.findElement(By.xpath(".//*[text()='Время работы']/../..")).getText();
            } catch(NoSuchElementException ignored) {
                timeWork = "Время работы "+"\n"+"Нет значения";
            }
            try {
                valueBattery = blockPowerElements.findElement(By.xpath(".//*[text()='Емкость аккумулятора']/../..")).getText();
            } catch(NoSuchElementException ignored) {
                valueBattery = "Емкость аккумулятора "+"\n"+"Нет значения";
            }
            try {
                valueBatteryPower = blockPowerElements.findElement(By.xpath(".//*[contains(text(),'Емкость аккумулятора (Вт*ч)')]/../..")).getText();
            } catch(NoSuchElementException ignored) {
                valueBatteryPower = "Емкость аккумулятора (Вт*ч) "+"\n"+"Нет значения";
            }
            try {
                cells = blockPowerElements.findElement(By.xpath(".//*[text()='Количество ячеек батареи']/../..")).getText();
            } catch(NoSuchElementException ignored) {
                cells = "Количество ячеек батареи "+"\n"+"Нет значения";
            }
            try {
                typeBattery = blockPowerElements.findElement(By.xpath(".//*[text()='Тип аккумулятора']/../..")).getText();
            } catch(NoSuchElementException ignored) {
                typeBattery = "Тип аккумулятора "+"\n"+"Нет значения";
            }
        }

        note.timeWork = timeWork;
        note.valueBattery = valueBattery;
        note.valueBatteryPower = valueBatteryPower;
        note.cells = cells;
        note.typeBattery = typeBattery;
        return;
    }

    @Test
    public void main(){
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openNoteCategoryNow();
        //marketPage.sortByPrice();
        marketPage.selectNote(0);
        marketPage.openNoteSpec();

        String timeWork = "Пока еще нет значения";
        String valueBattery = "Пока еще нет значения";
        String valueBatteryPower = "Пока еще нет значения";
        String cells = "Пока еще нет значения";
        String typeBattery = "Пока еще нет значения";


        Note note1 = new Note(timeWork, valueBattery);
        setAttributes(note1);
        System.out.println("-------------параметры первого ноутбука-----------------");
        System.out.println(note1.timeWork);
        System.out.println(note1.valueBattery);
        System.out.println(note1.valueBatteryPower);
        System.out.println(note1.cells);
        System.out.println(note1.typeBattery);


        marketPage.openNoteCategoryNow();
        //marketPage.sortByPrice();
        marketPage.selectNote(1);
        marketPage.openNoteSpec();
        Note note2 = new Note(timeWork, valueBattery, valueBatteryPower);
        setAttributes(note2);
        System.out.println("-------------параметры второго ноутбука-----------------");
        System.out.println(note2.timeWork);
        System.out.println(note2.valueBattery);
        System.out.println(note2.valueBatteryPower);
        System.out.println(note2.cells);
        System.out.println(note2.typeBattery);


        System.out.println("---------сравнение характеристик по отдельности----------");
        System.out.println("Время работы: "+note1.timeWork.equalsIgnoreCase(note2.timeWork));
        System.out.println("Емкость аккумулятора: "+note1.valueBattery.equalsIgnoreCase(note2.valueBattery));
        System.out.println("Емкость аккумулятора (Вт*ч): "+note1.valueBatteryPower.equalsIgnoreCase(note2.valueBatteryPower));
        System.out.println("Количество ячеек батареи: "+note1.cells.equalsIgnoreCase(note2.cells));
        System.out.println("Тип аккумулятора: "+note1.typeBattery.equalsIgnoreCase(note2.typeBattery));
        System.out.println("----------------сравнение объектов----------------------");
        System.out.println(note1.equals(note2));
    }
}
