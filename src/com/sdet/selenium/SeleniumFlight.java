package selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import unit.BookingPassagers;

public class SeleniumFlight {
    private WebDriver driver;
    private Select currencies;
    private WebElement passagersInfo;
    private WebElement passeagersOptions;
    private WebElement selectedCurrency;
    private String url = "https://rahulshettyacademy.com/dropdownsPractise/";

    public SeleniumFlight(WebDriver driver) {
        this.driver = driver;
        
    }
    
    public void init() {
        driver.manage().window().maximize();
        driver.get(url);
        currencies = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
        passagersInfo = driver.findElement(By.id("divpaxinfo"));
        passeagersOptions = driver.findElement(By.id("divpaxOptions"));
    }

    public WebElement selectCurrency(String option) {
        currencies.selectByValue(option);
        return selectedCurrency = currencies.getFirstSelectedOption();
    }

    public WebElement selectCurrency(int index) {
        currencies.selectByIndex(index);
        return selectedCurrency = currencies.getFirstSelectedOption();
    }

    public WebElement selectCurrencyByIndex(int index) {
        currencies.selectByIndex(index);
        return selectedCurrency = currencies.getFirstSelectedOption();
    }

    public WebElement selectCurrencyByVisibleText(String value) {
        currencies.selectByVisibleText(value);
        return selectedCurrency = currencies.getFirstSelectedOption();
    }

    public WebElement getSelectedCurrency() {
        return selectedCurrency;
    }

    public List<WebElement> getAllSelectedCurrencies() {
        return currencies.getAllSelectedOptions();
    }

    public void showPassagersOptions() throws Exception {
        passagersInfo.click();
        Thread.sleep(2000);
    }

    public void closePassagersOptions() {
        passeagersOptions.findElement(By.id("btnclosepaxoption")).click();;
    }
    public void addAdultPassagers() {
        this.addAdultPassagers(1);
    }

    public void addAdultPassagers(int count) {
        int start = 0;
        WebElement adult = passeagersOptions.findElement(By.id("hrefIncAdt"));
        
        while (start < count) {
            adult.click();
            start++;
        }
    }

    public void typeCountry(String country) throws Exception {
        WebElement autoSuggest = driver.findElement(By.id("autosuggest"));
        autoSuggest.sendKeys(country);
        Thread.sleep(2000);
    }

    public void selectCountry(String country) {
        driver.findElement(By.id("ui-id-1")).findElement(By.linkText(country)).click();
    }

    public void showDestinationFrom() throws Exception {
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        Thread.sleep(2000);
    }

    public void selectFromDestination(String from) throws Exception {
        String selector = "(//a[@value='" + from + "'])[1]";
        driver.findElement(By.xpath(selector)).click();

        Thread.sleep(2000);
    }

    public void selectToDestination(String from) throws Exception {
        String selector = "(//a[@value='" + from + "'])[2]";
        driver.findElement(By.xpath(selector)).click();

        Thread.sleep(2000);
    }

    public void selectDepatureDate(int month, int date) throws Exception {
        String selector = "//td[@data-month='" + month + "'][.//a[text()='"+ date +"']]";
        driver.findElement(By.xpath(selector)).click();

        Thread.sleep(2000);
    }

    public void selectReturnDate(int date) throws Exception {
        String selector = "//td[.//a[@class='ui-state-default'][text()='" + date + "']][2]";
        driver.findElement(By.xpath(selector)).click();

        Thread.sleep(2000);
    }


    public void removeAdultPassagers() {
        this.removeAdultPassagers(1);
    }

    public void removeAdultPassagers(int count) {        
        while (count != 0) {
            passeagersOptions.findElement(By.id("hrefDecAdt")).click();
            count--;
        }
    }

    public void addPassagers(BookingPassagers booking) {
        addAdultPassagers(booking.adults);    
        addChildPassagers(booking.children);
        addInfantPassagers(booking.infants);
    }

    public void addChildPassagers(int count) {
        int start = 0;
        WebElement children = passeagersOptions.findElement(By.id("hrefIncChd"));
        
        while (start < count) {
            children.click();
            start++;
        }
    }

    public void addInfantPassagers(int count) {
        int start = 0;
        WebElement infant = passeagersOptions.findElement(By.id("hrefIncInf"));
        
        while (start < count) {
            infant.click();
            start++;
        }
    }

    public void run() throws Exception {
        selectCurrency(3);

        Thread.sleep(2000);
        selectCurrency("AED");

        Thread.sleep(2000);
        selectCurrencyByVisibleText("INR");

        showPassagersOptions();    
        addPassagers(new BookingPassagers(1, 2, 1));
        closePassagersOptions();

        showDestinationFrom();
        selectFromDestination("MAA");
        selectToDestination("BLR");
        selectDepatureDate(6, 23);

        typeCountry("ind");
        selectCountry("Indonesia");
    }
}
    