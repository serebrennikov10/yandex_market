package ru.yandex;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest extends WebDriverSetting {



    @Test

    public void firstTest(){

        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        //WebDriverWait waitForDriver = new WebDriverWait(driver, 10);
       // Actions MoveToElement = new Actions(driver);

        marketPage.openPage();
        marketPage.getTitle();
        marketPage.openAllCategories();
        marketPage.openCompCategory();
        marketPage.openNotebookCategory();
        marketPage.sortByPrice();
        //marketPage.sortPriceByDesc();
        //marketPage.sortPriceByAsc();
        marketPage.selectFilterByCost("0", "30000");
        //marketPage.selectFilterByBrand("HP");
        //marketPage.selectFilterByBrand("Lenovo");
        marketPage.selectFilterByBrand("HP");
        System.out.println("test finished");


    }
}