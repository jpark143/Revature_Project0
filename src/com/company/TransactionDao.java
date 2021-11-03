package com.company;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDao {
    void addTransaction(Transaction transaction) throws SQLException;
    List<Transaction> getAllTransaction() throws SQLException;

}
