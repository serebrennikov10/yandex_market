package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;


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
    public void main(){
                MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
                marketPage.twoTestOpenNote();
                marketPage.selectFirstNote();
                marketPage.openNoteSpec();



        WebElement webTimeWork = driver.findElement(By.xpath("/html/body/div[1]/div[6]/div[1]/div[12]/dl[1]"));
        System.out.println(webTimeWork.getText());



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
            }


}
