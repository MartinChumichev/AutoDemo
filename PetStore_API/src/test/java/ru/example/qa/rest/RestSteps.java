package ru.example.qa.rest;

import ru.example.qa.constants.UrlResponseDto;

import java.util.LinkedHashMap;

public class RestSteps {
    static RestClient client = new RestClient();
    public static void sendGetRequest(UrlResponseDto service) {
        UrlResponseDto.setResponse(RestService.sendGetRequest(
                service,
                new LinkedHashMap<>(){{put("Content-Type", "application/json");}},
                client));
        client.getQueryParams().clear();
        client.getPathParams().clear();
    }

    public static void sendPostRequest(UrlResponseDto service) {
        UrlResponseDto.setResponse(RestService.sendPostRequest(
                service,
                new LinkedHashMap<>(){{put("Content-Type", "application/json");}},
                client,
                client.getRequestBody()));
        client.getQueryParams().clear();
        client.getPathParams().clear();
    }

    public static void addPathParam(String param, String value) {
        client.getPathParams().put(param, "пустой".equals(value) ? "" : value);
    }

    public static void addQueryParam(String param, String value) {
        client.getQueryParams().put(param, "пустой".equals(value) ? "" : value);
    }
}
