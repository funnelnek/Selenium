package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.SeleniumCart;

import java.time.Duration;
import java.util.List;

public class SeleniumCartTest {
    private WebDriver driver;
    private SeleniumCart page;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\artchild\\Desktop\\selenium\\selenium-training\\lib\\chromedriver.exe");
        driver = new ChromeDriver();
        page = new SeleniumCart(driver);
        page.init();
    }

    @DataProvider(name = "items")
    public Object[][] defaultItems() {
        return new Object[][] {
            {"Cucumber"},
            {"Tomato"},
            {"Banana"},
            {"Musk Melon"},
            {"Almonds"},
            {"Water Melon"},
            {"Cashews"},
            {"Walnuts"},
            {"Pears"},
            {"Corn"},
            {"Carrot"}
        };
    }

    @Test(dataProvider = "items")
    public void shouldAddItemToCart(String item) {
        page.addToCart(item);

        String xpath = "//li[@class='cart-item'][" + page.cartTotalItems() +"]";
        WebElement cart = driver.findElement(By.cssSelector(".cart-preview .cart-items"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(cart, By.xpath(xpath)));

        WebElement product = cart.findElement(By.xpath(xpath));

        String name = product.getText().trim();

        Assert.assertTrue(page.isItemInCart(item));
    }
}
