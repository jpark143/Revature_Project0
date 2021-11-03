package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{
    Connection connection;
    public CustomerDaoImpl(){
        this.connection = ConnectionFactory.getConnection();
    }
    @Override
    public void addCustomer(Customer customer) throws SQLException {
        String sql = "insert into customer (username, password, email) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, customer.getUsername());
        preparedStatement.setString(2, customer.getPassword());
        preparedStatement.setString(3, customer.getEmail());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Customer added!");
            System.out.println("  ");
        }
        else
            System.out.println("Something went wrong...");
    }

    @Override
    public boolean getCustomer(String username, String password) throws SQLException {
        String sql3 = "select * from customer where username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql3);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("Logged in");
            System.out.println(" ");
            System.out.println("Welcome! " + username + "!");
        }
        else
            System.out.println("Wrong input...");
        return resultSet.next();
    }

    @Override
    public List<Customer> getAllCustomer() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from customer";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String username = resultSet.getString(2);
            String password = resultSet.getString(3);
            String email = resultSet.getString(4);
            int status = resultSet.getInt(5);
            Customer customer = new Customer(id, username, password, email, status);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public List<Customer> getStatus() throws SQLException{
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from customer where status = 0";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String username = resultSet.getString(2);
            String password = resultSet.getString(3);
            String email = resultSet.getString(4);
            int status = resultSet.getInt(5);
            Customer customer = new Customer(id, username, password, email, status);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public void updateStatus(Customer customer) throws SQLException {
        String sql = "update customer set status =? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, customer.getStatus());
        preparedStatement.setInt(2, customer.getId());
        int update = preparedStatement.executeUpdate();
        if (update == 1) {
            System.out.println("Account has been approved!");
            System.out.println(" ");
        }
        else if (update == 0){
            System.out.println("Account has been rejected...");
            System.out.println(" ");
        }
        else
            System.out.println("Something went wrong...");
    }


    @Override
    public Customer getCustomerById(int custId) throws SQLException {
        Customer customer = new Customer();
        String sql = "select * from customer where id = " + custId ;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (resultSet != null){
            int id = resultSet.getInt(1);
            String username = resultSet.getString(2);
            String password = resultSet.getString(3);
            String email = resultSet.getString(4);
            int status = resultSet.getInt(5);
            customer = new Customer(id, username, password, email, status);
        }
        else
            System.out.println("No customer found...");
        return customer;
    }

    @Override
    public void deleteCustomer(int id) throws SQLException{
        String sql = "delete from customer where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if (count == 1){
            System.out.println("Deleting... Complete!");
        }else
            System.out.println("Something went wrong");

    }
}
