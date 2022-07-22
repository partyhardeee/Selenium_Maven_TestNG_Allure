import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;



public class YandexTest {
    public static ProductPage ProductPage;
    public static FilterPage filter;
    public static WebDriver driver;
    public static WebDriverWait wait;


    @BeforeClass
    public void setup() {
        driver = getDriver();
        ProductPage = new ProductPage(driver);
        filter = new FilterPage(driver);
        driver.manage().window().maximize();
        driver.get("https://market.yandex.ru/");
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='_1SD7T'][contains(text(),'Маркет')]")));}

    @Test
    public void yandexmark() throws InterruptedException {
        filter.findingForm();

        filter.sendFiltr("150", "350");

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
        ProductPage.checkAll();}


    private WebDriver getDriver() {
        /*
        Не получается сделать путь к драйверу начиная с папки проекта
         */
        System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\IdeaProjects\\Selenium_Maven_TestNG_Allure\\chromedriver.exe");
        return driver = new ChromeDriver();
    }
    @AfterClass (alwaysRun= true)
    public static void down()
    {
        driver.quit();
    }
}






