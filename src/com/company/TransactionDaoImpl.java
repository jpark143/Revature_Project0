package com.company;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    Connection connection;
    public TransactionDaoImpl(){
        this.connection = ConnectionFactory.getConnection();
    }
    @Override
    public void addTransaction(Transaction transaction) throws SQLException {
        String sql = "insert into trans (acc_From, acc_To, amount) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, transaction.getAcc_from());
        preparedStatement.setInt(2, transaction.getAcc_to());
        preparedStatement.setDouble(3, transaction.getAmount());
        int count = preparedStatement.executeUpdate();
        if (count > 0){
            System.out.println("Transaction complete!");
            System.out.println(" ");
        }
        else
            System.out.println("Something went wrong...");
    }

    @Override
    public List<Transaction> getAllTransaction() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "select * from trans";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int trans_id = resultSet.getInt(1);
            int accFrom = resultSet.getInt(2);
            int accTo = resultSet.getInt(3);
            double amount = resultSet.getDouble(4);
            Transaction transaction = new Transaction(trans_id, accFrom, accTo, amount);
            transactions.add(transaction);
        }
        return transactions;
    }
}
