package ru.mkb.online.testDataHelper;

import java.io.*;
import java.util.Properties;

/**
 * Класс для работы с проперти файлами.
 */
public class PropertyLoader {

    /**
     * Возвращаем объект Properties, заполненный из файла "src/main/resources/authData.properties".
     *
     * @return
     */
    public static Properties getAllPropFromFile() throws Exception {
        Properties properties = new Properties();
        try (FileInputStream fs = new FileInputStream("src/main/resources/authData.properties"); InputStreamReader is = new InputStreamReader(fs, "UTF-8")) {
            properties.load(is);
            return properties;
        }
    }

    /**
     * Забираем проперти из указанного файла "src/main/resources/authData.properties".
     *
     * @param key
     * @return
     */
    public static String loadProperty(final String key) {
        Properties properties = null;
        try {
            properties = getAllPropFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}