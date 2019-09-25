package ru.yandex.two_test_package;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import ru.yandex.WebDriverSetting;

import java.util.List;

public class Note  {
    private String timeWork;
    private String valueBattery;
    private String valueBatteryPower;
    private String cells;
    private String typeBattery;

    public Note(String timeWork, String valueBattery, String valueBatteryPower, String cells, String typeBattery){
        this.timeWork = timeWork;
        this.valueBattery = valueBattery;
        this.valueBatteryPower = valueBatteryPower;
        this.cells = cells;
        this.typeBattery = typeBattery;
    }

    public Note(){
    }

    public void setTimeWork(String timeWork){
        if (timeWork != null) {
            this.timeWork = timeWork;
        } else {
            this.timeWork = "Время работы "+"\n"+"Нет значения";
        }
    }
    public String getTimeWork(){
        return timeWork;
    }

    public void setValueBattery(String valueBattery){
        if (valueBattery != null) {
            this.valueBattery = valueBattery;
        } else {
            this.valueBattery = "Емкость аккумулятора "+"\n"+"Нет значения";
        }
    }
    public String getValueBattery(){
        return valueBattery;
    }

    public void setValueBatteryPower(String valueBatteryPower){
        if (valueBatteryPower != null) {
            this.valueBatteryPower = valueBatteryPower;
        } else {
            this.valueBatteryPower = "Емкость аккумулятора (Вт*ч) "+"\n"+"Нет значения";
        }
    }
    public String getValueBatteryPower(){
        return valueBatteryPower;
    }

    public void setCells(String cells){
        if (cells != null){
            this.cells = cells;
        } else {
            this.cells = "Количество ячеек батареи "+"\n"+"Нет значения";
        }
    }
    public String getCells(){
        return cells;
    }

    public void setTypeBattery(String typeBattery){
        if (typeBattery != null) {
            this.typeBattery = typeBattery;
        } else {
            this.typeBattery = "Тип аккумулятора "+"\n"+"Нет значения";
        }
    }
    public String getTypeBattery(){
        return typeBattery;
    }

private void set(){

}


}
