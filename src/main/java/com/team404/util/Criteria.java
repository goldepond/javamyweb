package com.team404.util;

import lombok.Data;

@Data
public class Criteria {
	private int pageNum;
	int amount;
	private String searchType;
	private String searchName;

	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}

}
