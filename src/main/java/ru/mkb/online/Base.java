package ru.mkb.online;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс для работы с WebDriver и PageObject.
 */
public class Base {
    private static final Logger LOG = LoggerFactory.getLogger(Base.class);
    private WebDriver driver;
    private int driverWaitTime = 10;


    public Base(final WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Метод ждет пока элементы станет видимым.
     * Затем кликает по нему.
     *
     * @param webElement
     */
    protected void click(final WebElement webElement) {
        waitVisibilityOfElement(webElement);
        webElement.click();
    }

    /**
     * Метод ожидания появления элемента на странице.
     *
     * @param webElement
     * @return
     */
    protected boolean waitVisibilityOfElement(final WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, driverWaitTime);
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return false;
    }

    /**
     * Метод ожидания появления элемента на странице.
     *
     * @param xpathLocator
     * @return
     */
    protected Boolean waitVisibilityOfElementByLocator(final String xpathLocator) {
        WebDriverWait wait = new WebDriverWait(driver, driverWaitTime);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return false;
    }

    /**
     * Вводит текст в поле webElement.
     *
     * @param webElement
     * @param string
     */
    protected void setText(final WebElement webElement, final String string) {
        waitVisibilityOfElement(webElement);
        webElement.sendKeys(string);
    }

    /**
     * Получить экземпляр драйвера.
     *
     * @return
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Метод ждет появления элемента на странице.
     * Затем забирает текст элемента.
     *
     * @param xpath
     * @return
     */
    protected String getText(final String xpath) {
        String text = getDriver().findElement(By.xpath(xpath)).getText();
        return text;
    }

    /**
     * Метод получения значения ожидания для драйвера.
     *
     * @return
     */
    public int getDriverWaitTime() {
        return driverWaitTime;
    }
}
