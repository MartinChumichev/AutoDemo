package ru.example.qa.security;

import org.junit.Assert;
import org.junit.Test;

public class SecurityTest {
    @Test
    public void passwordTest() {
        ApplicationProperties applicationProperties = new ApplicationProperties();
        Assert.assertEquals("Проверка зашифрованного пароля",
                "123QWE456rty!-+", applicationProperties.getProperty("secret.password"));
    }
}
