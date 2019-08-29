package ru.yandex;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        Assert.assertTrue(title.equals("Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов"));
        System.out.println(title);
        System.out.println("О да, яндекс-маркет открылся");
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

    public void sortPriceByDesc() {
        WebElement divNameSortPrice = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[3]"));
        String classNameSortPrice = divNameSortPrice.getAttribute("class");
        String asc = "n-filter-sorter i-bem n-filter-sorter_js_inited n-filter-sorter_sort_asc n-filter-sorter_state_select";
        if (classNameSortPrice == asc) {
            System.out.println("Сейчас стоит сортировка по ВОЗРАСТАНИЮ!" + classNameSortPrice);

        } else {

            System.out.println("Устанавливаю сортировку по убыванию...");
            WebElement sortPrice = driver.findElement(By.linkText("по цене"));
            sortPrice.click();
            //waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("/html/body/div[1]/div[5]/div[2]/div[1]")));
            System.out.println("Установлена сортировка по УБЫВАНИЮ" + classNameSortPrice);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void sortPriceByAsc() {
        WebElement divNameSortPrice = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[3]"));
        String classNameSortPrice = divNameSortPrice.getAttribute("class");
        String desc = "n-filter-sorter i-bem n-filter-sorter_js_inited n-filter-sorter_sort_desc n-filter-sorter_state_select";

        if (classNameSortPrice == desc) {
            System.out.println("Сейчас стоит сортировка по УБЫВАНИЮ!" + classNameSortPrice);
        } else {

            System.out.println("Устанавливаю сортировку по возрастанию...");
            WebElement sortPrice = driver.findElement(By.linkText("по цене"));
            sortPrice.click();
            //waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("/html/body/div[1]/div[5]/div[2]/div[1]")));
            System.out.println("Установлена сортировка по ВОЗРАСТАНИЮ" + classNameSortPrice);
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
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void selectFilterByBrand(String brandName){
        //WebElement selectBrand = driver.findElement(By.name("Производитель "+brandName));
        //selectBrand.click();

        //WebElement selectBrand = driver.findElement(By.partialLinkText("HP"));
        //selectBrand.click();

        List<WebElement> allAnchors = driver.findElements(By.cssSelector("#search-prepack > div > div > div:nth-child(3) > div > div > div._178jz2CyDL > div:nth-child(3) > div > fieldset > ul"));
        for (WebElement eachAnchorElem : allAnchors) {
            WebElement linkBrand = eachAnchorElem.findElement(By.linkText("HP"));
            linkBrand.click();
        }



        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }





    }
}




