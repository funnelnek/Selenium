package selenium;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SeleniumCart {
    private final WebDriver driver;
    private final ArrayList<String> cart = new ArrayList<>();

    public SeleniumCart(WebDriver driver) {
        this.driver = driver;
    }

    public void init() {
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
    }

    public void addToCart(String[] items) {
        for (String item : items) {
            addToCart(item);
        }
    }

    public void addToCart(String item) {
        cart.add(item);

        if (exists(item)) {
            WebElement product = getProductsOnPage().get(cart.size() -1);
            WebElement button = product.findElement(By.cssSelector(".product-action button"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.elementToBeClickable(button));
            button.click();
        }
    }

    public List<WebElement> getProductsOnPage() {
        return driver.findElements(By.cssSelector(".products .product"));
    }

    public String[] getProductNames() {
        String[] names = new String[getProductsOnPage().size()];

        for (int i = 0; i < getProductsOnPage().size(); i++) {
            WebElement product = getProductsOnPage().get(i);
            WebElement heading = product.findElement(By.className("product-name"));
            String[] title = heading.getText().split("-");
            String name = title[0].trim();
            names[i] = name;
        }

        return names;
    }

    public boolean exists(String item) {
        List<String> products = Arrays.asList(getProductNames());
        return products.contains(item);
    }

    public int cartTotalItems() {
        return cart.size();
    }

    public boolean isItemInCart(String item) {
        return cart.contains(item);
    }

    public ArrayList<String> getCart() {
        return cart;
    }
}
