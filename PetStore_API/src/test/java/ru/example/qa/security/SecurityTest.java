package ru.example.qa.security;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.example.qa.security.java.ApplicationProperties;
import ru.example.qa.security.spring.AppConfigForJasyptSimple;
import ru.example.qa.security.spring.PropertyServiceForJasyptSimple;

public class SecurityTest {
    @Test
    public void passwordTest() {
        ApplicationProperties applicationProperties = new ApplicationProperties();
        Assert.assertEquals("Проверка зашифрованного пароля",
                "123QWE456rty!-+", applicationProperties.getProperty("secret.password"));
    }

    @Test
    public void springBootPasswordTest() {
        System.setProperty("jasypt.encryptor.algorithm", "PBEWithMD5AndDES");
        System.setProperty("jasypt.encryptor.iv-generator-classname", "org.jasypt.iv.NoIvGenerator");
        System.setProperty("jasypt.encryptor.password", "HELLO_WORLD");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigForJasyptSimple.class);
        PropertyServiceForJasyptSimple service = context.getBean(PropertyServiceForJasyptSimple.class);

        Assert.assertEquals("123QWE456rty!-+", service.getProperty());
    }
}
