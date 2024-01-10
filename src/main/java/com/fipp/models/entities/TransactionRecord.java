package com.fipp.models.entities;

import java.math.BigDecimal;
import java.sql.Date;

public class TransactionRecord{
    private int id;
    private Date date;
    private BigDecimal value;
    private String descriptionCategory;

    public TransactionRecord(int id, Date date, BigDecimal value, String descriptionCategory) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.descriptionCategory = descriptionCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDescriptionCategory() {
        return descriptionCategory;
    }

    public void setDescriptionCategory(String descriptionCategory) {
        this.descriptionCategory = descriptionCategory;
    }
}
