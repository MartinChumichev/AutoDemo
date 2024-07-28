package ru.example.qa.dto;

import java.util.List;
import lombok.Data;

@Data
public class GetListUsers{
	private int perPage;
	private int total;
	private List<DataItem> data;
	private int page;
	private int totalPages;
	private Support support;
}