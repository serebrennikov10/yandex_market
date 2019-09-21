package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


//@Test
public class TwoTest extends WebDriverSetting {
/*    String timeWork;
    String valueBattery;
    String valueBatteryPower;
    String cells;
    String typeBattery;*/

    /*public boolean equals(TwoTest note) {
        return this.timeWork     == note.timeWork;
        return this.valueBattery == note.valueBattery;
        return this.valueBatteryPower   == note.valueBatteryPower;
        return this.cells        == note.cells;
        return this.typeBattery  == note.typeBattery;
    }*/

    //@Test
    public void main(String timeWork, String valuebattery, String valuePower, String cells, String typeBattery) {

        //String path = "/html/body/div[1]/div[6]/div[1]/div[12]";
        String path = "/html/body/div[1]/div[6]/div[1]/div[11]";
/*
        //String path = "/html/body/div[1]/div[6]/div[1]/*[text()='Питание']/..";
        //List<WebElement> blockPower = driver.findElements(By.xpath(path));
        List<WebElement> blockPower = driver.findElements(By.xpath(path + "/dl[@class='n-product-spec']")); //работает при конкретном path
        System.out.println("Тянем из элементов списка List:");
        System.out.println(blockPower.get(0).getText());
        System.out.println(blockPower.get(1).getText());
        System.out.println(blockPower.get(2).getText());
        System.out.println(blockPower.get(3).getText());
        System.out.println(blockPower.get(4).getText());*/

        System.out.println("Тянем из поиска по тексту:");
        //      div/ul/li[@data-name='spec']"
        //      By.xpath(".//*[text()='HP']/..")
        //     By.xpath(".//*[contains(text(),'HP')]/..")
        //     By.xpath(".//*[contains(text(),'Время работы')]/..")

        List<WebElement> blockPower2 = driver.findElements(By.xpath(path));
        System.out.println("Выводим все элементы листа 2:");
        for (WebElement blockPowerElements:blockPower2) {

            try {
                timeWork = blockPowerElements.findElement(By.xpath(".//*[contains(text(),'Емкость аккумулятора')]/../..")).getText();
                System.out.println(timeWork);
            } catch(NoSuchElementException ignored) {
                timeWork = "Нет значения";
                System.out.println(timeWork);
            }
            try {
                valuebattery = blockPowerElements.findElement(By.xpath(".//*[contains(text(),'Емкость аккумулятора')]/../..")).getText();
                System.out.println(valuebattery);
            } catch(NoSuchElementException ignored) {
                valuebattery = "Нет значения";
                System.out.println(valuebattery);
            }
            try {
                valuePower = blockPowerElements.findElement(By.xpath(".//*[contains(text(),'Емкость аккумулятора')]/../..")).getText();
                System.out.println(valuePower);
            } catch(NoSuchElementException ignored) {
                valuePower = "Нет значения";
                System.out.println(valuePower);
            }
            try {
                cells = blockPowerElements.findElement(By.xpath(".//*[contains(text(),'Емкость аккумулятора')]/../..")).getText();
                System.out.println(cells);
            } catch(NoSuchElementException ignored) {
                cells = "Нет значения";
                System.out.println(cells);
            }
            try {
                typeBattery = blockPowerElements.findElement(By.xpath(".//*[contains(text(),'Тип аккумулятора')]/../..")).getText();
                System.out.println(typeBattery);
            } catch(NoSuchElementException ignored) {
                typeBattery = "Нет значения";
                System.out.println(typeBattery);
            }




/*       return timeWork;
        return valueBattery;
        return valueBatteryPower;
        return cells;
        return typeBattery;*/

/*            try {

            } catch (){

            }*/


        }

            /*
        WebElement valueBattery = driver.findElement(By.xpath(path+".//*[contains(text(),'Емкость аккумулятора')]/.."));
        System.out.println(valueBattery.getText());
        WebElement valueBatteryPower = driver.findElement(By.xpath(path+".//*[contains(text(),'Емкость аккумулятора (Вт*ч)')]/.."));
        System.out.println(valueBatteryPower.getText());
        WebElement cells = driver.findElement(By.xpath(path+".//*[contains(text(),'Количество ячеек батареи')]/.."));
        System.out.println(cells.getText());
        WebElement typeBattery = driver.findElement(By.xpath(path+".//*[contains(text(),'Тип аккумулятора')]/.."));
        System.out.println(typeBattery.getText());

    */

/*       WebElement timeWork = driver.findElement(By.xpath(path+"/dl[1]"));
        System.out.println(timeWork.getText());
        WebElement valueBattery = driver.findElement(By.xpath(path+"/dl[2]"));
        System.out.println(valueBattery.getText());
        WebElement valueBatteryPower = driver.findElement(By.xpath(path+"/dl[3]"));
        System.out.println(valueBatteryPower.getText());
        WebElement cells = driver.findElement(By.xpath(path+"/dl[4]"));
        System.out.println(cells.getText());
        WebElement typeBattery = driver.findElement(By.xpath(path+"/dl[5]"));
        System.out.println(typeBattery.getText());*/


 /*       TwoTest note1 = new TwoTest();
        note1.timeWork = "1";
        note1.valueBattery = "1";
        note1.valueBatteryPower = "1";
        note1.cells = "1";
        note1.typeBattery = "1";


        TwoTest note2 = new TwoTest();
        note2.timeWork = "1";
        note2.valueBattery = "1";
        note2.valueBatteryPower = "1";
        note2.cells = "1";
        note2.typeBattery = "1";*/

       /* System.out.println(note1.timeWork.equalsIgnoreCase(note2.timeWork));
        System.out.println(note1.valueBattery.equalsIgnoreCase(note2.valueBattery));
        System.out.println(note1.valueBatteryPower.equalsIgnoreCase(note2.valueBatteryPower));
        System.out.println(note1.cells.equalsIgnoreCase(note2.cells));
        System.out.println(note1.typeBattery.equalsIgnoreCase(note2.typeBattery));*/

    }


    public class Atributes{
        String timeWork;
        String valuebattery;
        String valuePower;
        String cells;
        String typeBattery;
    }

    public void NoteAttribute(String[] args){
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        //marketPage.twoTestOpenNote();
        marketPage.openPage();
        //selectNewRegionOnPage();
        marketPage.openAllCategories();
        marketPage.openCompCategory();
        marketPage.openNotebookCategory();
        marketPage.sortByPrice();
        marketPage.selectFirstNote();
        marketPage.openNoteSpec();

        String timeWork;
        String valuebattery;
        //String main(timeWork, valueBattery, valueBatteryPower, cells, typeBattery);

        Atributes note1 = new Atributes();
        note1.timeWork = "1";
        note1.valuebattery = "1";
        note1.valuePower = "1";
        note1.cells = "1";
        note1.typeBattery = "1";


        Atributes note2 = new Atributes();
        note2.timeWork = "1";
        note2.valuebattery = "1";
        note2.valuePower = "1";
        note2.cells = "1";
        note2.typeBattery = "1";
    }



}