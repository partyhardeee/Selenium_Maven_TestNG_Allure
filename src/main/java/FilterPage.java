import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.time.Duration;

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
    private WebElement zoo;

    @FindBy(xpath = "//a[@href='/catalog--lakomstva-dlia-koshek/62819/list?hid=15963668']")
    private WebElement lakomstva;


    @Step("Открытие страницы - Лакомства")
    public void findingForm(){
        Actions actions = new Actions(driver);
        WebElement element1 = (new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(input)));
        input.click();
        WebElement element2 = (new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(zoo)));
        actions.moveToElement(zoo).build().perform();
        lakomstva.click();
    }

    @Step("Устанавливается фильтр по цене и выбирается производитель")
    public void sendFiltr(){
        Actions actions = new Actions(driver);
        WebElement element1 = (new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(dreamiz)));
        if(dreamiz.isDisplayed()){
            actions.moveToElement(dreamiz).build().perform();
            dreamiz.click();}
        else {
            WebElement element2 = (new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[.='Dreamies']//span[@class='_2XaWK']"))));
            actions.moveToElement(dreamiz).build().perform();
            dreamiz.click();
        }
            WebElement element3 = (new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(pole1)));
            actions.moveToElement(pole1).build().perform();
            pole1.click();
            pole1.sendKeys("150");
            pole2.click();
            pole2.sendKeys("350");
            WebElement element4 = (new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(check)));
            actions.moveToElement(check).build().perform();
            check.click();
    }

    @Step("Выбор другого продукта")
    public void unchecknCheck(){
        Actions actions = new Actions(driver);
        WebElement element1 = (new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(dreamiz)));
        actions.moveToElement(dreamiz).build().perform();
        dreamiz.click();
        WebElement element2 = (new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(checkveda)));
        actions.moveToElement(checkveda).build().perform();
        checkveda.click();
    }

}






