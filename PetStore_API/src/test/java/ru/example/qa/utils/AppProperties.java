package ru.example.qa.utils;

import java.util.HashMap;
import java.util.Map;

public class AppProperties extends PropertyHandler{
    private static final Map<String, AppProperties> propertiesInstancesByStand = new HashMap<>();

    public AppProperties(String pathToProperties) {
        super(pathToProperties);
    }

    public static AppProperties getProperties() {
        //TODO добавить выбор пропертей по стендам
        propertiesInstancesByStand.put("STANDNAME", new AppProperties("config/application-test.properties"));
        return propertiesInstancesByStand.get("STANDNAME");
    }

    public static String getUrl() {
        return getProperties().getPropertiesMap().get("rest.url");
    }
}
