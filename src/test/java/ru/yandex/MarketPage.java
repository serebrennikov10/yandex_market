package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class MarketPage extends WebDriverSetting {
    private WebDriver driver;
    private WebDriverWait waitDriver;
    private Actions MoveToElement;

   public MarketPage(WebDriver driver) {
        this.driver = driver;
        this.waitDriver = new WebDriverWait(driver, 10);
        this.MoveToElement = new Actions(driver);
    }

    public void openPage() {

        driver.get("https://market.yandex.ru/");
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("n-adaptive-layout")));

    }

    public void getTitle() {

        String title = driver.getTitle();
        Assert.assertEquals("Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов", title);
        System.out.println("Title: "+title);
        //System.out.println("О да, яндекс-маркет открылся");
    }

    public void selectNewRegionOnPage(){
        WebElement changeRegion = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/noindex/div/div/div[2]/div/div[2]/div[1]/span"));
        String nowRegion = changeRegion.getText();
        System.out.println(nowRegion);

        String newRegion = "Воронеж";
        if (nowRegion.equals(newRegion)) {
            System.out.println("Текущий регион: "+nowRegion+". Смена региона не происходит.");
        }
        else {
            System.out.println("Запускаю смену региона..");
            changeRegion.click();
            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("header2-region-popup")));
            //WebElement frameInputRegion = driver.findElement(By.className("header2-region-popup"));
            WebElement inputRegion = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[1]/form/div/div/div/div[1]/span/input"));
            inputRegion.sendKeys(newRegion);
            //frameInputRegion.click();
            List <WebElement> regionSuggestList = driver.findElements(By.xpath("/html/body/div[6]/div/div/div[1]/div[1]/form/div/div/div/div[2]/div"));
                    for (WebElement regionList:regionSuggestList) {
                        WebElement firstElementList = regionList.findElement(By.className("region-suggest__list-item suggestick-list__item suggest2-item suggest2-item_type_text"));
                        MoveToElement.moveToElement(firstElementList).perform();
                        firstElementList.click();
                    }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement buttonSelectRegion = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[1]/form/div/button"));
            buttonSelectRegion.click();
            System.out.println("Установлен новый регион "+newRegion);
        }



    }

    public void openAllCategories() {
        WebElement allCategory = driver.findElement(By.xpath("/html/body/div[1]/div/span/div[2]/noindex/div[2]/div/div/div/div[1]"));
        MoveToElement.moveToElement(allCategory).perform();
        allCategory.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("n-w-tabs__tabs-column")));
    }

    public void openCompCategory() {

        WebElement compCategory = driver.findElement(By.xpath("/html/body/div[1]/div/span/div[2]/noindex/div[1]/div/div/div/div/div/div/div[1]/div/div[3]/a"));
        MoveToElement.moveToElement(compCategory).perform();
        compCategory.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[7]/div/div/div[1]/div/div/div/div/div/div")));
    }

    public void openNotebookCategory() {
        WebElement notebookCategory = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[7]/div/div/div[1]/div/div/div/div/div/div/div[1]/div[2]/ul/li[2]/div/a"));
        MoveToElement.moveToElement(notebookCategory).perform();
        notebookCategory.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("headline__header")));
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
            System.out.println("Сейчас стоит сортировка по ВОЗРАСТАНИЮ!" + classNameSortPrice);
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
            System.out.println("Сейчас стоит сортировка по УБЫВАНИЮ!" + classNameSortPrice);
        }
         else {
            System.out.println("Сортировка установлена по возрастанию. Делаю по убыванию...");
            WebElement sortPrice = driver.findElement(By.linkText("по цене"));
            sortPrice.click();
            //waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("/html/body/div[1]/div[5]/div[2]/div[1]")));
            System.out.println("Установлена сортировка по УБЫВАНИЮ");

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
            String firstElementNotePrice = elementInfoAboutNote.findElement(By.className("n-snippet-card2__price")).getText();
            String firstElementNoteInfo = elementInfoAboutNote.findElement(By.className("n-snippet-card2__content")).getText();
            System.out.println("Информация о ноутбуке:");
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


}







