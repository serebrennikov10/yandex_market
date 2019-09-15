package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class FirstTest extends WebDriverSetting {

/*    private MarketPage marketPage;

    public FirstTest() {
        this.marketPage = PageFactory.initElements(driver, MarketPage.class);
    }*/


    @Test(priority = 1, description = "Открытие страницы")
    @Description(value = "Тест проверяет открытие страницы")
    public void openPage() {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        //WebDriverWait waitForDriver = new WebDriverWait(driver, 10);
        //Actions MoveToElement = new Actions(driver);
        marketPage.openPage();
        marketPage.getTitle();
    }

    @Test(priority = 2,description = "Смена региона")
    @Description(value = "Тест проверяет смену региона на 'Воронеж' ")
    public void selectRegion() {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.selectNewRegionOnPage();
    }

    @Test(priority = 3,description = "Выбор категории 'Компьютеры' -> 'Ноутбуки'")
    @Description(value = "Тест проверяет выбор категории 'Компьютеры' -> 'Ноутбуки'")
    public void selectNotebook(){
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openAllCategories();
        marketPage.openCompCategory();
        marketPage.openNotebookCategory();
    }
    @Test(priority = 4,description = "Задаю параметры поиска: до 30000 р.")
    @Description(value = "Тест проверяет установку параметра стоимости до 30000 р.")
    public void selectPrice() {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.selectFilterByCost("0", "30000");
    }

     @Test(priority = 5,description = "Задаю параметры поиска: бренд HP и Lenovo")
      @Description(value = "Тест проверяет установку бренда HP и Lenovo")
             public void selectBrand() {
         MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
         marketPage.selectFilterByBrand("HP");
         marketPage.selectFilterByBrand("Lenovo");
     }

    @Test(priority = 6,description = "Задаю параметры поиска: цвет Черный и Белый")
    @Description(value = "Тест проверяет установку цвета Черный и Белый")
    public void selectColor() {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.selectFilterByColor("черный");
        marketPage.selectFilterByColor("белый");
    }
    @Test(priority = 7,description = "Выводим разницу между дорогим и дешевым ноутбуком")
    @Description(value = "Тест проверяет вывод разницы между дорогим и дешевым ноутбуком")
    public void difference() {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.sortByPrice();
        marketPage.differenceLaptops();
    }
    @Test(priority = 8,description = "Выводим список ноутбуков")
    @Description(value = "Тест проверяет вывод списка ноутбуков с сортировкой по имени")
    public void outNoteList() {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.outputInfoAllNotebook();
    }
    @Test(priority = 9,description = "Выводим список ноутбуков из Мар <name, price>")
    @Description(value = "Тест проверяет вывод списка из Мар")
    public void outNoteMap() {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.outputInfoInMap();
    }
    //marketPage.sortByPrice();
    //marketPage.sortPriceByDesc();  //по убыванию
    //marketPage.sortPriceByAsc(); //по возрастанию
    //marketPage.outInfo();
    public void fistTestEnd(){
        System.out.println("Test completed!");
    }

    }

/*    @Test(description = "мой тест - два")
    public void twoTest() {
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.openPage();
        marketPage.getTitle();
        marketPage.selectNewRegionOnPage();
        marketPage.openAllCategories();
        marketPage.openCompCategory();
        marketPage.openNotebookCategory();

    }*/




