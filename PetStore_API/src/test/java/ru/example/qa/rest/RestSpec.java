package ru.example.qa.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class RestSpec {
    public static void installRelaxedHttpsValidation(String ulr) {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ulr)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .setRelaxedHTTPSValidation()
                .build();
    }

    public static void installSpec(RequestSpecBuilder requestSpecBuilder) {
        RestAssured.requestSpecification = requestSpecBuilder.build();
    }
}
