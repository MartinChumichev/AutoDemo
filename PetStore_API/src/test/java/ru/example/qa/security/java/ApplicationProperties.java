package ru.example.qa.security.java;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.properties.EncryptableProperties;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

@Slf4j
public class ApplicationProperties {
    private static final String pathToApplicationProperties = "config/application-test.properties";
    private final Properties properties;

    public ApplicationProperties() {
        properties = new EncryptableProperties(EncryptPropertyUtils.getEncryptor());
        loadPropertyFromFile(pathToApplicationProperties, properties);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    static void loadPropertyFromFile(String path, Properties properties) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> urls = classLoader.getResources(path);
            URL url = urls.nextElement();
            InputStream inputStream = url.openStream();
            properties.load(inputStream);
        } catch (IOException e) {
            log.info("Ошибка чтения из файла " + path + "\n" + e);
        }
    }
}
