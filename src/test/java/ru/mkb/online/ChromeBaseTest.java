package ru.mkb.online;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Базовый класс от которого должны наследоваться
 * тестды для запуска через Chrome.
 */
public class ChromeBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(ChromeBaseTest.class);
    protected WebDriver driver;
    private long startLoadPageTime;

    /**
     * Метод получения экземпляра драйвера.
     *
     * @return
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Метод для получения времени начала загрузки страницы.
     *
     * @return
     */
    public long getStartLoadPageTime() {
        return startLoadPageTime;
    }


    @Rule
    public TestWatcher watcher = new TestWatcher() {

        /**
         * Переопределяем метод starting.
         *
         * @param description
         */
        @Override
        protected void starting(final Description description) {
            URL url = null;

            {
                try {
                    url = new URL("http://localhost:9515");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

            driver = new RemoteWebDriver(url, DesiredCapabilities.chrome());
            driver.manage().window().maximize();
            startLoadPageTime = System.currentTimeMillis() / 1000;
            driver.get("https://online.mkb.ru");
        }

        /**
         * Переопределяем метод finished.
         *
         * @param description
         */
        @Override
        protected void finished(final Description description) {
            LOG.info("finished");
            driver.quit();
        }
    };
}
