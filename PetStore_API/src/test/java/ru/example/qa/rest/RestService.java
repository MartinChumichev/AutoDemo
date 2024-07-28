package ru.example.qa.rest;

import io.restassured.http.Header;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.example.qa.constants.IUrlResponseDto;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestService {
    public static ExtractableResponse<Response> sendGetRequest(IUrlResponseDto dtoEnum, Map<String, ?> headers,
                                                               RestClient client) {
        ExtractableResponse<Response> response = buildRequestSpec(headers, client)
                .get(dtoEnum.getPath())
                .then().extract();
        client.setResponse(extractResponse(dtoEnum, response));
        return response;
    }

    private static RequestSpecification buildRequestSpec(Map<String, ?> headers, RestClient client) {
        return given()
                .header((Header) headers)
                .queryParams(client.getQueryParams())
                .pathParams(client.getPathParams())
                .when();
    }

    public static Object extractResponse(IUrlResponseDto dtoEnum, ExtractableResponse<Response> response) {
        return response.as(dtoEnum.getResponseClassByStatusCode().get(response.statusCode()));
    }
}
