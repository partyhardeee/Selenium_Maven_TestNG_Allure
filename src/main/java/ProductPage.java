import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.qameta.allure.Step;
import java.util.Set;

public class ProductPage {
    public WebDriver driver;
    private String window2;
    private String window1;
    public static WebDriverWait wait;

    public ProductPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);

    }

    @FindBy(xpath = "/html[1]/body[1]/div[4]/div[3]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[2]")
    private WebElement braveness;

    @FindBy(xpath = "//a[contains(text(),'Сравнить')]")
    private WebElement compare;

    @FindBy(xpath = "/html[1]/body[1]/div[4]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]")
    private WebElement deleteitem;

    @FindBy(xpath = "/html[1]/body[1]/div[4]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]")
    private WebElement delall;

    @FindBy(xpath = "/html[1]/body[1]/div[4]/div[3]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/article[1]/div[5]/h3[1]/a[1]/span[1]")
    private WebElement firstpack;

    @FindBy(xpath = "/html[1]/body[1]/div[4]/div[3]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/article[1]/div[5]/h3[1]/a[1]/span[1]")
    private WebElement secondpack;

    @Step("Товар добавляется в сравнение")
    public void buttonCompare(){
        Actions actions = new Actions(driver);
        try {
            actions.moveToElement(braveness).build().perform();
            braveness.click();
        }
        catch (TimeoutException e)
        {
            wait.until(ExpectedConditions.visibilityOf(compare));
        }
    }

    @Step("Переход на страницу первого товара")
    public void firstPick(){

        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(firstpack));
        actions.moveToElement(firstpack).build().perform();
        window1 = driver.getWindowHandle();
        wait.until(ExpectedConditions.visibilityOf(firstpack));
        firstpack.click();
        Set<String> currentWindows = driver.getWindowHandles();
        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                window2 = window;
                break;
            }
        }
        driver.switchTo().window(window2);
    }
    @Step("Переход на главную страницу")
    public void winFirst(){
        driver.close();
        driver.switchTo().window(window1);
    }

    @Step("Выбор второго продукта из списка")
    public void secondPick(){
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(secondpack));
        actions.moveToElement(secondpack).build().perform();
        secondpack.click();

        Set<String> currentWindows = driver.getWindowHandles();
        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                window2 = window;
                break;
            }
        }
        driver.switchTo().window(window2);

    }
    @Step("Переход на страницу сравнения")
    public void goCompare(){
        wait.until(ExpectedConditions.visibilityOf(compare));
        compare.click();
    }

    @Step("Проверка суммы двух товаров и отображения выбранных продуктов на странице")
    public void assertPrice(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[4]/div[3]/div[2]/div[1]/div[4]/div[1]/div[1]/div[1]")));
        String firstprice = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[3]/div[2]/div[1]/div[4]/div[1]/div[1]/div[1]")).getText();
        String secprice = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[3]/div[2]/div[1]/div[4]/div[1]/div[2]/div[1]/span[1]/span[1]")).getText();
        String firstint = firstprice.replaceAll("[^0-9]+", "");
        String secint = secprice.replaceAll("[^0-9]+", "");
        int x = Integer.parseInt(firstint) + Integer.parseInt(secint);
        Assert.assertTrue(x < 700);
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Dreamies')]")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Деревенские лакомства')]")).isDisplayed());
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            System.err.println("Не найдены продукты в корзине по локатору");
        }
    }

    @Step("Удаление первого продукта из корзины")
    public void deletefBasket(){
        Actions actions = new Actions(driver);
        try {
        actions.moveToElement(deleteitem).build().perform();
        deleteitem.click();
            }
        catch (NoSuchElementException e)
        {
            e.printStackTrace();
            System.err.println("Не найдены продукты в корзине по локатору");
        }
    }
    @Step("Проверка того, что удаленного товара нет в корзине")
    public void checkOne(){
        try {
            /*
            Не понимаю почему assertFalse не работает, я думал что он должен проверить что элемента не отображен
             */
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[contains(text(),'Dreamies')]")));
            Assert.assertFalse(driver.findElement(By.xpath("//a[contains(text(),'Dreamies')]"))
                    .isDisplayed());
        }
        catch (NoSuchElementException e)
        {
            e.printStackTrace();
            System.err.println("Dreamies удален");
        }
    }
    @Step("Проверка того, что все товары удалены из корзины")
    public void checkAll(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[4]/div[3]/div[2]/div[1]/h2[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[3]/div[2]/div[1]/h2[1]"))
                .getText().contains("Сравнивать пока нечего"));
    }
    @Step("Удаление всех товаров из корзины")
    public void deleteAll(){
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(delall));
        actions.moveToElement(delall).build().perform();
        delall.click();
    }}