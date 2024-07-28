package ru.example.qa.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class RestSpec {
    public static void installRelaxedHttpsValidation(String ulr) {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ulr)
                .setContentType(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .build();
    }

    public static void installSpec(RequestSpecBuilder requestSpecBuilder) {
        RestAssured.requestSpecification = requestSpecBuilder.build();
    }
}
