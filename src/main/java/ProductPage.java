import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;


import java.time.Duration;
import java.util.Set;

public class ProductPage {
    public WebDriver driver;
    private String window2;
    private String window1;

    public ProductPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[4]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]")
    private WebElement braveness;

    @FindBy(xpath = "//a[contains(text(),'Сравнить')]")
    private WebElement compare;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[4]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]")
    private WebElement deleteitem;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[4]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]")
    private WebElement delall;

    @Step("Товар добавляется в сравнение")
    public void buttonCompare() throws InterruptedException {
        Thread.sleep(2000);
        braveness.click();
        Thread.sleep(2000);
    }

    @Step("Переход на страницу первого товара")
    public void firstPick() throws InterruptedException {

        Actions actions = new Actions(driver);
        WebElement time1 = (new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/article[1]/div[3]/a[1]/img[1]"))));

        WebElement first = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/article[1]/div[3]/a[1]/img[1]"));
        actions.moveToElement(first).build().perform();
        window1 = driver.getWindowHandle();
        Thread.sleep(2000);
        first.click();

        Set<String> currentWindows = driver.getWindowHandles();
        Thread.sleep(2000);


        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                window2 = window;
                break;
            }
        }
        driver.switchTo().window(window2);
        Thread.sleep(2000);


    }
    @Step("Переход на главную страницу")
    public void winFirst() throws InterruptedException {
        System.out.println(window1);
        System.out.println(window2);
        driver.close();
        driver.switchTo().window(window1);
        Thread.sleep(2000);
    }
    @Step("Выбор второго продукта из списка")
    public void secondPick() throws InterruptedException {

        Actions actions = new Actions(driver);
        WebElement time3 = (new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/article[1]/div[3]/a[1]/img[1]"))));

        WebElement second = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/article[1]/div[3]/a[1]/img[1]"));
        actions.moveToElement(second).build().perform();
        second.click();

        Set<String> currentWindows = driver.getWindowHandles();
        Thread.sleep(2000);
        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                window2 = window;
                break;
            }
        }
        driver.switchTo().window(window2);
        Thread.sleep(2000);

    }
    @Step("Переход на страницу сравнения")
    public void goCompare(){

        compare.click();
    }
    @Step("Проверка суммы двух товаров и отображения выбранных продуктов на странице")
    public void assertPrice() throws InterruptedException {

        String firstprice = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[2]/div[1]/div[4]/div[1]/div[1]/div[1]/span[1]/span[1]")).getText();
        String secprice = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[2]/div[1]/div[4]/div[1]/div[2]/div[1]/span[1]/span[1]")).getText();
        Thread.sleep(4000);
        String firstint = firstprice.replaceAll("[от ]", "");
        String secint = secprice.replaceAll("[от ]", "");
        Integer a = Integer.parseInt(firstint);
        Integer b = Integer.parseInt(secint);
        int x = a + b;
        Assert.assertTrue(x < 700);
        Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]"))
                .getText().contains("Dreamies"));
        Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/a[1]"))
                .getText().contains("Деревенские лакомства"));
        Thread.sleep(4000);




    }
    @Step("Удаление первого продукта из корзины")
    public void deletefBasket() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(deleteitem).build().perform();
        Thread.sleep(2000);
        deleteitem.click();
    }
    @Step("Проверка того, что удаленного товара нет в корзине")
    public void checkOne() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]"))
                .getText().contains("Dreamies"));
    }
    @Step("Проверка того, что все товары удалены из корзины")
    public void checkAll() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[2]/div[1]/h2[1]"))
                .getText().contains("Сравнивать пока нечего"));
    }
    @Step("Удаление всех товаров из корзины")
    public void deleteAll() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(delall).build().perform();
        Thread.sleep(2000);
        delall.click();
        Thread.sleep(2000);
    }}