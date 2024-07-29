package ru.example.qa.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostRegister {
	private String email;
	private String password;
}