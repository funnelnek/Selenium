package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CranberryTownship {
    private WebDriver driver;

    public CranberryTownship(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnContacts() {
        driver.findElement(By.linkText("Contacts")).click();
    }

    public void scrollTo(int pixel) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0," + pixel + ");");
    }
    public void init() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.pghoptical.com/about-us/locations/cranberry-township/");
    }

    public void run() {
        clickOnContacts();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("https://www.pghoptical.com/contacts-2/"));

        scrollTo(500);
    }
}
