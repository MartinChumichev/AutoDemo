package ru.example.qa.constants;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import ru.example.qa.dto.get.GetListUsers;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum UrlResponseDto implements IUrlResponseDto {
    //GET
    LIST_USERS("/api/users", Map.of(200, GetListUsers.class));

    private final String path;
    private final Map<Integer, Class<?>> responseClassByStatusCode;
    private final Map<Integer, Class<?>> errorResponseClassByStatusCode = Map.of(400, Map.class);
    @Getter
    @Setter
    private static ExtractableResponse<Response> response;

    UrlResponseDto(String path, Map<Integer, Class<?>> responseClassByStatusCode) {
        Map<Integer, Class<?>> totalResponseClassByStatusCode = new HashMap<>(errorResponseClassByStatusCode);
        totalResponseClassByStatusCode.putAll(responseClassByStatusCode);

        this.path = path;
        this.responseClassByStatusCode = totalResponseClassByStatusCode;
    }
}
