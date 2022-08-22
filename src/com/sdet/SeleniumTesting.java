import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.CranberryEagle;
import selenium.Harmony;

public class SeleniumTesting {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\artchild\\Desktop\\selenium\\selenium-training\\lib\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        CranberryEagle page = new CranberryEagle(driver);
        page.init();
        page.run("Robert", "test@email.com");
        driver.close();
    }
}
