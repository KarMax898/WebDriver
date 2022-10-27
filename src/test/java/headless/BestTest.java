package headless;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;
public class BestTest {
    WebDriver driver;
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(BestTest.class);
    @BeforeEach
    public void startup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        logger.info(" Открытие в Headless Режиме");
    }
    @AfterEach
    public void setdow(){
        if (driver != null)
            driver.quit();
        logger.info("Драйвер выполнил работу" );
    }
    @BeforeAll
    public static void  download() {
        WebDriverManager.chromedriver().setup();
    }
    @Test
    public void openpage() {
        String vvod = "ОТУС";
        driver.get("https://duckduckgo.com/");
        driver.findElement(By.cssSelector(".js-search-input")).sendKeys(vvod);
        driver.findElement(By.cssSelector("input.search__button")).click();
        List<WebElement> kyrs = driver.findElements(By.cssSelector("[data-testid='result-title-a'] > span"));
      String dada = kyrs.get(0).getText();
        logger.info(" Драйвер поднят");
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...",
                dada);
        logger.info("сравнение элементов");
    }




}
