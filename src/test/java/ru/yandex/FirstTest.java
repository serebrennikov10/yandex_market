package ru.yandex;

import io.qameta.allure.Step;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class FirstTest extends WebDriverSetting {



    @Test
    @Step(value = "Проверка тайтла")
        public void oneTest(){
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        //WebDriverWait waitForDriver = new WebDriverWait(driver, 10);
       // Actions MoveToElement = new Actions(driver);
        marketPage.openPage();

        marketPage.getTitle();

        marketPage.openAllCategories();
        //marketPage.openCompCategory();
        //marketPage.openNotebookCategory();

        //marketPage.selectFilterByCost("0", "30000");
        //marketPage.selectFilterByBrand("HP");
        //marketPage.selectFilterByBrand("Lenovo");
        //marketPage.selectFilterByColor("черный");
        //marketPage.selectFilterByColor("белый");
        //marketPage.sortByPrice();
        //marketPage.sortPriceByDesc();  //по убыванию
        //marketPage.sortPriceByAsc(); //по возрастанию
        //marketPage.outInfo();



        //marketPage.sortByPrice();
        //marketPage.sortPriceByDesc();  //по убыванию
        //marketPage.outInfo();
        //marketPage.sortByPrice();
        //marketPage.sortPriceByAsc();
        //marketPage.outInfo();

        System.out.println("Test completed!");

    }

/*    @Test

    public void twoTest() {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage();
        marketPage.getTitle();
        marketPage.openAllCategories();
        marketPage.openCompCategory();
        marketPage.openNotebookCategory();

    }*/




}