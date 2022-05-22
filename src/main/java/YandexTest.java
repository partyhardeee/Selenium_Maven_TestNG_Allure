import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;



public class YandexTest {
    public static ProductPage ProductPage;
    public static FilterPage filter;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        ProductPage = new ProductPage(driver);
        filter = new FilterPage(driver);
        driver.manage().window().maximize();
        driver.get("https://market.yandex.ru/");
        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='_1SD7T'][contains(text(),'Маркет')]"))));}
    @Test
    public void yandexmark() throws InterruptedException {
        filter.findingForm();
        filter.sendFiltr();
        ProductPage.firstPick();
        ProductPage.buttonCompare();
        ProductPage.winFirst();
        filter.unchecknCheck();
        ProductPage.secondPick();
        ProductPage.buttonCompare();
        ProductPage.goCompare();
        ProductPage.assertPrice();
        ProductPage.deletefBasket();
        ProductPage.checkOne();
        ProductPage.deleteAll();
        ProductPage.checkAll(); }
    @AfterClass
    public static void down()
    { driver.quit();}}






