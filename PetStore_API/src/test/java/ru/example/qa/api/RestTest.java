package ru.example.qa.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.example.qa.dto.get.ColorsData;
import ru.example.qa.dto.get.GetListUsers;
import ru.example.qa.dto.post.PostRegister;
import ru.example.qa.dto.post.SuccessReg;
import ru.example.qa.dto.put.UserTime;
import ru.example.qa.dto.put.UserTimeResponse;
import ru.example.qa.rest.RestSpec;
import ru.example.qa.utils.AppProperties;

import java.time.Clock;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RestTest {
    static {
        RestSpec.installRelaxedHttpsValidation(AppProperties.getUrl());
        RestSpec.responseSpecUniq(200);
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

    @Test
    public void sortedYearsTest() {
        List<ColorsData> colors = given()
                .when()
                .get("/api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);
        List<Integer> years = colors.stream().map(ColorsData::getYear).toList();
        List<Integer> sortedYears = years.stream().sorted().toList();
        Assertions.assertEquals(sortedYears, years);
    }

    @Test
    public void deleteUserTest() {
        RestSpec.responseSpecUniq(204);
        given()
                .when()
                .delete("/api/users/2")
                .then().log().all();
    }

    @Test
    public void timeTest() {
        UserTime user = new UserTime("morpheus", "zion resident");
        UserTimeResponse response = given()
                .body(user)
                .when()
                .put("/api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponse.class);
        String currentTime = Clock.systemUTC().instant().toString().replaceAll("(.{8})$", "");
        System.out.println(currentTime);
        Assertions.assertEquals(currentTime, response.getUpdatedAt().replaceAll("(.{5})$", ""));
        System.out.println(response.getUpdatedAt().replaceAll("(.{5})$", ""));
    }
}
