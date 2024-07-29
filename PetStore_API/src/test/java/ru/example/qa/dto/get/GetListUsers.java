package ru.example.qa.dto.get;

import java.util.List;
import lombok.Data;

@Data
public class GetListUsers{
	private int per_page;
	private int total;
	private List<DataItem> data;
	private int page;
	private int total_pages;
	private Support support;
}