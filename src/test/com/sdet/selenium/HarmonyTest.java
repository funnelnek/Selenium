package selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HarmonyTest {
    private WebDriver browser;
    private Harmony page;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\artchild\\Desktop\\selenium\\selenium-training\\lib\\chromedriver.exe");
        browser = new ChromeDriver();
        page = new Harmony(browser);

        page.init();
        page.run();
    }

    @AfterTest
    public void close() {
        browser.close();
    }

    @Test
    public void shouldSetHiddenTimestampFieldTo10() {
        WebElement field = browser.findElement(By.cssSelector("[name='_mc4wp_timestamp']"));
        Assert.assertEquals(field.getDomAttribute("value"), "10");
    }

    @Test
    public void shouldSetHiddenElementIdTomc4wp_form_2() {
        WebElement field = browser.findElement(By.cssSelector("[name='_mc4wp_form_element_id']"));
        Assert.assertEquals(field.getDomAttribute("value"), "mc4wp-form-2");
    }

}
