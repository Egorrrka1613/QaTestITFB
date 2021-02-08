package ru.mkb.online.functionalTests;

import org.junit.Test;
import ru.mkb.online.baseTests.MkbAuthBaseTest;
import ru.mkb.online.testDataHelper.testData.AuthDataForTest;

/**
 * Класс тестов страницы авторизации.
 */
public class FalseAuthorizationTest extends MkbAuthBaseTest {

    /**
     * Тестовый кейс:
     * 1. Перейти на страницу https://online.mkb.ru
     * 2. Страница должна загрузиться в течение 10 секунд
     * 3. Ввести логин Avtotest
     * 4. Ввести пароль 123456
     * 5. Нажать кнопку Войти
     * 6. Проверить ожидаемый результат:  появиться сообщение об ошибке
     * 7. Повторить пункты 4,5 еще 2 раза
     * 8. Проверить ожидаемый результат:  появиться сообщение о блокировке на  15 минут
     */
    @Test
    public void start() {
        AuthDataForTest data = new AuthDataForTest();
        checkTimeLoadPage();
        authToMkb(data);
        invalidAuthCheckBan(data);
    }
}
