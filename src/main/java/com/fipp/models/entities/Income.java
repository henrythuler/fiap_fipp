package com.fipp.models.entities;

import com.fipp.models.enums.*;
import java.math.BigDecimal;
import java.sql.Date;

public class Income extends Transaction
{
	private static int sequenceId = 1;
	protected String payer;

	public Income(int userId, Date date, BigDecimal value, Method method, String description, Category category, Subcategory subcategory, Status status, String payer) {
		super(sequenceId++, userId, date, value, method, description, category, subcategory, status);
		this.payer = payer;
	}

	public Income(int id, int userId, Date date, BigDecimal value, Method method, String description, Category category, Subcategory subcategory, Status status, String payer) {
		super(id, userId, date, value, method, description, category, subcategory, status);
		this.payer = payer;
	}


	public String getPayer() {
		return this.payer;
	}
}