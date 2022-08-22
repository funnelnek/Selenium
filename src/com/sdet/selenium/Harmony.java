package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Harmony {
    private WebDriver driver;

    public Harmony(WebDriver driver) {
        this.driver = driver;
    }

    public void init() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://harmonymuseum.org/");
    }

    public void goToContact() {
        driver.findElement(By.cssSelector("a[href='/contact-us']")).click();
    }

    public WebElement getContactForm() {
        return driver.findElement(By.id("gform_3"));
    }

    public void run() {
        goToContact();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlToBe("https://harmonymuseum.org/contact-us/"));

        WebElement form = getContactForm();

        WebElement firstname = form.findElement(By.id("input_3_7"));
        firstname.sendKeys("Testing");

        WebElement lastname = form.findElement(By.id("input_3_8"));
        lastname.sendKeys("Tester");

        WebElement email = form.findElement(By.id("input_3_3"));
        email.sendKeys("Testing@mail.com");

        WebElement phone = form.findElement(By.id("input_3_4"));
        phone.sendKeys("7685488659");

        Select reason = new Select(form.findElement(By.id("input_3_10")));
        reason.selectByValue("Genealogy Research");

        WebElement message = form.findElement(By.id("input_3_6"));
        message.sendKeys("This is a automation test.");

        WebDriver captcha = driver.switchTo().frame(0);
        captcha.findElement(By.className("recaptcha-checkbox")).click();


        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("recaptcha-checkbox-checked")));

        driver = captcha.switchTo().parentFrame();
        driver.findElement(By.id("gform_submit_button_3")).click();
    }
}
