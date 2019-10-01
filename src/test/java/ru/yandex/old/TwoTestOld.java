package ru.yandex.old;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.MarketPage;
import ru.yandex.WebDriverSetting;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class TwoTestOld extends WebDriverSetting {
    //Actions MoveToElement = new Actions(driver);
    public class Note {
        String timeWork, valueBattery, valueBatteryPower, cells, typeBattery;



    }

    private void setAttributes(Note note){

        String timeWork = null;
        String valueBattery = null;
        String valueBatteryPower = null;
        String cells = null;
        String typeBattery = null;
        //String path = "/html/body/div[1]/div[6]/div[1]/div[11]";
        String path = "/html/body/div[1]/div[6]/div[1]/.//*[text()='Питание']/..";
        System.out.println("Тянем из поиска по тексту:");

        List<WebElement> blockPowerList = driver.findElements(By.xpath(path));
        System.out.println("Выводим все элементы листа:");
        for (WebElement blockPowerElements:blockPowerList) {
            //By.xpath(".//*[text()='HP']/..")
            //By.xpath(".//*[contains(text(),'Время работы')]/../..")
            try {
                timeWork = blockPowerElements.findElement(By.xpath(".//*[text()='Время работы']/../..")).getText();
                System.out.println("text element: "+timeWork);
            } catch(NoSuchElementException ignored) {
                timeWork = "Время работы: Нет значения";
                System.out.println(timeWork);
            }
            try {
                valueBattery = blockPowerElements.findElement(By.xpath(".//*[text()='Емкость аккумулятора']/../..")).getText();
                System.out.println(valueBattery);
            } catch(NoSuchElementException ignored) {
                valueBattery = "Емкость аккумулятора: Нет значения";
                System.out.println(valueBattery);
            }
            try {
                valueBatteryPower = blockPowerElements.findElement(By.xpath(".//*[contains(text(),'Емкость аккумулятора (Вт*ч)')]/../..")).getText();
                System.out.println(valueBatteryPower);
            } catch(NoSuchElementException ignored) {
                valueBatteryPower = "Емкость аккумулятора (Вт*ч): Нет значения";
                System.out.println(valueBatteryPower);
            }
            try {
                cells = blockPowerElements.findElement(By.xpath(".//*[text()='Количество ячеек батареи']/../..")).getText();
                System.out.println(cells);
            } catch(NoSuchElementException ignored) {
                cells = "Количество ячеек батареи: Нет значения";
                System.out.println(cells);
            }
            try {
                typeBattery = blockPowerElements.findElement(By.xpath(".//*[text()='Тип аккумулятора']/../..")).getText();
                System.out.println(typeBattery);
            } catch(NoSuchElementException ignored) {
                typeBattery = "Тип аккумулятора: Нет значения";
                System.out.println(typeBattery);
            }
        }


        note.timeWork = timeWork;
        note.valueBattery = valueBattery;
        note.valueBatteryPower = valueBatteryPower;
        note.cells = cells;
        note.typeBattery = typeBattery;

        //return note;
    }


    //@Test
    public void main() throws IOException {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openNoteCategoryNow();
        marketPage.selectNote(0);
        marketPage.openNoteSpec();

        Note note1 = new Note();
        //note1.timeWork = "note1";

        //System.out.println("note1 до изменения: "+note1);
        setAttributes(note1);
        System.out.println("note1 после: ");
        System.out.println(note1.timeWork);
        System.out.println(note1.valueBattery);
        System.out.println(note1.valueBatteryPower);
        System.out.println(note1.cells);
        System.out.println(note1.typeBattery);

        marketPage.openPage();
        marketPage.openAllCategories();
        marketPage.openCompCategory();
        marketPage.openNotebookCategory();
        marketPage.sortByPrice();
        marketPage.sortPriceByDesc();
        marketPage.selectNote(1);
        marketPage.openNoteSpec();

        Note note2 = new Note();
        //note2.timeWork = "note2";
        //System.out.println("note2 до изменения: "+note2.timeWork);
        setAttributes(note2);
        System.out.println("note2 после: ");
        System.out.println(note2.timeWork);
        System.out.println(note2.valueBattery);
        System.out.println(note2.valueBatteryPower);
        System.out.println(note2.cells);
        System.out.println(note2.typeBattery);


        System.out.println("Итого note1 после: "+note1.timeWork);
        System.out.println("Итого note2 после: "+note2.timeWork);
        System.out.println("Сравниваю характеристики блока 'Питание': ");
        System.out.println("Время работы: "+note1.timeWork.equalsIgnoreCase(note2.timeWork));
        System.out.println("Емкость аккумулятора: "+note1.valueBattery.equalsIgnoreCase(note2.valueBattery));
        System.out.println("Емкость аккумулятора (Вт*ч): "+note1.valueBatteryPower.equalsIgnoreCase(note2.valueBatteryPower));
        System.out.println("Количество ячеек батареи: "+note1.cells.equalsIgnoreCase(note2.cells));
        System.out.println("Тип аккумулятора: "+note1.typeBattery.equalsIgnoreCase(note2.typeBattery));



    }






    //@Test
    public void out() throws IOException {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage();
        marketPage.openAllCategories();
        marketPage.openCompCategory();
        marketPage.openNotebookCategory();
        marketPage.setListStyleVisibility();
        marketPage.sortByPrice();
        marketPage.sortPriceByAsc();
        marketPage.selectNote(1);
        marketPage.openNoteSpec();

        System.out.println("Ищу поле с подсказкой");
        //WebElement help = driver.findElement(By.className("link link_pseudo_yes n-hint-button__handle i-bem link_js_inited _popup-destructor _popup-destructor_js_inited"));


        String parentHandle = driver.getWindowHandle();

        WebElement help = driver.findElement(By.xpath("/html/body/div[1]/div[6]/div[1]/.//*[text()='?']/.."));
        //MoveToElement.moveToElement(help).perform();
        //System.out.println("кликаю");
        help.click();

        //System.out.println("Ищу текст");
        //WebElement hintText = driver.findElement(By.className("n-hint-button__inner"));
        //WebElement hintText = driver.findElement(By.xpath("//div[@class='n-hint-button__article']"));


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(String childHandle : driver.getWindowHandles()){
            if (!childHandle.equals(parentHandle)){
                driver.switchTo().window(childHandle);
            }
        }

        WebElement hintText = driver.findElement(By.xpath("//div[@class='popup__content']"));
        System.out.println("Вывожу информацию:");
        System.out.println(hintText.getSize());
        System.out.println(hintText.getText());

        //driver.switchTo().window(parentHandle);



        //WebElement hintText = driver.findElement(By.xpath("/html/body/div[3]/div/div/div"));

    /*List <WebElement> hintText = driver.findElements(By.xpath("/html/body/div[3]/div/div/div"));
    hintText.size();*/
/*        System.out.println("Вывожу информацию:");
        System.out.println(hintText.getText());*/
        //System.out.println(hintText.getText());


    }


//@Test
    public void out2() throws IOException {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
    marketPage.openPage()
            .openNoteCategoryNow()
            .sortByPrice()
            .selectNote(0)
            .openNoteSpec();

        System.out.println("Ищу поле с подсказкой");
        //WebElement help = driver.findElement(By.className("link link_pseudo_yes n-hint-button__handle i-bem link_js_inited _popup-destructor _popup-destructor_js_inited"));


        //String parentHandle = driver.getWindowHandle();
        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window

        WebElement help = driver.findElement(By.xpath("/html/body/div[1]/div[6]/div[1]/.//*[text()='?']/.."));
        help.click();

        //System.out.println("Ищу текст");
        //WebElement hintText = driver.findElement(By.className("n-hint-button__inner"));
        //WebElement hintText = driver.findElement(By.xpath("//div[@class='n-hint-button__article']"));



        String subWindowHandler = null;

        Set handles = driver.getWindowHandles(); // get all window handles
        Iterator iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = (String) iterator.next();
        }
        driver.switchTo().window(subWindowHandler); // switch to popup window

// Now you are in the popup window, perform necessary actions here
        WebElement hintText = driver.findElement(By.xpath("//div[@class='popup__content']"));
        String text = hintText.getText();
        System.out.println("Вывожу информацию:");
        System.out.println(hintText.getSize());
        System.out.println(text);


        driver.switchTo().window(parentWindowHandler);  // switch back to parent window



        //driver.switchTo().window(parentHandle);



        //WebElement hintText = driver.findElement(By.xpath("/html/body/div[3]/div/div/div"));

    /*List <WebElement> hintText = driver.findElements(By.xpath("/html/body/div[3]/div/div/div"));
    hintText.size();*/
/*        System.out.println("Вывожу информацию:");
        System.out.println(hintText.getText());*/
        //System.out.println(hintText.getText());


    }




    public void outPopup() throws IOException {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage()
                .openNoteCategoryNow()
                .sortByPrice()
                .selectNote(1)
                .openNoteSpec();

        System.out.println("Ищу поле с подсказкой");
        //WebElement help = driver.findElement(By.className("link link_pseudo_yes n-hint-button__handle i-bem link_js_inited _popup-destructor _popup-destructor_js_inited"));
        WebElement help = driver.findElement(By.xpath("/html/body/div[1]/div[6]/div[1]/.//*[text()='?']/.."));
        help.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(driver.findElement(By.xpath("//div[@class='n-hint-button__article']")).getText());
        //System.out.println(driver.findElement(By.xpath("//div[contains(@class, 'popup_visibility_visible')]//div[@class='n-hint-button__article']")).getText());



        //System.out.println("Ищу текст");
        //WebElement hintText = driver.findElement(By.className("n-hint-button__inner"));
        int sizeFrame = driver.findElements(By.xpath("//iframe[@class='popup__under']")).size();
        System.out.println("Всего фреймов "+sizeFrame);
        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='popup__under']"));
        driver.switchTo().frame(iframe);

        System.out.println("переключение прошло");
        System.out.println("текст из фрейма:");
        System.out.println(iframe.getText());


        int sizeHint = driver.findElements(By.xpath("//div[@class='n-hint-button__article']")).size();
        System.out.println("Всего хинтов "+sizeHint);
        WebElement hintText = driver.findElement(By.xpath("//iframe[@class='popup__under']/.."));
        //WebElement hintText = driver.findElement(By.xpath("//div[@class='n-hint-button__article']"));
        //WebElement hintText = driver.findElement(By.xpath("/html/body/div[4]/div/div/div"));

        String text = hintText.getText();
        System.out.println("Вывожу информацию:");
        System.out.println(hintText.getSize());
        System.out.println(text);

        driver.switchTo().defaultContent(); //вернуться к основной странице

        //driver.switchTo().window(parentHandle);



        //WebElement hintText = driver.findElement(By.xpath("/html/body/div[3]/div/div/div"));

    /*List <WebElement> hintText = driver.findElements(By.xpath("/html/body/div[3]/div/div/div"));
    hintText.sizeHint();*/
/*        System.out.println("Вывожу информацию:");
        System.out.println(hintText.getText());*/
        //System.out.println(hintText.getText());


    }
}
