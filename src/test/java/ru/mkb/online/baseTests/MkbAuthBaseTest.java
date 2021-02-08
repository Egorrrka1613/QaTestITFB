package ru.mkb.online.baseTests;

import ru.mkb.online.ChromeBaseTest;
import ru.mkb.online.pageObject.LoginPageObject;
import ru.mkb.online.testDataHelper.testData.AuthDataForTest;

/**
 * Класс с базовыми тестами для страницы авторизации.
 */
public class MkbAuthBaseTest extends ChromeBaseTest {
    private final int authIteration = 2;

    /**
     * Метод проверяет как быстро загрузиласть страница авторизации.
     */
    public void checkTimeLoadPage() {
        LoginPageObject au = new LoginPageObject(getDriver());
        au.getPageLoadTime(getStartLoadPageTime());
    }

    /**
     * Метод для авторизации в ЛК.
     */
    public void authToMkb(final AuthDataForTest data) {
        LoginPageObject au = new LoginPageObject(getDriver());
        au.setLogin(data.getLogin());
        au.setPassword(data.getPassword());
        au.clickLoginButton();
        au.checkErrorMessageAuth();
    }

    /**
     * Метод заданное количество раз вводит пароль, затем проверяет наличие сообщения о блокировке входа.
     */
    public void invalidAuthCheckBan(final AuthDataForTest data) {
        LoginPageObject au = new LoginPageObject(getDriver());
        for (int x = 0; x < authIteration; x++) {
            au.setPassword(data.getPassword());
            au.clickLoginButton();
        }
        au.checkMessageBanAuth();
        au.checkTimeBan();
    }
}