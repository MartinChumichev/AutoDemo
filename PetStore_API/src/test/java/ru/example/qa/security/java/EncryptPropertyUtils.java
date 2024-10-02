package ru.example.qa.security.java;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.util.Objects;
import java.util.Properties;

import static ru.example.qa.security.java.ApplicationProperties.loadPropertyFromFile;

@Slf4j
public class EncryptPropertyUtils {
    private static final String secretPropertyKey = "encryptor.password";
    private static final String pathToSecretProperty = "secret.properties";

    static StandardPBEStringEncryptor getEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(Objects.requireNonNull(getSecretPassword()));
        return encryptor;
    }

    private static String getSecretPassword() {
        String password;
        password = getSecretPasswordFromFile();
        if (password != null) return password;
        password = getSecretPasswordFromFlag();
        if (password != null) return password;
        log.info("Не найден пароль к энкриптору");
        return null;
    }

    private static String getSecretPasswordFromFlag() {
        return System.getProperty(secretPropertyKey);
    }

    private static String getSecretPasswordFromFile() {
        try {
            Properties properties = new Properties();
            loadPropertyFromFile(pathToSecretProperty, properties);
            return properties.getProperty(secretPropertyKey);
        } catch (Exception e) {
            log.info("Ошибка чтения файла secret.property" + e);
            return null;
        }
    }
}
