package com.fipp.dao;

import com.fipp.models.entities.Transaction;
import com.fipp.models.entities.TransactionRecord;

import java.util.ArrayList;

public interface TransactionDao {
    ArrayList<TransactionRecord> getAll();
}
