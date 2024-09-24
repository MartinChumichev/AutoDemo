package ru.example.qa.dto.put;

import lombok.Data;

@Data
public class UserTimeResponse {
    private String name;
    private String job;
    private String updatedAt;
}