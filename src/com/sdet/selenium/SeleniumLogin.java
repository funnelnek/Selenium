package selenium;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumLogin {
    private WebDriver driver;
    private String url = "https://rahulshettyacademy.com/locatorspractice/";
    private String resetPassword = "";

    public SeleniumLogin(WebDriver driver) {
        this.driver = driver;
    }
    
    public void init() {        
        driver.get(url);
    }

    public void signin(String identity, String credential) {
        WebElement username = this.driver.findElement(By.id("inputUsername"));
        username.sendKeys(identity);

        WebElement password = this.driver.findElement(By.name("inputPassword"));
        password.sendKeys(credential);

        WebElement remember = this.driver.findElement(By.id("chkboxOne"));
        remember.click();

        WebElement terms = this.driver.findElement(By.id("chkboxTwo"));
        terms.click();

        WebElement submit = this.driver.findElement(By.className("submit"));
        submit.submit();
    }

    public void forgot() {
        WebElement forgot = driver.findElement(By.linkText("Forgot your password?"));            
        forgot.click();
    }

    public String reset(String name, String email, String phone) {
        WebElement user = driver.findElement(By.cssSelector("input[placeholder='Name']"));
        user.sendKeys(name);

        WebElement emailInput = driver.findElement(By.cssSelector("input[placeholder='Email']"));
        emailInput.sendKeys(email);

        WebElement phoneInput = driver.findElement(By.cssSelector("input[placeholder^='Phone']"));
        phoneInput.sendKeys(phone);

        WebElement reset = driver.findElement(By.className("reset-pwd-btn"));
        reset.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("infoMsg")));

        WebElement info = driver.findElement(By.className("infoMsg"));
        String infoMessage = info.getText();
        
        Matcher re = Pattern.compile("'(.*)'").matcher(infoMessage);

        if (re.find()) {
            resetPassword = re.group(1);           
        }        
        
        return resetPassword;
    }

    public void goToLogin() {
        WebElement goToLogin = driver.findElement(By.xpath("//button[contains(@class, 'go-to-login')]"));
        goToLogin.click();
    }

    public void logout() {
        WebElement logout = driver.findElement(By.className("logout-btn"));
        logout.click();
    }

    public void run(String name, String email, String phone, String password) throws Exception {
        boolean isSignedIn = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


        this.init();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".login-page-container > .container")));

        this.signin(email, password);

        try {
            driver.findElement(By.cssSelector(".login-page-container > .container"));
        } catch (NoSuchElementException exception) {
            isSignedIn = true;
        }

        if (!isSignedIn) {
            this.forgot();

            wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("right-panel-active")));

            String tempPassword = this.reset(name, email, phone);

            if (tempPassword.length() > 0) {
                this.goToLogin();

                wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("right-panel-active")));
                this.signin(email, tempPassword);
                isSignedIn = true;
            }
        }

        if (isSignedIn) {
            Thread.sleep(3000);
            this.logout();
        }
    }
}
