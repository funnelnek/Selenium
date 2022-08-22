package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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


public class LoginTest {
    private WebDriver browser;
    private SeleniumLogin login;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\artchild\\Desktop\\selenium\\selenium-training\\lib\\chromedriver.exe");
        browser = new ChromeDriver();
        login = new SeleniumLogin(browser);
        login.init();
    }

    @AfterTest
    public void close() {
        browser.close();
    }

    @Test
    public void shouldSignInWithUsernameAndPassword() {
        login.signin("robert", "rahulshettyacademy");

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(3));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout-btn")));
            Assert.assertNotNull(browser.findElement(By.className("logout-btn")));
        } catch (Exception e) {
            Assert.assertFalse(false);
        }

        login.logout();
    }

    @Test
    public void shouldHavePageTitleOfRahulShettyAcademyLoginPage() {
        Assert.assertEquals(browser.getTitle(), "Rahul Shetty Academy - Login page");
    }

    @Test
    public void shouldHaveLoginFormDisplayed() {
        WebElement form = browser.findElement(By.cssSelector(".sign-in-container>form"));
         Assert.assertTrue(form.isDisplayed());
    }

    @Test
    public void shouldDisplayIncorrectUsernameOrPasswordErrorMessage() {
        login.signin("Robert", "password");
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(1));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
            Assert.assertNotNull(browser.findElement(By.className("error")));
        } catch (TimeoutException e) {
            Assert.fail("Did not display Incorrect username or password error message");
        }
    }

    @Test(dependsOnMethods = "shouldDisplayIncorrectUsernameOrPasswordErrorMessage")
    public void  shouldClickOnForgotPassword() {
        login.forgot();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(1));

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("right-panel-active")));
            Assert.assertNotNull(browser.findElement(By.className("right-panel-active")));
        } catch (TimeoutException e) {
            Assert.fail("Did not activate reset password");
        }
    }

    @Test(dependsOnMethods = "shouldClickOnForgotPassword")
    public void shouldResetPassword() {
        String password = login.reset("Robert", "robert@email", "84983849348");
        Assert.assertEquals(password, "rahulshettyacademy");
    }

    @Test(dependsOnMethods = "shouldResetPassword")
    public void shouldGoToSigninForm() {
        login.goToLogin();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(2));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".login-page-container > .container:not(.right-panel-active)")));
        } catch (TimeoutException e) {
            Assert.fail("Did not navigate to sign-in form.");
        }
    }

}
