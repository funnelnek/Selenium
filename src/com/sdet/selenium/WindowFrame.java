package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class WindowFrame {
    private WebDriver driver;

    public WindowFrame(WebDriver driver) {
        this.driver = driver;
    }

    public void init() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.htmlquick.com/reference/tags/iframe.html");
    }

    public void switchFrame() {
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[src='iframe-example-1.html']")));
    }

    public String getFrameContent() {
        return driver.getPageSource();
    }

    public void run(){
        switchFrame();
        System.out.println(getFrameContent());
    }

}
