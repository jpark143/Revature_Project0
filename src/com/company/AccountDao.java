package com.company;


import java.sql.SQLException;
import java.util.List;

public interface AccountDao {
    // list of methods I want to implement
    void addAccount(Account account) throws SQLException;
    List<Account> getAccount() throws SQLException;
    Account getBalance(int accountNum) throws SQLException;
    Account addBalance(int accountNumb, double amount) throws SQLException;
    Account withdraw(int accountNumb, double amount) throws SQLException;
    void updateAmount(Account account) throws SQLException;
    void deleteAccount(int id) throws SQLException;
}
