package ru.example.qa.constants;

import java.util.Map;

public interface IUrlResponseDto {
    String getPath();
    Map<Integer, Class<?>> getResponseClassByStatusCode();
}
