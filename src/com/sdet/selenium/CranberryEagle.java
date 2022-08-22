package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CranberryEagle {
    private WebDriver driver;

    public CranberryEagle(WebDriver driver) {
        this.driver = driver;
    }

    public void init() {
        driver.manage().window().maximize();
        driver.get("https://www.cranberryeagle.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mc_embed_signup")));
    }

    public void typeName(String name) {
        driver.findElement(By.xpath("//input[starts-with(@name,'FNAME')]")).sendKeys(name);
    }

    public void typeEmail(String email) {
        driver.findElement(By.xpath("//input[starts-with(@name,'EMAIL')]")).sendKeys(email);
    }

    public void submit() {
        driver.findElement(By.id("mc-embedded-subscribe")).click();
    }

    public void run(String name, String email) {
        typeEmail(email);
        typeName(name);
    }

}
