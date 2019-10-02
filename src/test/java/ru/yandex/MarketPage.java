package ru.yandex;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
        this.waitDriver = new WebDriverWait(driver, 20);
        this.MoveToElement = new Actions(driver);
    }


    @FindBy(css = "button[class*='button region-select-form']")
    private WebElement buttonSelectRegion;
    //@FindBy(xpath="/html/body/div[6]/div/div/div[1]/div[1]/form/div/div/div/div[1]/span/input")
    @FindBy(xpath="//input[contains(@placeholder, 'Укажите другой регион')]")
    private WebElement inputRegion;

    private By frameInputRegion = By.className("header2-region-popup");


    @Step("Открытие страницы")
    public MarketPage openPage() throws IOException {

        driver.get("https://market.yandex.ru/");
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("n-adaptive-layout")));
        saveScreenshotPNG (driver, "ЯндексМаркет");
        return  new MarketPage(driver);
    }

    public MarketPage getTitle() {

        String title = driver.getTitle();
        Assert.assertEquals("Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов", title);
        System.out.println("Title: "+title);
        return  new MarketPage(driver);
    }

/*    public class RegionOnPage {
        private static RegionOnPage instance = new RegionOnPage();
        private RegionOnPage() {
        }
        public static RegionOnPage getInstance() {

            return instance;
        }
    }*/

    public static class RegionOnPage {
        private static volatile RegionOnPage instance;
        private RegionOnPage() {
            //System.out.println("Singleton created!");
        }
        public static RegionOnPage getInstance() {
            if (instance == null) {
                synchronized (RegionOnPage.class) {
                    if (instance == null) {
                        instance = new RegionOnPage();
                    }
                }
            }
            return instance;
        }
    }

    @Step("Смена региона")
    public void selectNewRegionOnPage() throws IOException {
        RegionOnPage region1 = RegionOnPage.getInstance();
        //System.out.println(region1.getClass());
        //RegionOnPage region2 = RegionOnPage.getInstance();
        //System.out.println(region2.getClass());
        WebElement changeRegion = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/noindex/div/div/div[2]/div/div[2]/div[1]/span"));
        //WebElement changeRegion = driver.findElement(By.xpath("//input[contains(@placeholder, 'Укажите другой регион')]"));
        String nowRegion = changeRegion.getText();
        String newRegion = "Воронеж";
        if (nowRegion.equals(newRegion)) {
            System.out.println("Текущий регион: "+nowRegion+". Смена региона не происходит.");
        }
        else {
            System.out.println("Текущий регион: "+nowRegion+". Запускаю смену региона..");
            changeRegion.click();
            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(frameInputRegion));
            //WebElement frameInputRegion = driver.findElement(By.className("header2-region-popup"));
            //WebElement inputRegion = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div[1]/form/div/div/div/div[1]/span/input"));
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
            //WebElement buttonSelectRegion = driver.findElement(By.cssSelector("button[class*='button region-select-form']"));
            MoveToElement.moveToElement(buttonSelectRegion).perform();
            buttonSelectRegion.click();
            System.out.println("Установлен новый регион "+newRegion);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String nameScreen = "Установлен регион "+newRegion;
        saveScreenshotPNG (driver, nameScreen);
        captureScreen();
    }




    @Step("Открытие категорий")
    //public static void checkOpenCategory(){}
    public MarketPage openAllCategories() throws IOException {
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='n-w-tab__control-hamburger']")));
        WebElement allCategory = driver.findElement(By.xpath("/html/body/div[1]/div/span/div[2]/noindex/div[2]/div/div/div/div[1]"));
        MoveToElement.moveToElement(allCategory).perform();
        allCategory.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("n-w-tabs__tabs-column")));
        captureScreen();
        String nameScreen = "Все категории";
        saveScreenshotPNG (driver, nameScreen);
        return  new MarketPage(driver);
    }

    @Step("Открытие категори: Компьютеры")
    //public static void checkOpenComp(){}
    public MarketPage openCompCategory() throws IOException {
        WebElement compCategory = driver.findElement(By.xpath("/html/body/div[1]/div/span/div[2]/noindex/div[1]/div/div/div/div/div/div/div[1]/div/div[3]/a"));
        MoveToElement.moveToElement(compCategory).perform();
        compCategory.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[7]/div/div/div[1]/div/div/div/div/div/div")));
        captureScreen();
        String nameScreen = "Компьютерная техника";
        saveScreenshotPNG (driver, nameScreen);
        return  new MarketPage(driver);
    }

    @Step("Открытие категори: Ноутбуки")
    //public static void checkOpenNote(){}
    public MarketPage openNotebookCategory() throws IOException {
        WebElement notebookCategory = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[7]/div/div/div[1]/div/div/div/div/div/div/div[1]/div[2]/ul/li[2]/div/a"));
        MoveToElement.moveToElement(notebookCategory).perform();
        notebookCategory.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("headline__header")));
        captureScreen();
        String nameScreen = "Ноутбуки";
        saveScreenshotPNG (driver, nameScreen);
        return  new MarketPage(driver);
    }

    public void setListStyleVisibility(){
        WebElement setListStyle = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div[2]/div[2]/div/span/label[1]"));
        setListStyle.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Выбор категории 'Компьютеры' -> 'Ноутбуки'")
    public MarketPage openNoteCategoryNow() throws IOException {
        openAllCategories();
        openCompCategory();
        openNotebookCategory();
        setListStyleVisibility();
        return  new MarketPage(driver);
    }


    @Step("Сортировка по цене")
    public MarketPage sortByPrice() throws IOException {
        WebElement sortPrice = driver.findElement(By.linkText("по цене"));
        sortPrice.click();
        //waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("headline__header")));
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'n-snippet-list')]")));

        //WebElement divNameSortPrice = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[3]"));
        //String classNameSortPrice = divNameSortPrice.getAttribute("class");
        //System.out.println(classNameSortPrice);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String nameScreen = "Сортировка по цене";
        saveScreenshotPNG(driver, nameScreen);
        return  new MarketPage(driver);

    }

    public void getPriceAttribute(){
        WebElement divNameSortPrice = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[3]"));
        String classNameSortPrice = divNameSortPrice.getAttribute("class");
        String desc = "n-filter-sorter i-bem n-filter-sorter_js_inited n-filter-sorter_sort_desc n-filter-sorter_state_select";
    }

    public MarketPage sortPriceByAsc() {
        //по возрастанию

        WebElement divNameSortPrice = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[3]"));
        String classNameSortPrice = divNameSortPrice.getAttribute("class");
        String asc = "n-filter-sorter i-bem n-filter-sorter_js_inited n-filter-sorter_sort_asc n-filter-sorter_state_select";
        //System.out.println("Сейчас: " + classNameSortPrice);
        if (classNameSortPrice.equals(asc)) {
            //System.out.println("Сейчас стоит сортировка по ВОЗРАСТАНИЮ!" + classNameSortPrice);
        }
        else {
            //System.out.println("Сортировка установлена по убыванию. Делаю по возрастанию...");
            WebElement sortPrice = driver.findElement(By.linkText("по цене"));
            sortPrice.click();
            //waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("/html/body/div[1]/div[5]/div[2]/div[1]")));
            System.out.println("Установлена сортировка по возрастанию");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return  new MarketPage(driver);
    }

    public MarketPage sortPriceByDesc() {
        //по убыванию
        WebElement divNameSortPrice = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[3]"));
        String classNameSortPrice = divNameSortPrice.getAttribute("class");
        String desc = "n-filter-sorter i-bem n-filter-sorter_js_inited n-filter-sorter_sort_desc n-filter-sorter_state_select";
        //System.out.println("Сейчас: " + classNameSortPrice);
        if (classNameSortPrice.equals(desc)) {
            //System.out.println("Сейчас стоит сортировка по УБЫВАНИЮ!" + classNameSortPrice);
        }
        else {
            //System.out.println("Сортировка установлена по возрастанию. Делаю по убыванию...");
            WebElement sortPrice = driver.findElement(By.linkText("по цене"));
            sortPrice.click();
            //waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("/html/body/div[1]/div[5]/div[2]/div[1]")));
            System.out.println("Установлена сортировка по убыванию");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return  new MarketPage(driver);
    }

    @Step("Задаю параметры поиска по стоимости: от {From} до {To}")
    public MarketPage selectFilterByCost(String From, String To) throws IOException {
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
        String nameScreen = "Устанавливаю фильтр стоимости";
        saveScreenshotPNG(driver, nameScreen);
        return  new MarketPage(driver);
    }

    @Step("Задаю параметры поиска по бренду: {brand}")
    public MarketPage selectFilterByBrand(String brand) throws IOException {
        List<WebElement> brandsNames = driver.findElements(By.xpath("//*[@id=\"search-prepack\"]/div/div/div[3]/div/div/div[2]/div[4]/div/div/fieldset"));
        for  (WebElement brandsList:brandsNames) {
            //System.out.println(brandsList.getText());
            brandsList.findElement(By.xpath(".//*[text()='"+brand+"']/..")).click();
            System.out.println("Выбран бренд "+ brand);
            //brandsList.findElement(By.xpath(".//*[text()='HP']/..")).click();   -- по тексту, рабочий вариант
            //brandsList.findElement(By.partialLinkText("HP")).click();
            //brandsList.findElement(By.tagName("span")).findElement(By.linkText("HP")).click();
            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'n-snippet-list')]")));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            saveScreenshotPNG(driver, brand);
        }
        //WebElement selectBrand = driver.findElement(By.name("Производитель "+brandName));
        //selectBrand.click();
        //WebElement zsddf = driver.findElement(By.className().By)
        //WebElement brand = brandsList.findElement(By.linkText("HP")
        return  new MarketPage(driver);
    }

    @Step("Задаю параметры поиска по цвету: {color}")
    public MarketPage selectFilterByColor(String color) throws IOException {
        List<WebElement> colorsNames = driver.findElements(By.xpath("//*[@id=\"search-prepack\"]/div/div/div[3]/div/div/div[2]/div[22]/div/div/fieldset"));
        for (WebElement colorList:colorsNames){
            //System.out.println(colorList.getText());
            //colorList.findElement(By.xpath(".//*[text()='Цвет "+color+"']/..")).click();
            colorList.findElement(By.xpath("//span[text()='Цвет "+color+"']/..")).click();
            System.out.println("Выбран цвет: "+color);
            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'n-snippet-list')]")));
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        captureScreen();
        String nameScreen = "Цвет "+color;
        saveScreenshotPNG(driver, nameScreen);
        return  new MarketPage(driver);
    }


    class WebElementComparator implements Comparator<WebElement> {
        @Override
        public int compare(WebElement o1, WebElement o2) {
            if (o1 == o2) return 0;
            return o1.getText().compareTo(o2.getText());
        }
    }

    public void outInfo(){
        List<WebElement> elementsAboutInfoNote = driver.findElements(By.xpath("/html/body/div[1]/div[5]/div[2]/div[1]/div[2]/div/div[1]"));
        for (WebElement elementInfoAboutNote:elementsAboutInfoNote) {
            String firstElementNoteName = elementInfoAboutNote.findElement(By.className("n-snippet-card2__title")).getText();
            String firstElementNotePrice = elementInfoAboutNote.findElement(By.className("n-snippet-card2__main-price")).getText();
            String firstElementNoteInfo = elementInfoAboutNote.findElement(By.className("n-snippet-card2__content")).getText();
            firstElementNotePrice = firstElementNotePrice.replaceAll("\\D+","");
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

    @Step("Выводим разницу между дорогим и дешевым ноутбуком")
    public MarketPage differenceLaptops() throws IOException {
        sortPriceByAsc();
        String asc = "Сортировка по возрастанию (дешевый)";
        saveScreenshotPNG(driver, asc);
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

        }
        sortPriceByDesc();
        String desc = "Сортировка по убыванию (дорогой)";
        saveScreenshotPNG(driver, desc);
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
        return new MarketPage(driver);
    }

    @Step("Вывожу список ноутбуков")
    public void stepOutputInfoInList(String list){

    }
    @Step("Формирую список ноутбуков")
    public MarketPage outputInfoInList() throws IOException {
        List<WebElement> elementsAboutInfoNote = driver.findElements(By.xpath("/html/body/div[1]/div[5]/div[2]/div[1]/div[2]/div/div[1]"));
        for (WebElement elementInfoAboutNote:elementsAboutInfoNote) {
            List<WebElement> elements = new ArrayList<>(elementInfoAboutNote.findElements(By.className("n-snippet-card2__title")));
            elements.sort(new WebElementComparator()); //сортировка
            //Collections.sort(elements, Collections.reverseOrder());
            System.out.println("Вывожу отсортированные элементы списка:");
            for (WebElement element:elements) {
                String list = element.getText();
                //element.toString();
                stepOutputInfoInList(list);
                System.out.println(list);
            }
        }
        saveScreenshotPNG(driver, "Ноутбуки");
        System.out.println("Вывод элементов списка закончен.");
        return new MarketPage(driver);
    }

    @Step("Выводим список ноутбуков из Мар <name, price>")
    public MarketPage outputInfoInMap() throws IOException {
        Map<String , String> map = new HashMap<>();
        List<WebElement> elementsName = driver.findElements(By.className("n-snippet-card2__title"));
        List<WebElement> elementsPrice = driver.findElements(By.className("n-snippet-card2__main-price"));
        for(int i=0;i<elementsName.size();i++)
            map.put(elementsName.get(i).getText(), elementsPrice.get(i).getText());
        System.out.println("Размер набора данных : " +map.size()+". Вывожу данные из map:");

        Map<String, String> reversedMap = new TreeMap<String, String>(map);
        for (Map.Entry entry : reversedMap.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        String nameScreen = "Элементы map";
        saveScreenshotPNG(driver, nameScreen);
        System.out.println("Вывод данных из map завершен.");
        return new MarketPage(driver);
    }

    @Step("Выбираю ноутбук: {noteName}")
    public MarketPage openNoteSpec(int number) throws IOException {
        List<WebElement> elementsName = driver.findElements(By.className("n-snippet-card2__title"));
        WebElement note = elementsName.get(number);
        String noteName = note.getText();
        System.out.println("Выбран: "+noteName);
        MoveToElement.moveToElement(note).perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        note.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("n-product-tabs__list")));
        saveScreenshotPNG (driver, noteName);
        openSpec();
        return new MarketPage(driver);
    }

    @Step("Открываю характеристики")
    private MarketPage openSpec() throws IOException {
        WebElement pageNoteSpec = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[3]/div/div/div/ul/li[@data-name='spec']"));
        pageNoteSpec.click();
        //waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.className("/html/body/div[1]/div[6]/div[1]")));
        saveScreenshotPNG (driver, "Характеристики");
        return new MarketPage(driver);
    }

    @Step("Открываю первую подсказку")
    public MarketPage outFirstPopup() throws IOException {
        System.out.println("Ищу поле с подсказкой");
        driver.findElement(By.xpath("/html/body/div[1]/div[6]/div[1]/.//*[text()='?']/..")).click();
        System.out.println("Текст из подсказки:");
        System.out.println(driver.findElement(By.xpath("//div[contains(@class, 'popup_visibility_visible')]//div[@class='n-hint-button__article']")).getText());
        saveScreenshotPNG (driver, "Характеристики");
        return new MarketPage(driver);
    }

    @Step("Отправляю запрос методом GET")
    public MarketPage sendRequestGET(String region) throws IOException  {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, region);
        String responseBody = response.getBody().asString();
        outputResponseInFile(responseBody);
        System.out.println("Response:" + responseBody);
        return new MarketPage(driver);
    }

    @Step("Вывожу response из файла")
    public MarketPage outputResponseInFile(String responseBody) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./src/main/resources/FourthTest.html");
            fileOutputStream.write(responseBody.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        getBytesAnnotationWithArgs("/FourthTest.html");
        return new MarketPage(driver);
    }

    @Step("Поиск товаров из файла {FileName}")
    public MarketPage readAndSearchFromExcel(String FileName) throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream("src/main/resources/"+FileName));
        XSSFSheet myExcelSheet = myExcelBook.getSheet("book1");
        for(int i=0;i<myExcelSheet.getLastRowNum();i++) {
            XSSFRow row = myExcelSheet.getRow(i);
            String noteName = row.getCell(0).getStringCellValue();
            searchNote(noteName);
        }
        return new MarketPage(driver);
    }

    @Step("Ищу: {noteName}")
    public MarketPage searchNote(String noteName) throws IOException {
        System.out.println("Ищу: "+noteName);
        WebElement searchInputLine = driver.findElement(By.id("header-search"));
        searchInputLine.clear();
        searchInputLine.sendKeys(noteName);
        searchInputLine.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setListStyleVisibility();
        saveScreenshotPNG (driver, noteName);
        captureScreen();
        return new MarketPage(driver);
    }




    public MarketPage captureScreen() {
        String path;
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path = "./target/screenshots/" + screenshot.getName();
            FileUtils.copyFile(screenshot, new File(path));
        }
        catch(IOException e) {
            e.getMessage();
        }
        return  new MarketPage(driver);
    }

    /*    private void captureScreen(Integer i) {
        String path;
        try {
            //WebDriver augmentedDriver = new Augmenter().augment(driver);
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path = "./src/main/resources/screenshots/" + "screenshot" + i + ".png";
            FileUtils.copyFile(screenshot, new File(path));
        }
        catch(IOException e) {
            e.getMessage();
        }
    }*/

    //By.xpath(".//*[text()='HP']/..")
    //By.xpath(".//*[contains(text(),'Время работы')]/../..")

}







