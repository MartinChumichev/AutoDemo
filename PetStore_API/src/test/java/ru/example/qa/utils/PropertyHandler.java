package ru.example.qa.utils;

import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.util.*;

abstract class PropertyHandler {
    private final String PROPERTIES_NAME_PATTERN = "[a-zA-Z.0-9_]+";
    @Getter
    protected final Map<String, String> propertiesMap;

    PropertyHandler(String pathToProperties) {
        this(new HashMap<>());
        loadProperty(pathToProperties);
    }

    public PropertyHandler(Map<String, String> propertiesMap) {
        this.propertiesMap = propertiesMap;
    }

    private void loadProperty(String pathToProperties) {
        Properties extendedProperties = new Properties(); // TODO Добавить чтение пропертей через энкриптор
        List<URL> propertiesURL = getListUrl(pathToProperties);
        if (propertiesURL.isEmpty()) {
            throw new RuntimeException("Нет property файла");
        }

        for (URL url : propertiesURL) {
            try {
                extendedProperties.load(url.openStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!extendedProperties.isEmpty())
            fillPropsMap(extendedProperties);
    }

    public List<URL> getListUrl(String relativePathToResourceFile) {
        List<URL> urlList = new ArrayList<>();

        try {
            Enumeration<URL> urlEnum = Thread.currentThread().getContextClassLoader().getResources(relativePathToResourceFile);
            urlList.addAll(Collections.list(urlEnum));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return urlList;
    }

    public void fillPropsMap(Properties properties) {
        for (String key : properties.stringPropertyNames()) {
            if (key.matches(PROPERTIES_NAME_PATTERN)) {
                String value = properties.getProperty(key);
                if (value.contains("${")) {
                    value = value.replace("${", "").replace("}", "");
                    value = properties.getProperty(value);
                }
                propertiesMap.putIfAbsent(key, value);
            } else {
                throw new RuntimeException("Ключ в некорректном формате");
            }
        }
    }
}
