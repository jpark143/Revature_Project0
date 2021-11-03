package com.company;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// class implements DAO interface and provides implementation of all methods

public class AccountDaoImpl implements AccountDao{
    Connection connection;
    public AccountDaoImpl(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addAccount(Account account) throws SQLException {
        String sql = "insert into account (balance) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, account.getAccountType());
        preparedStatement.setDouble(1, account.getBalance());
        int count = preparedStatement.executeUpdate();
        if (count > 0){
            System.out.println("Account has been created!");
            System.out.println(" ");
        }
        else
            System.out.println("Something went wrong...");
    }


    @Override
    public Account getBalance(int accNum) throws SQLException {
        Account account = new Account();
        String sql = "select * from account where accountNum = " + accNum;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (resultSet != null){
            int accountNum = resultSet.getInt(1);
//            String accountType = resultSet.getString(2);
            double balance = resultSet.getDouble(2);
            int customer_id = resultSet.getInt(3);
            double amount = resultSet.getDouble(4);
            account = new Account(accountNum, balance, customer_id, amount);
        }
        else
            System.out.println("Something is wrong...");

        return account;
    }

    @Override
    public void updateAmount(Account account) throws SQLException {
        String sql = "update account set amount = ? where accountNum = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, account.getAmount());
        preparedStatement.setInt(2, account.getAccountNum());
        int update = preparedStatement.executeUpdate();
        if(update > 0){
            System.out.println("Total of $" + account.getAmount() + " selected.");
        }
        else
            System.out.println("Something went wrong...");

    }

    @Override
    public List<Account> getAccount() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "select * from account";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int accountNum = resultSet.getInt(1);
            double balance = resultSet.getDouble(2);
            int customer_id = resultSet.getInt(3);
            double amount = resultSet.getDouble(4);
            Account account = new Account(accountNum, balance, customer_id, amount);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public Account addBalance(int accountNumb, double amo) throws SQLException {
        Account account = new Account();
        String sql = "update account set balance = balance + ? where accountNum = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1,amo);
        preparedStatement.setInt(2, accountNumb);
        preparedStatement.executeUpdate();
        return account;
    }

    @Override
    public Account withdraw(int accountNumb, double amo) throws SQLException {
        Account account = new Account();
        String sql = "update account set balance = balance - ? where accountNum = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, amo);
        preparedStatement.setInt(2, accountNumb);
        preparedStatement.executeUpdate();
        return account;
    }

    @Override
    public void deleteAccount(int id) throws SQLException {
        String sql = "delete from account where accountNum = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if (count == 0){
            System.out.println("Something went wrong...");
        }else
            System.out.println("All set!");
    }
}
