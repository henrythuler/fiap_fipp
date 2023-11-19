package com.fipp.models.entities;

import com.fipp.models.enums.*;
import java.math.BigDecimal;
import java.sql.Date;

public abstract class Transaction
{
	protected int id;
	protected Date date;
	protected BigDecimal value;
	protected Method method;
	protected String description;
	protected Category category;
	protected Subcategory subcategory;
	protected Status status;
	protected int userId;

	
	public Transaction(int id, int userId, Date date, BigDecimal value, Method method, String description, Category category, Subcategory subcategory, Status status) {
		this.id = id;
		this.date = date;
		this.value = value;
		this.method = method;
		this.description = description;
		this.category = category;
		this.subcategory = subcategory;
		this.status = status;
		this.userId = userId;
	}


	public int getId() {
		return id;
	}
	public Date getDate() {
		return date;
	}
	public BigDecimal getValue() {
		return value;
	}
	public Method getMetodo() {
		return method;
	}
	public String getDescription() {
		return description;
	}
	public Category getCategoria() {
		return category;
	}
	public Subcategory getSubcategoria() {
		return subcategory;
	}
	public Status getStatus() {
		return status;
	}
	public int getUserId() {
		return userId;
	}
	public void setId(int id) { this.id = id; }
}
