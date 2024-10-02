package ru.example.qa.security.spring;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class PropertyServiceForJasyptSimple {
    @Value("${secret.password}")
    private String property;
}
