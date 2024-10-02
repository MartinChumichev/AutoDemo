package ru.example.qa.security.spring;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EncryptablePropertySource("config/application-test.properties")
public class AppConfigForJasyptSimple {
    @Bean
    public PropertyServiceForJasyptSimple serviceForJasyptSimple() {
        return new PropertyServiceForJasyptSimple();
    }
}
