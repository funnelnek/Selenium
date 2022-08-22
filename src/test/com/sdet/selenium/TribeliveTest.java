package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;


@SuppressWarnings("SpellCheckingInspection")
public class TribeliveTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\artchild\\Desktop\\selenium\\selenium-training\\lib\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.triblive.com");
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }


    @Test
    public void shouldRunExeterSelenium2Lesson2Test() {
        String myHeader;

        List<WebElement> headlines = driver.findElements(By.className("headline"));

        System.out.println(headlines.size());

        for (WebElement webElement : headlines)
        {
            String name = webElement.getText();
            System.out.println(name);
        }

        myHeader = driver.findElement(By.xpath("//h2[@class='focus']")).getText();
        System.out.println("myHeader is "+myHeader);
    }

    @Test
    public void shouldPrintTheContentOfTheFirstInstanceOfAnH1ElementContainingAClassAttributeOfFocus() {
        String headline = driver.findElement(By.cssSelector("h1.focus")).getText();
        System.out.println(headline);
        Assert.assertNotNull(headline);
    }

}
