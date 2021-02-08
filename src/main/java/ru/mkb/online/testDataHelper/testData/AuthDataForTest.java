package ru.mkb.online.testDataHelper.testData;

import ru.mkb.online.testDataHelper.PropertyLoader;

/**
 * Класс, в котором инициализируюстся данные для теста.
 */
public class AuthDataForTest {
    private String login;
    private String password;

    public AuthDataForTest() {
        this.login = PropertyLoader.loadProperty("login");
        this.password = PropertyLoader.loadProperty("password");
    }

    /**
     * Метод получения логина.
     *
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     * Метод получения пароля.
     *
     * @return
     */
    public String getPassword() {
        return password;
    }
}
