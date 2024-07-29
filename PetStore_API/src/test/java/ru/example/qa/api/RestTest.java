package ru.example.qa.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.example.qa.dto.get.GetListUsers;
import ru.example.qa.dto.post.PostRegister;
import ru.example.qa.dto.post.SuccessReg;
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
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().as(GetListUsers.class);

        users.getData().forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assertions.assertTrue(users.getData().stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
    }

    @Test
    public void checkSuccessReg() {
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        PostRegister user = new PostRegister("eve.holt@reqres.in", "pistol");

        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);

        Assertions.assertNotNull(successReg.getId());
        Assertions.assertNotNull(successReg.getToken());

        Assertions.assertEquals(id, successReg.getId());
        Assertions.assertEquals(token, successReg.getToken());
    }
}
