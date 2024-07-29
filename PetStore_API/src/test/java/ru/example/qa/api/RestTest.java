package ru.example.qa.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.example.qa.dto.GetListUsers;
import ru.example.qa.rest.RestSpec;
import ru.example.qa.utils.AppProperties;

import static io.restassured.RestAssured.given;

public class RestTest {
    static {
        RestSpec.installRelaxedHttpsValidation(AppProperties.getUrl());
    }

    @Test
    public void checkAvatarAndIdTest() {


        GetListUsers users = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().as(GetListUsers.class);

        users.getData().forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assertions.assertTrue(users.getData().stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
    }
}
