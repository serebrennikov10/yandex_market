package ru.yandex;

import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MarketPage extends WebDriverSetting {
    private WebDriver driver;
    private WebDriverWait waitDriver;
    private Actions MoveToElement;
    private Integer firstElementByPriceMax;
    private Integer firstElementByPriceMin;

    public MarketPage(WebDriver driver) {
            this.driver = driver;
        this.waitDriver = new WebDriverWait(driver, 15);
        this.MoveToElement = new Actions(driver);
    }

    public void openPage() {

        driver.get("https://market.yandex.ru/");
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("n-adaptive-layout")));
        captureScreen();

    }

    public void getTitle() {

        String title = driver.getTitle();
        Assert.assertEquals("Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов", title);
        System.out.println("Title: "+title);
        //System.out.println("О да, яндекс-маркет открылся");
    }

/*    public class RegionOnPage {
        public static RegionOnPage instance = new RegionOnPage();
        private RegionOnPage(){}
        public static RegionOnPage getInstance(){
            return  instance;
        }
        }*/

    public void selectNewRegionOnPage(){
        WebElement changeRegion = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/noindex/div/div/div[2]/div/div[2]/div[1]/span"));
        String nowRegion = changeRegion.getText();
        String newRegion = "Воронеж";
        if (nowRegion.equals(newRegion)) {
            System.out.println("Текущий регион: "+nowRegion+". Смена региона не происходит.");
        }
        else {
            System.out.println("Текущий регион: "+nowRegion+". Запускаю смену региона..");
            changeRegion.click();
            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("header2-region-popup")));
            //WebElement frameInputRegion = driver.findElement(By.className("header2-region-popup"));
            WebElement inputRegion = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[1]/form/div/div/div/div[1]/span/input"));
            inputRegion.sendKeys(newRegion);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inputRegion.sendKeys(Keys.ENTER);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement buttonSelectRegion = driver.findElement(By.cssSelector("button[class*='button region-select-form']"));
            MoveToElement.moveToElement(buttonSelectRegion).perform();
            buttonSelectRegion.click();
            System.out.println("Установлен новый регион "+newRegion);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        captureScreen();



    }


/*    public void selectNewRegionOnPage(){
        WebElement changeRegion = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/noindex/div/div/div[2]/div/div[2]/div[1]/span"));
        String nowRegion = changeRegion.getText();
        String newRegion = "Воронеж";
        if (nowRegion.equals(newRegion)) {
            System.out.println("Текущий регион: "+nowRegion+". Смена региона не происходит.");
        }
        else {
            System.out.println("Текущий регион: "+nowRegion+". Запускаю смену региона..");
            changeRegion.click();
            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("header2-region-popup")));
            //WebElement frameInputRegion = driver.findElement(By.className("header2-region-popup"));
            WebElement inputRegion = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[1]/form/div/div/div/div[1]/span/input"));
            inputRegion.sendKeys(newRegion);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inputRegion.sendKeys(Keys.ENTER);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement buttonSelectRegion = driver.findElement(By.cssSelector("button[class*='button region-select-form']"));
            MoveToElement.moveToElement(buttonSelectRegion).perform();
            buttonSelectRegion.click();
            System.out.println("Установлен новый регион "+newRegion);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }*/

    public void openAllCategories() {
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='n-w-tab__control-hamburger']")));
        WebElement allCategory = driver.findElement(By.xpath("/html/body/div[1]/div/span/div[2]/noindex/div[2]/div/div/div/div[1]"));
        MoveToElement.moveToElement(allCategory).perform();
        allCategory.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("n-w-tabs__tabs-column")));
        captureScreen();
    }

    public void openCompCategory() {

        WebElement compCategory = driver.findElement(By.xpath("/html/body/div[1]/div/span/div[2]/noindex/div[1]/div/div/div/div/div/div/div[1]/div/div[3]/a"));
        MoveToElement.moveToElement(compCategory).perform();
        compCategory.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[7]/div/div/div[1]/div/div/div/div/div/div")));
        captureScreen();
    }

    public void openNotebookCategory() {
        WebElement notebookCategory = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[7]/div/div/div[1]/div/div/div/div/div/div/div[1]/div[2]/ul/li[2]/div/a"));
        MoveToElement.moveToElement(notebookCategory).perform();
        notebookCategory.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("headline__header")));
        captureScreen();
    }

    public void sortByPrice() {
        WebElement sortPrice = driver.findElement(By.linkText("по цене"));
        sortPrice.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("headline__header")));

        //WebElement divNameSortPrice = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[3]"));
        //String classNameSortPrice = divNameSortPrice.getAttribute("class");
        //System.out.println(classNameSortPrice);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void getPriceAttribute(){
        WebElement divNameSortPrice = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[3]"));
        String classNameSortPrice = divNameSortPrice.getAttribute("class");
        String desc = "n-filter-sorter i-bem n-filter-sorter_js_inited n-filter-sorter_sort_desc n-filter-sorter_state_select";
    }

    public void sortPriceByAsc() {
        //по возрастанию

        WebElement divNameSortPrice = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[3]"));
        String classNameSortPrice = divNameSortPrice.getAttribute("class");
        String asc = "n-filter-sorter i-bem n-filter-sorter_js_inited n-filter-sorter_sort_asc n-filter-sorter_state_select";
        //System.out.println("Сейчас: " + classNameSortPrice);
        if (classNameSortPrice.equals(asc)) {
            //System.out.println("Сейчас стоит сортировка по ВОЗРАСТАНИЮ!" + classNameSortPrice);
        }
        else {
            System.out.println("Сортировка установлена по убыванию. Делаю по возрастанию...");
            WebElement sortPrice = driver.findElement(By.linkText("по цене"));
            sortPrice.click();
            //waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("/html/body/div[1]/div[5]/div[2]/div[1]")));
            System.out.println("Установлена сортировка по возрастанию");
        }


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void sortPriceByDesc() {
        //по убыванию
        WebElement divNameSortPrice = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[3]"));
        String classNameSortPrice = divNameSortPrice.getAttribute("class");
        String desc = "n-filter-sorter i-bem n-filter-sorter_js_inited n-filter-sorter_sort_desc n-filter-sorter_state_select";
        //System.out.println("Сейчас: " + classNameSortPrice);
        if (classNameSortPrice.equals(desc)) {
            //System.out.println("Сейчас стоит сортировка по УБЫВАНИЮ!" + classNameSortPrice);
        }
        else {
            System.out.println("Сортировка установлена по возрастанию. Делаю по убыванию...");
            WebElement sortPrice = driver.findElement(By.linkText("по цене"));
            sortPrice.click();
            //waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("/html/body/div[1]/div[5]/div[2]/div[1]")));
            //System.out.println("Установлена сортировка по УБЫВАНИЮ");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void selectFilterByCost(String From, String To) {
        WebElement selectCostFrom = driver.findElement(By.xpath("//*[@id=\"glpricefrom\"]"));
        selectCostFrom.click();
        selectCostFrom.sendKeys(From);
        WebElement selectCostTo = driver.findElement(By.xpath("//*[@id=\"glpriceto\"]"));
        selectCostTo.click();
        selectCostTo.sendKeys(To);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        captureScreen();

    }

    public void selectFilterByBrand(String brand){
        List<WebElement> brandsNames = driver.findElements(By.xpath("//*[@id=\"search-prepack\"]/div/div/div[3]/div/div/div[2]/div[4]/div/div/fieldset"));
        for  (WebElement brandsList:brandsNames) {
            //System.out.println(brandsList.getText());
            brandsList.findElement(By.xpath(".//*[text()='"+brand+"']/..")).click();
            System.out.println("Выбран бренд "+ brand);
            //brandsList.findElement(By.xpath(".//*[text()='HP']/..")).click();   -- по тексту, рабочий вариант
            //brandsList.findElement(By.partialLinkText("HP")).click();
            //brandsList.findElement(By.tagName("span")).findElement(By.linkText("HP")).click();
            captureScreen();
        }

        //WebElement selectBrand = driver.findElement(By.name("Производитель "+brandName));
        //selectBrand.click();
        //WebElement zsddf = driver.findElement(By.className().By)
        //WebElement brand = brandsList.findElement(By.linkText("HP")

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public void selectFilterByColor(String color){
        List<WebElement> colorsNames = driver.findElements(By.xpath("//*[@id=\"search-prepack\"]/div/div/div[3]/div/div/div[2]/div[22]/div/div/fieldset"));
        for (WebElement colorList:colorsNames){
            //System.out.println(colorList.getText());
            colorList.findElement(By.xpath(".//*[text()='Цвет "+color+"']/..")).click();
            System.out.println("Выбран цвет: "+color);
            captureScreen();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void outInfo(){
        System.out.println("запуск метода");
        List<WebElement> elementsAboutInfoNote = driver.findElements(By.xpath("/html/body/div[1]/div[5]/div[2]/div[1]/div[2]/div/div[1]"));

        for (WebElement elementInfoAboutNote:elementsAboutInfoNote) {
            //System.out.println(elementInfoAboutNote.getText());
            String firstElementNoteName = elementInfoAboutNote.findElement(By.className("n-snippet-card2__title")).getText();
            String firstElementNotePrice = elementInfoAboutNote.findElement(By.className("n-snippet-card2__main-price")).getText();
            String firstElementNoteInfo = elementInfoAboutNote.findElement(By.className("n-snippet-card2__content")).getText();
            System.out.println("Информация о ноутбуке");
            System.out.println("Название: ");
            System.out.println(firstElementNoteName);
            System.out.println("Цена: ");
            System.out.println(firstElementNotePrice);
            System.out.println("Характеристики: ");
            System.out.println(firstElementNoteInfo);

            //List<WebElement> elements = elementInfoAboutNote.findElements(By.className("n-snippet-card2__title"));
            //for (WebElement element:elements) {
            //System.out.println(element.getText());
        }


    }

    class WebElementComparator implements Comparator<WebElement> {
        @Override
        public int compare(WebElement o1, WebElement o2) {
            if (o1 == o2) return 0;
            return o1.getText().compareTo(o2.getText());
        }
    }

    public void outInfoTwo(){
        List<WebElement> elementsAboutInfoNote = driver.findElements(By.xpath("/html/body/div[1]/div[5]/div[2]/div[1]/div[2]/div/div[1]"));
        for (WebElement elementInfoAboutNote:elementsAboutInfoNote) {

            //System.out.println(elementInfoAboutNote.getText());    //вывод всего текста по всем элементам
            String firstElementNoteName = elementInfoAboutNote.findElement(By.className("n-snippet-card2__title")).getText();
            String firstElementNotePrice = elementInfoAboutNote.findElement(By.className("n-snippet-card2__main-price")).getText();
            String firstElementNoteInfo = elementInfoAboutNote.findElement(By.className("n-snippet-card2__content")).getText();
            firstElementNotePrice = firstElementNotePrice.replaceAll("\\D+","");
            //System.out.println(elementInfoAboutNote.findElement(By.className("n-snippet-card2__title")).getText());   //называние первого элемента
            System.out.println("Информация о ноутбуке");
            System.out.println("Название: ");
            System.out.println(firstElementNoteName);
            System.out.println("Цена: ");
            System.out.println(firstElementNotePrice);
            System.out.println("Характеристики: ");
            System.out.println(firstElementNoteInfo);

            //List<WebElement> elements = new ArrayList<>(elementInfoAboutNote.findElements(By.className("n-snippet-card2__title")));
            //for (WebElement element:elements) {
                   // elements.sort(new WebElementComparator());
                //System.out.println(element.getText());
            //}


        }
    }


    public void differenceLaptops(){
        sortPriceByAsc();
        List<WebElement> elementsAboutInfoNoteMin = driver.findElements(By.xpath("/html/body/div[1]/div[5]/div[2]/div[1]/div[2]/div/div[1]"));
        for (WebElement elementInfoAboutNoteMin : elementsAboutInfoNoteMin) {
            //System.out.println(elementInfoAboutNoteMin.getText());    //вывод всего текста по всем элементам
            String firstElementNoteNameMin = elementInfoAboutNoteMin.findElement(By.className("n-snippet-card2__title")).getText();
            String firstElementNotePriceMin = elementInfoAboutNoteMin.findElement(By.className("n-snippet-card2__main-price")).getText();
            //String firstElementNoteInfo = elementInfoAboutNoteMin.findElement(By.className("n-snippet-card2__content")).getText();
            //String firstElementByPriceMin = firstElementNotePriceMin.replaceAll("\\D+", "");
            firstElementByPriceMin = Integer.parseInt(firstElementNotePriceMin.replaceAll("\\D+", ""));
            //System.out.println(elementInfoAboutNoteMin.findElement(By.className("n-snippet-card2__title")).getText());   //называние первого элемента
            System.out.println("Дешевый Ноутбук:");
            System.out.println("Название: "+firstElementNoteNameMin);
            System.out.println("Цена: "+firstElementByPriceMin+" р.");
            captureScreen();
        }
        sortPriceByDesc();
        List<WebElement> elementsAboutInfoNoteMax = driver.findElements(By.xpath("/html/body/div[1]/div[5]/div[2]/div[1]/div[2]/div/div[1]"));
        for (WebElement elementInfoAboutNoteMax : elementsAboutInfoNoteMax) {
            //System.out.println(elementInfoAboutNoteMax.getText());    //вывод всего текста по всем элементам
            String firstElementNoteNameMax = elementInfoAboutNoteMax.findElement(By.className("n-snippet-card2__title")).getText();
            String firstElementNotePriceMax = elementInfoAboutNoteMax.findElement(By.className("n-snippet-card2__main-price")).getText();
            //String firstElementNoteInfo = elementInfoAboutNoteMax.findElement(By.className("n-snippet-card2__content")).getText();
            //String firstElementByPriceMax = firstElementNotePriceMax.replaceAll("\\D+", "");
            firstElementByPriceMax = Integer.parseInt(firstElementNotePriceMax.replaceAll("\\D+", ""));
            //System.out.println(elementInfoAboutNoteMax.findElement(By.className("n-snippet-card2__title")).getText());   //называние первого элемента
            System.out.println("Дорогой Ноутбук:");
            System.out.println("Название: " + firstElementNoteNameMax);
            System.out.println("Цена: " + firstElementByPriceMax+" р.");
            captureScreen();
        }
        int differencePrice = firstElementByPriceMax - firstElementByPriceMin;
        System.out.println("Разница в цене составляет "+differencePrice+" р.");

    }


    void outputInfoAllNotebook(){
        List<WebElement> elementsAboutInfoNote = driver.findElements(By.xpath("/html/body/div[1]/div[5]/div[2]/div[1]/div[2]/div/div[1]"));
        for (WebElement elementInfoAboutNote:elementsAboutInfoNote) {
            List<WebElement> elements = new ArrayList<>(elementInfoAboutNote.findElements(By.className("n-snippet-card2__title")));
            elements.sort(new WebElementComparator());
            for (WebElement element:elements) {
                System.out.println(element.getText());
            }
        }
    }


    void outputInfoInMap(){
        Map<String , String> map = new HashMap<>();
        List<WebElement> elementsName = driver.findElements(By.className("n-snippet-card2__title"));
        List<WebElement> elementsPrice = driver.findElements(By.className("n-snippet-card2__main-price"));
        for(int i=0;i<elementsName.size();i++)
            map.put(elementsName.get(i).getText(), elementsPrice.get(i).getText());
        System.out.println("Размер набора данных : " +map.size());

        Map<String, String> reversedMap = new TreeMap<String, String>(map);
        for (Map.Entry entry : reversedMap.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }


    }
    public void captureScreen() {
        String path;
        try {

            //WebDriver augmentedDriver = new Augmenter().augment(driver);
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path = "./target/screenshots/" + screenshot.getName();
            FileUtils.copyFile(screenshot, new File(path));
        }
        catch(IOException e) {
            e.getMessage();
        }
        //return path;
    }



}







