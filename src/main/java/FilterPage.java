import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.allure.annotations.Step;

public class FilterPage {
    public WebDriver driver;
    public FilterPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//span[@data-auto='filter-range-min']//input")
    private WebElement pole1;

    @FindBy(xpath = "//span[@data-auto='filter-range-max']//input")
    private WebElement pole2;

    @FindBy(xpath = "//div[@data-filter-value-id='offer-shipping_delivery']//span[@class='_3nHaI']")
    private WebElement check;

    @FindBy(xpath = "//span[contains(text(),'Показать всё')]")
    private  WebElement pokaz;

    @FindBy(xpath ="//span[@class='_1ZDAA'][contains(text(),'Деревенские лакомства')]")
    private WebElement checkveda;

    @FindBy(xpath = "//*[.='Dreamies']//span[@class='_2XaWK']")
    private WebElement dreamiz;

    @FindBy(xpath = "//div[@class='_10dWC']")
    private WebElement input;

    @FindBy(xpath = "//span[contains(text(),'Зоотовары')]")
    private WebElement Zoo;

    @FindBy(xpath = "//a[@href='/catalog--lakomstva-dlia-koshek/62819/list?hid=15963668']")
    private WebElement lakomstva;


    @Step("Открытие страницы - Лакомства")
    public void findingForm() throws InterruptedException {
        Actions actions = new Actions(driver);
        input.click();
        Thread.sleep(2000);
        actions.moveToElement(Zoo).build().perform();
        lakomstva.click();
        Thread.sleep(2000);
    }

    @Step("Устанавливается фильтр по цене и выбирается производитель")
    public void sendFiltr() throws InterruptedException {
        Actions actions = new Actions(driver);
        Thread.sleep(4000);
        actions.moveToElement(dreamiz).build().perform();
        Thread.sleep(2000);
        dreamiz.click();
        Thread.sleep(3000);
        actions.moveToElement(pole1).build().perform();
        pole1.click();
        pole1.sendKeys("150");
        pole2.click();
        pole2.sendKeys("350");
        Thread.sleep(2000);
        actions.moveToElement(check).build().perform();
        check.click();
        Thread.sleep(2000);

    }
    @Step("Выбор другого продукта")
    public void unchecknCheck() throws InterruptedException {
        Actions actions = new Actions(driver);

        actions.moveToElement(dreamiz).build().perform();
        Thread.sleep(2000);
        dreamiz.click();
        Thread.sleep(2000);
        actions.moveToElement(checkveda).build().perform();
        Thread.sleep(2000);
        checkveda.click();
        Thread.sleep(3000);
    }

}






