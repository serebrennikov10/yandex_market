package ru.yandex;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.two_test_package.Note;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class MarketPage extends WebDriverSetting {
    private WebDriver driver;
    private WebDriverWait waitDriver;
    private Actions MoveToElement;
    private Integer firstElementByMaxPrice;
    private Integer firstElementByMinPrice;

    public MarketPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.waitDriver = new WebDriverWait(driver, 20);
        this.MoveToElement = new Actions(driver);
    }

    @FindBy(css = "button[class*='button region-select-form']")
    private WebElement buttonSelectRegion;
    @FindBy(xpath = "//input[contains(@placeholder, 'Укажите другой регион')]")
    private WebElement inputRegion;
    @FindBy(xpath = "/html/body/div[1]/div/div[1]/noindex/div/div/div[2]/div/div[2]/div[1]/span")
    private WebElement changeRegion;
    @FindBy(xpath = "/html/body/div[1]/div/span/div[2]/noindex/div[2]/div/div/div/div[1]")
    private WebElement allCategory;
    @FindBy(xpath = "/html/body/div[1]/div/span/div[2]/noindex/div[1]/div/div/div/div/div/div/div[1]/div/div[3]/a")
    private WebElement compCategory;
    @FindBy(xpath = "/html/body/div[1]/div[2]/div[7]/div/div/div[1]/div/div/div/div/div/div/div[1]/div[2]/ul/li[2]/div/a")
    private WebElement notebookCategory;
    @FindBy(xpath = "/html/body/div[1]/div[5]/div[1]/div[2]/div[2]/div/span/label[1]")
    private WebElement setListStyle;
    @FindBy(linkText = "по цене")
    private WebElement sortPrice;
    @FindBy(xpath = "/html/body/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[3]")
    private WebElement divNameSortPrice;
    @FindBy(xpath = "//input[@id=\"glpricefrom\"]")
    private WebElement selectCostFrom;
    @FindBy(xpath = "//*[@id=\"glpriceto\"]")
    private WebElement selectCostTo;
    @FindBy(xpath = "/html/body/div[1]/div[5]/div[3]/div/div/div/ul/li[@data-name='spec']")
    private WebElement pageNoteSpec;
    @FindBy(xpath = "/html/body/div[1]/div[6]/div[1]/.//*[text()='?']/..")
    private WebElement firstPopup;
    @FindBy(xpath = "//div[contains(@class, 'popup_visibility_visible')]//div[@class='n-hint-button__article']")
    private WebElement popupWindow;
    @FindBy(id = "header-search")
    private WebElement searchInputHeader;

    private By adaptiveLayout = By.className("n-adaptive-layout");
    private By frameInputRegion = By.className("header2-region-popup");
    private By controlHamburger = By.cssSelector("div[class*='n-w-tab__control-hamburger']");
    private By categoryTabsColumn  = By.className("n-w-tabs__tabs-column");
    private By compCategoryPage = By.xpath("/html/body/div[1]/div[2]/div[7]/div/div/div[1]/div/div/div/div/div/div");
    private By noteHeadlineHeader  = By.className("headline__header");
    private By noteSnippetList =  By.xpath("//div[contains(@class, 'n-snippet-list')]");
    private By brandsPack = By.xpath("//*[@id=\"search-prepack\"]/div/div/div[3]/div/div/div[2]/div[4]/div/div/fieldset");
    private By colorsPack = By.xpath("//*[@id=\"search-prepack\"]/div/div/div[3]/div/div/div[2]/div[22]/div/div/fieldset");
    private By elementsNote  = By.xpath("/html/body/div[1]/div[5]/div[2]/div[1]/div[2]/div/div[1]");
    private By noteNameTitle = By.className("n-snippet-card2__title");
    private By noteMainPrice = By.className("n-snippet-card2__main-price");
    private By notePage = By.className("n-product-tabs__list");
    private By specPowerList = By.xpath("/html/body/div[1]/div[6]/div[1]/.//*[text()='Питание']/..");
    private By timeWorkLine = By.xpath(".//*[text()='Время работы']/../..");
    private By valueBatteryLine = By.xpath(".//*[text()='Емкость аккумулятора']/../..");
    private By valueBatteryPowerLine = By.xpath(".//*[text()='Емкость аккумулятора (Вт*ч)']/../..");
    private By cellsLine = By.xpath(".//*[text()='Количество ячеек батареи']/../..");
    private By typeBatteryLine = By.xpath(".//*[text()='Тип аккумулятора']/../..");
    private String asc = "n-filter-sorter i-bem n-filter-sorter_js_inited n-filter-sorter_sort_asc n-filter-sorter_state_select";
    private String desc = "n-filter-sorter i-bem n-filter-sorter_js_inited n-filter-sorter_sort_desc n-filter-sorter_state_select";



    @Step("Открываю страницу")
    public MarketPage openPage() throws IOException {
        driver.get("https://market.yandex.ru/");
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(adaptiveLayout));
        saveScreenshotPNG (driver, "Страница ЯндексМаркет");
        getTitle(driver.getTitle());
        return new MarketPage(driver);
    }

    @Step("Проверяю title")
    public MarketPage getTitle(String title) {
        Assert.assertEquals("Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов", title);
        return new MarketPage(driver);
    }


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

    @Step("Меняю регион")
    public void selectNewRegionOnPage() throws IOException {
        RegionOnPage region1 = RegionOnPage.getInstance();
        //System.out.println(region1.getClass());
        //RegionOnPage region2 = RegionOnPage.getInstance();
        //System.out.println(region2.getClass());
        String nowRegion = changeRegion.getText();
        String newRegion = "Воронеж";
        if (nowRegion.equals(newRegion)) {
            System.out.println("Текущий регион: "+nowRegion+". Смена региона не происходит.");
        }
        else {
            System.out.println("Текущий регион: "+nowRegion+". Запускаю смену региона..");
            changeRegion.click();
            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(frameInputRegion));
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
            MoveToElement.moveToElement(buttonSelectRegion).perform();
            buttonSelectRegion.click();
            System.out.println("Установлен новый регион "+newRegion);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        saveScreenshotPNG (driver, "Установлен регион "+newRegion);
    }


    @Step("Открываю категорий")
    public MarketPage openAllCategories() throws IOException {
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(controlHamburger));
        MoveToElement.moveToElement(allCategory).perform();
        allCategory.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(categoryTabsColumn));
        saveScreenshotPNG (driver, "Все категории");
        return new MarketPage(driver);
    }

    @Step("Открываю категорию: Компьютеры")
    public MarketPage openCompCategory() throws IOException {
        MoveToElement.moveToElement(compCategory).perform();
        compCategory.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(compCategoryPage));
        saveScreenshotPNG (driver, "Компьютерная техника");
        return new MarketPage(driver);
    }

    @Step("Открываю Ноутбуки")
    public MarketPage openNotebookCategory() throws IOException {
        MoveToElement.moveToElement(notebookCategory).perform();
        notebookCategory.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(noteHeadlineHeader));
        saveScreenshotPNG (driver, "Ноутбуки");
        return new MarketPage(driver);
    }

    public void setListStyleVisibility(){
        MoveToElement.moveToElement(setListStyle).perform();
        setListStyle.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Выбираю категории 'Компьютеры' -> 'Ноутбуки'")
    public MarketPage openNoteCategoryNow() throws IOException {
        openAllCategories();
        openCompCategory();
        openNotebookCategory();
        setListStyleVisibility();
        return new MarketPage(driver);
    }

    @Step("Сортирую по цене")
    public MarketPage sortByPrice() throws IOException {
        sortPrice.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(noteSnippetList));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        saveScreenshotPNG(driver, "Сортировка по цене");
        return new MarketPage(driver);
    }

    @Step("Сортирую по возрастанию")
    public MarketPage sortPriceByAsc() throws IOException {
        String classNameSortPrice = divNameSortPrice.getAttribute("class");
        if (classNameSortPrice.equals(asc)) {
        }
        else {
            sortPrice.click();
            System.out.println("Установил сортировку по возрастанию");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        saveScreenshotPNG(driver, "Сортировка по возрастанию (дешевый)");
        return new MarketPage(driver);
    }

    @Step("Сортирую по убыванию")
    public MarketPage sortPriceByDesc() throws IOException {
        String classNameSortPrice = divNameSortPrice.getAttribute("class");
        if (classNameSortPrice.equals(desc)) {
        }
        else {
            sortPrice.click();
            System.out.println("Установлена сортировка по убыванию");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        saveScreenshotPNG(driver, "Сортировка по убыванию (дорогой)");
        return new MarketPage(driver);
    }

    @Step("Задаю параметры поиска по стоимости: от {From} до {To}")
    public MarketPage selectFilterByCost(String From, String To) throws IOException {
        selectCostFrom.click();
        selectCostFrom.sendKeys(From);
        selectCostTo.click();
        selectCostTo.sendKeys(To);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        saveScreenshotPNG(driver, "Установлен фильтр стоимости");
        return new MarketPage(driver);
    }

    @Step("Задаю параметры поиска по бренду: {brand}")
    public MarketPage selectFilterByBrand(String brand) throws IOException {
        List<WebElement> brandsNames = driver.findElements(brandsPack);
        for  (WebElement brandsList:brandsNames) {
            brandsList.findElement(By.xpath("//span[text()='"+brand+"']/..")).click();
            System.out.println("Выбран бренд "+ brand);
            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(noteSnippetList));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            saveScreenshotPNG(driver, brand);
        }
        return new MarketPage(driver);
    }

    @Step("Задаю параметры поиска по цвету: {color}")
    public MarketPage selectFilterByColor(String color) throws IOException {
        List<WebElement> colorsNames = driver.findElements(colorsPack);
        for (WebElement colorList:colorsNames){
            colorList.findElement(By.xpath("//span[text()='Цвет "+color+"']/..")).click();
            System.out.println("Выбран цвет: "+color);
            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(noteSnippetList));
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        saveScreenshotPNG(driver, "Цвет "+color);
        return new MarketPage(driver);
    }

    @Step("Считаю разницу между дорогим и дешевым ноутбуком")
    public MarketPage findNotePriceMinAndMax() throws IOException {
        sortPriceByAsc();
        List<WebElement> elementsAboutInfoNoteMin = driver.findElements(elementsNote);
        for (WebElement elementInfoAboutNoteMin : elementsAboutInfoNoteMin) {
            String firstElementNoteNameMin = elementInfoAboutNoteMin.findElement(noteNameTitle).getText();
            String firstElementNotePriceMin = elementInfoAboutNoteMin.findElement(noteMainPrice).getText();
            firstElementByMinPrice = Integer.parseInt(firstElementNotePriceMin.replaceAll("\\D+", ""));
            System.out.println("Дешевый Ноутбук:");
            System.out.println("Название: "+firstElementNoteNameMin);
            System.out.println("Цена: "+ firstElementByMinPrice +" р.");
            stepFindNoteMinPrice(firstElementNoteNameMin, firstElementByMinPrice);
        }
        sortPriceByDesc();
        List<WebElement> elementsAboutInfoNoteMax = driver.findElements(elementsNote);
        for (WebElement elementInfoAboutNoteMax : elementsAboutInfoNoteMax) {
            String firstElementNoteNameMax = elementInfoAboutNoteMax.findElement(noteNameTitle).getText();
            String firstElementNotePriceMax = elementInfoAboutNoteMax.findElement(noteMainPrice).getText();
            firstElementByMaxPrice = Integer.parseInt(firstElementNotePriceMax.replaceAll("\\D+", ""));
            System.out.println("Дорогой Ноутбук:");
            System.out.println("Название: " + firstElementNoteNameMax);
            System.out.println("Цена: " + firstElementByMaxPrice +" р.");
            stepFindNoteMaxPrice(firstElementNoteNameMax, firstElementByMaxPrice);
        }
        differencePrice(firstElementByMinPrice, firstElementByMaxPrice);
        return new MarketPage(driver);
    }

    @Step("Дешевый ноутбук")
    private void stepFindNoteMinPrice(String noteName, int price){    }

    @Step("Дорогой ноутбук")
    private void stepFindNoteMaxPrice(String noteName, int price){    }

    @Step("Разница между дорогим и дешевым ноутбуком")
    private void differenceLaptops(int minPrice, int maxPrice, int differencePrice) {
    }

    @Step("Считаю разницу")
    private void differencePrice(int min, int max) throws IOException {
        int differencePrice = max - min;
        differenceLaptops(min, max, differencePrice);
        System.out.println("Разница в цене составляет "+differencePrice+" р.");
    }


    class WebElementComparator implements Comparator<WebElement> {
        @Override
        public int compare(WebElement o1, WebElement o2) {
            if (o1 == o2) return 0;
            return o1.getText().compareTo(o2.getText());
        }
    }

    @Step("Список ноутбуков")
    public void stepOutputInfoInList(String list){    }
    @Step("Вывожу список ноутбуков")
    public MarketPage outputInfoInList() throws IOException {
        List<WebElement> elementsAboutInfoNote = driver.findElements(elementsNote);
        for (WebElement elementInfoAboutNote:elementsAboutInfoNote) {
            List<WebElement> elements = new ArrayList<>(elementInfoAboutNote.findElements(noteNameTitle));
            elements.sort(new WebElementComparator()); //сортировка
            System.out.println("Вывожу отсортированные элементы списка:");
            for (WebElement element:elements) {
                System.out.println(element.getText());
            }
            String text = elements.stream().map(WebElement::getText).collect(Collectors.joining("\n"));
            stepOutputInfoInList(text);
        }
        saveScreenshotPNG(driver, "Ноутбуки");
        System.out.println("Вывод элементов списка закончен.");
        return new MarketPage(driver);
    }

    @Step("Список map ноутбуков")
    public void stepOutputInfoInMap(String list){    }
    @Step("Вывожу ноутбуки из Мар <name, price>")
    public MarketPage outputInfoInMap() throws IOException {
        Map<String , String> map = new HashMap<>();
        List<WebElement> elementsName = driver.findElements(noteNameTitle);
        List<WebElement> elementsPrice = driver.findElements(noteMainPrice);
        for(int i=0;i<elementsName.size();i++)
            map.put(elementsName.get(i).getText(), elementsPrice.get(i).getText());
        System.out.println("Размер набора данных : " +map.size()+". Вывожу данные из map:");
        Map<String, String> reversedMap = new TreeMap<String, String>(map);
        for (Map.Entry entry : reversedMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        String text = map.entrySet().stream()
                .map(entry -> { return (entry.getKey() + " - " + entry.getValue()); })
                .collect(Collectors.joining("\n"));
        stepOutputInfoInMap(text);
        saveScreenshotPNG(driver, "Элементы map");
        System.out.println("Вывод данных из map завершен.");
        return new MarketPage(driver);
    }

    @Step("Выбираю ноутбук, порядковый номер - {number}")
    public MarketPage openNoteSpecNow(int number) throws IOException{
        openNote(number);
        openSpec();
        return new MarketPage(driver);
    }

    @Step("Открываю выбранный ноут")
    public MarketPage openNote(int number) throws IOException {
        List<WebElement> elementsName = driver.findElements(noteNameTitle);
        int n = number - 1;
        WebElement note = elementsName.get(n);
        String noteName = note.getText();
        System.out.println("Выбран: "+noteName);
        MoveToElement.moveToElement(note).perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        note.click();
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(notePage));
        saveScreenshotPNG (driver, noteName);
        return new MarketPage(driver);
    }

    @Step("Открываю характеристики")
    public MarketPage openSpec() throws IOException {
        MoveToElement.moveToElement(pageNoteSpec).perform();
        pageNoteSpec.click();
        saveScreenshotPNG (driver, "Характеристики");
        return new MarketPage(driver);
    }

    @Step("Кликаю на первую подсказку")
    public MarketPage outFirstPopup() throws IOException {
        System.out.println("Ищу поле с подсказкой");
        MoveToElement.moveToElement(firstPopup).perform();
        firstPopup.click();
        System.out.println("Текст из подсказки:");
        popupWindow.click();
        saveScreenshotPNG (driver, "открытие popup");
        String popup = popupWindow.getText();
        stepOutPopup(popup);
        return new MarketPage(driver);
    }

    @Step("Вывожу подсказку")
    private void stepOutPopup(String popup){
        System.out.println(popup);
    }


    @Step("Поиск товаров из файла {FileName}")
    public MarketPage readAndSearchFromExcel(String FileName) throws IOException {
        XSSFWorkbook excelFile = new XSSFWorkbook(new FileInputStream("src/main/resources/"+FileName));
        XSSFSheet excelSheet = excelFile.getSheet("book1");
        int rowSize = excelSheet.getLastRowNum()+1;
        for(int i=0;i<rowSize;i++) {
            XSSFRow row = excelSheet.getRow(i);
            String noteName = row.getCell(0).getStringCellValue();
            searchNote(noteName);
        }
        return new MarketPage(driver);
    }

    @Step("Ищу: {noteName}")
    public MarketPage searchNote(String noteName) throws IOException {
        System.out.println("Ищу: "+noteName);
        searchInputHeader.clear();
        searchInputHeader.sendKeys(noteName);
        searchInputHeader.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setListStyleVisibility();
        captureScreen(noteName);
        saveScreenshotPNG (driver, noteName);
        return new MarketPage(driver);
    }



    @Step("Устанавливаю атрибуты")
    public MarketPage setNewAttributes(Note note){
        String timeWork = null;
        String valueBattery = null;
        String valueBatteryPower = null;
        String cells = null;
        String typeBattery = null;
        List<WebElement> blockPowerList = driver.findElements(specPowerList);  ///
        for (WebElement blockPowerElements:blockPowerList) {
            try {
                timeWork = blockPowerElements.findElement(timeWorkLine).getText();

            } catch(NoSuchElementException ignored) {
                timeWork = "Время работы "+"\n"+"Нет значения";
            }
            try {
                valueBattery = blockPowerElements.findElement(valueBatteryLine).getText();
            } catch(NoSuchElementException ignored) {
                valueBattery = "Емкость аккумулятора "+"\n"+"Нет значения";
            }
            try {
                valueBatteryPower = blockPowerElements.findElement(valueBatteryPowerLine).getText();
            } catch(NoSuchElementException ignored) {
                valueBatteryPower = "Емкость аккумулятора (Вт*ч) "+"\n"+"Нет значения";
            }
            try {
                cells = blockPowerElements.findElement(cellsLine).getText();
            } catch(NoSuchElementException ignored) {
                cells = "Количество ячеек батареи "+"\n"+"Нет значения";
            }
            try {
                typeBattery = blockPowerElements.findElement(typeBatteryLine).getText();
            } catch(NoSuchElementException ignored) {
                typeBattery = "Тип аккумулятора "+"\n"+"Нет значения";
            }
        }
        note.setTimeWork(timeWork);
        note.setValueBattery(valueBattery);
        note.setValueBatteryPower(valueBatteryPower);
        note.setCells(cells);
        note.setTypeBattery(typeBattery);
        getAttribyte(note.getTimeWork(), note.getValueBattery(), note.getValueBatteryPower(), note.getCells(), note.getTypeBattery());

        System.out.println(note.getTimeWork());
        System.out.println(note.getValueBattery());
        System.out.println(note.getValueBatteryPower());
        System.out.println(note.getCells());
        System.out.println(note.getTypeBattery());
        System.out.println("-----------------------------------------");
        return new MarketPage(driver);
    }

    @Step("атрибуты")
    private static void getAttribyte(String timeWork, String valueBattery, String valueBatteryPower, String cells, String typeBattery){
    }

    @Step("Сравниваю два ноутбука")
    public void equals(Note one, Note two){
        boolean result = one.equals(two);
        System.out.println("Сравниваю значение: "+result);
        stepEquals(result);
    }

    @Step("Результат")
    public void stepEquals(Boolean result){
    }



    @Attachment(value = "{nameScreen}", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver, String nameScreen) throws IOException {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Вложение", type = "application/json", fileExtension = ".txt")
    public static byte [] getBytesAnnotationWithArgs(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/resources", resourceName));
    }



    private void captureScreen(String noteName) {
        String path;
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path = "./src/main/resources/screenshots/" + noteName + ".png";
            FileUtils.copyFile(screenshot, new File(path));
        }
        catch(IOException e) {
            e.getMessage();
        }
    }


}