package com.fipp.models.entities;

import com.fipp.models.enums.*;
import java.math.BigDecimal;
import java.sql.Date;

public class Expense extends Transaction
{
	protected String beneficiary;

	public Expense(int id, int UserId, Date date, BigDecimal value, Method method, String description, Category category, Subcategory subcategory, Status status, String beneficiary) {
		super(id, UserId, date, value, method, description, category, subcategory, status);
		this.beneficiary = beneficiary;
	}


	public String getBeneficiary() {
		return this.beneficiary;
	}

}