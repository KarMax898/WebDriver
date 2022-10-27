package cookies;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FirstTest {
    WebDriver driver;
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(FirstTest.class);
    @BeforeEach
    public void confidriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
    }
    @AfterEach
    public void end(){
        if (driver!=null)
            driver.quit();
    }
    private void autorization()  {
        String login = "keino9@mail.ru";
        String password = "89A87a66a73a/";
        String locator = "//button[@class='header2__auth js-open-modal']";
        driver.findElement(By.xpath(locator)).click();
        driver.findElement(By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)")).sendKeys(login);
        driver.findElement(By.cssSelector(".js-psw-input")).sendKeys(password);
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).click();

        logger.info("Авторизация прошла успешно");
    }
    @Test
    public void openpage() throws InterruptedException {
        driver.get("http://otus.ru");
        logger.info("Открыта страница отус");
        autorization();
        driver.navigate().refresh();
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie: cookies) {
            logger.info(String.format("name = %s , value = %s",cookie.getName(),cookie.getValue()));
        }
    }
}






