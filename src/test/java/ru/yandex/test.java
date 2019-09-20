package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.List;


public class test extends WebDriverSetting {

    public class Note {
        String timeWork;
    }

    @Test
    public void main(){
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

        Note note1 = new Note();
        note1.timeWork = "note1";

        System.out.println("note1 до изменения: "+note1.timeWork);
        setAttributes(note1);
        System.out.println("note1 после: "+note1.timeWork);

        marketPage.openPage();
        marketPage.openAllCategories();
        marketPage.openCompCategory();
        marketPage.openNotebookCategory();
        marketPage.sortByPrice();
        marketPage.sortPriceByDesc();
        marketPage.selectFirstNote();
        marketPage.openNoteSpec();

        Note note2 = new Note();
        note2.timeWork = "note2";
        System.out.println("note2 до изменения: "+note2.timeWork);
        setAttributes(note2);
        System.out.println("note2 после: "+note2.timeWork);

        System.out.println("Итого note1 после: "+note1.timeWork);
        System.out.println("Итого note2 после: "+note2.timeWork);
    }

    public Note setAttributes(Note note){

        String timeWork = null;
        //String path = "/html/body/div[1]/div[6]/div[1]/div[11]";
        String path = "/html/body/div[1]/div[6]/div[1]/.//*[contains(text(),'Питание')]/..";
        System.out.println("Тянем из поиска по тексту:");

        List<WebElement> blockPower2 = driver.findElements(By.xpath(path));
        System.out.println("Выводим все элементы листа 2:");
        for (WebElement blockPowerElements:blockPower2) {

            try {
                timeWork = blockPowerElements.findElement(By.xpath(".//*[contains(text(),'Емкость аккумулятора')]/../..")).getText();
                System.out.println("text element: "+timeWork);
            } catch(NoSuchElementException ignored) {
                timeWork = "Нет значения";
                System.out.println(timeWork);
            }
        }
        note.timeWork = timeWork;
        return note;
    }



}
