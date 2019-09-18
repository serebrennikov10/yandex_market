package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.List;


//@Test
public class TwoTest extends WebDriverSetting {
    String timeWork;
    String valuebattery;
    String valuePower;
    String cells;
    String typeBattery;

    public boolean equals(TwoTest note) {
        return this.timeWork     == note.timeWork;
        /*return this.valuebattery == note.valuebattery;
        return this.valuePower   == note.valuePower;
        return this.cells        == note.cells;
        return this.typeBattery  == note.typeBattery;*/
    }

    @Test
    public void main() {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.twoTestOpenNote();
        marketPage.selectFirstNote();
        marketPage.openNoteSpec();

        String path = "/html/body/div[1]/div[6]/div[1]/div[12]";
        List<WebElement> blockPower = driver.findElements(By.xpath(path + "/dl[@class='n-product-spec']"));
        System.out.println("Тянем из элементов списка List:");
        System.out.println(blockPower.get(0).getText());
        System.out.println(blockPower.get(1).getText());
        System.out.println(blockPower.get(2).getText());
        System.out.println(blockPower.get(3).getText());
        System.out.println(blockPower.get(4).getText());

        System.out.println("Тянем из поиска по тексту:");

        //      div/ul/li[@data-name='spec']"
        //      By.xpath(".//*[text()='HP']/..")
        //     By.xpath(".//*[contains(text(),'HP')]/..")
        //     By.xpath(".//*[contains(text(),'Время работы')]/..")

         List<WebElement> blockPower2 = driver.findElements(By.xpath(path + "/dl[@class='n-product-spec']"));
        System.out.println("Выводим все элементы листа 2"+blockPower2);
        for (WebElement blockPowerElements:blockPower2) {
            String timeWork = blockPowerElements.findElement(By.xpath(".//*[text()='Время работы']/../..")).getText();
            System.out.println(timeWork);
        }

            /*
       WebElement valuebattery = driver.findElement(By.xpath(path+".//*[contains(text(),'Емкость аккумулятора')]/.."));
        System.out.println(valuebattery.getText());
        WebElement valuePower = driver.findElement(By.xpath(path+".//*[contains(text(),'Емкость аккумулятора (Вт*ч)')]/.."));
        System.out.println(valuePower.getText());
        WebElement cells = driver.findElement(By.xpath(path+".//*[contains(text(),'Количество ячеек батареи')]/.."));
        System.out.println(cells.getText());
        WebElement typeBattery = driver.findElement(By.xpath(path+".//*[contains(text(),'Тип аккумулятора')]/.."));
        System.out.println(typeBattery.getText());

    */

/*        WebElement timeWork = driver.findElement(By.xpath(path+"/dl[1]"));
        System.out.println(timeWork.getText());
        WebElement valuebattery = driver.findElement(By.xpath(path+"/dl[2]"));
        System.out.println(valuebattery.getText());
        WebElement valuePower = driver.findElement(By.xpath(path+"/dl[3]"));
        System.out.println(valuePower.getText());
        WebElement cells = driver.findElement(By.xpath(path+"/dl[4]"));
        System.out.println(cells.getText());
        WebElement typeBattery = driver.findElement(By.xpath(path+"/dl[5]"));
        System.out.println(typeBattery.getText());*/

        TwoTest note1 = new TwoTest();
        note1.timeWork = "1";
        note1.valuebattery = "1";
        note1.valuePower = "1";
        note1.cells = "1";
        note1.typeBattery = "1";


        TwoTest note2 = new TwoTest();
        note2.timeWork = "1";
        note2.valuebattery = "1";
        note2.valuePower = "1";
        note2.cells = "1";
        note2.typeBattery = "1";

        System.out.println(note1.timeWork.equalsIgnoreCase(note2.timeWork));
        System.out.println(note1.valuebattery.equalsIgnoreCase(note2.valuebattery));
        System.out.println(note1.valuePower.equalsIgnoreCase(note2.valuePower));
        System.out.println(note1.cells.equalsIgnoreCase(note2.cells));
        System.out.println(note1.typeBattery.equalsIgnoreCase(note2.typeBattery));

    }
}