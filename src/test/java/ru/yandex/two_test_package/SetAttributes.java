package ru.yandex.two_test_package;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import ru.yandex.WebDriverSetting;

import java.util.List;

public class SetAttributes extends WebDriverSetting {

    public void setNewAttributes(Note note){
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
                valueBatteryPower = blockPowerElements.findElement(By.xpath(".//*[text()='Емкость аккумулятора (Вт*ч)']/../..")).getText();
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

        note.setTimeWork(timeWork);
        note.setValueBattery(valueBattery);
        note.setValueBatteryPower(valueBatteryPower);
        note.setCells(cells);
        note.setTypeBattery(typeBattery);

    }

    public void equals(Note one, Note two){
        System.out.println("Сравнение: "+one.equals(two));
    }
}
