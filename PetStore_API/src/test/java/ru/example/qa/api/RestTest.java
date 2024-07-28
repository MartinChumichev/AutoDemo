package ru.example.qa.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import ru.example.qa.dto.GetListUsers;

import static io.restassured.RestAssured.given;

public class RestTest {
//    static {
//        RestSpec.installRelaxedHttpsValidation(AppProperties.getUrl());
//    }

    @Test
    public void checkAvatarAndIdTest() {
//        ExtractableResponse<Response> response = given()
//                .when()
//                .contentType(ContentType.JSON)
//                .get(AppProperties.getUrl() + UrlResponseDto.LIST_USERS.getPath())
//                .then().log().all()
//                .extract();
//
//        GetListUsers list = (GetListUsers) RestService.extractResponse(UrlResponseDto.LIST_USERS, response);

        GetListUsers users = given()
                .spec(new RequestSpecBuilder().setBaseUri("https://reqres.in").setRelaxedHTTPSValidation().build())
                .when()
                .contentType(ContentType.JSON)
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().as(GetListUsers.class); //java.lang.NoClassDefFoundError: io/restassured/internal/common/mapper/ObjectDeserializationContextImpl
    }
}
