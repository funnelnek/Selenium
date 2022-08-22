package selenium;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class CranberryTest {
    private WebDriver browser;
    private CranberryEagle page;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\artchild\\Desktop\\selenium\\selenium-training\\lib\\chromedriver.exe");
        browser = new ChromeDriver();
        page = new CranberryEagle(browser);
        page.init();
    }

    @AfterTest
    public void close() {
        browser.close();
    }

    @Test
    public void shouldDisplayTheForm() {
        WebElement form = browser.findElement(By.id("mc_embed_signup"));
        Assert.assertTrue(form.isDisplayed());
    }

    @Test(dependsOnMethods = "shouldDisplayTheForm")
    public void shouldEnterEmailAddress() {
        String email = "testing@email.com";
        page.typeEmail(email);
        Assert.assertEquals(browser.findElement(By.id("mce-EMAIL")).getAttribute("value"), email);
    }

    @Test(dependsOnMethods = "shouldDisplayTheForm")
    public void shouldEnterFirstName() {
        String name = "Testing";
        page.typeName(name);
        Assert.assertEquals(browser.findElement(By.id("mce-FNAME")).getAttribute("value"), name);
    }

    @Test(dependsOnMethods = {"shouldEnterEmailAddress", "shouldEnterFirstName"})
    public void shouldSubmitForm() {
        page.submit();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mce-success-response")));

        Assert.assertNotNull(browser.findElement(By.id("mce-success-response")));
    }
}
