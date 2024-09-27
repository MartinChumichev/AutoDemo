package ru.example.qa.rest;

import lombok.Data;

import java.util.HashMap;

@Data
public class RestClient {
    private HashMap<String, Object> pathParams = new HashMap<>();
    private HashMap<String, Object> queryParams = new HashMap<>();
    private Object response;
    private Object requestBody;

    public <T> T  getResponseToDto() {
        return (T) response;
    }
}
