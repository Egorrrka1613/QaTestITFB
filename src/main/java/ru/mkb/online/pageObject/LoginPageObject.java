package ru.mkb.online.pageObject;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mkb.online.Base;

/**
 * Класс с описанием старницы авторизации
 */
public class LoginPageObject extends Base {
    private static final Logger LOG = LoggerFactory.getLogger(Base.class);

    public LoginPageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//input[@id = 'txtLogin']")
    private WebElement loginLabel;

    @FindBy(xpath = ".//input[@id = 'txtPassword']")
    private WebElement passwordLabel;

    @FindBy(xpath = ".//p[contains(text(), 'МОСКОВСКИЙ КРЕДИТНЫЙ БАНК')]")
    private WebElement basementAuthPageLabel;

    @FindBy(xpath = ".//div[@id = 'btnLoginStandard']")
    private WebElement loginButton;

    private String dynamicXpathErrorLabel = ".//div[@id = 'errMessage'";

    /**
     * Метод проверки скорости загрузки страницы, ориентируясь на время загрузки "подвала" страницы.
     *
     * @param start
     */
    public void getPageLoadTime(final long start) {
        if (checkBasementLoginPageLabel()) {
            long finish = System.currentTimeMillis() / 1000;
            long totalTime = finish - start;
            LOG.info("Время загрузки страницы " + totalTime + " с.");
            Assert.assertTrue("Проверка, что время загрузки страницы не превышает " + getDriverWaitTime() + " секунд.", totalTime <= getDriverWaitTime());
        } else
            LOG.info("Элемент подвала страницы не найден.");
    }

    /**
     * Метод ожидания загрузки элемента из "подвала" страницы.
     */
    public boolean checkBasementLoginPageLabel() {
        return waitVisibilityOfElement(basementAuthPageLabel);
    }

    /**
     * Метод для ввода логна.
     */
    public void setLogin(final String login) {
        setText(loginLabel, login);
    }

    /**
     * Метод для ввода пароля.
     */
    public void setPassword(final String password) {
        setText(passwordLabel, password);
    }

    /**
     * Клик по кнопке Войти.
     */
    public void clickLoginButton() {
        click(loginButton);
    }

    /**
     * Проверка наличия сообщения об ошибке авторизации.
     */
    public void checkErrorMessageAuth() {
        String errorMessage = " and contains(text(), 'Ошибка аутентификации')]";
        String xpath = dynamicXpathErrorLabel + errorMessage;
        Assert.assertTrue(waitVisibilityOfElementByLocator(xpath));
    }

    /**
     * Проверка наличия сообщения об огрничении ввода логина/пароля на время.
     */
    public void checkMessageBanAuth() {
        String errorMessage = " and contains(text(), 'Вы ввели неправильный логин / пароль 3 раза.')]";
        String xpath = dynamicXpathErrorLabel + errorMessage;
        Assert.assertTrue(waitVisibilityOfElementByLocator(xpath));

    }

    /**
     * Проверка времени блокировки ввода данных.
     */
    public void checkTimeBan() {
        String timeBan = (getText(dynamicXpathErrorLabel + "]").split("через ")[1]);
        LOG.info("Оставшееся время блокировки ввода: " + timeBan);
        Assert.assertTrue("Проверка, что отсчет времени блока стартовал и не превышает 14 минут.", (timeBan.split(" мин")[0].equals("14")));
    }

}
