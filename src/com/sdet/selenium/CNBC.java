package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class CNBC {
    private WebDriver driver;

    public CNBC(WebDriver driver) {
        this.driver = driver;
    }

    public void init() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.cnbc.com/");
    }

    public void clickNavMenuButton() {
        driver.findElement(By.className("nav-menu-button")).click();
    }

    public void run() {
        clickNavMenuButton();
    }
}
