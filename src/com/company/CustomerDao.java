package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    void addCustomer(Customer customer) throws SQLException;
    boolean getCustomer(String username, String password) throws SQLException;
    List<Customer> getStatus() throws SQLException;
    void updateStatus(Customer customer) throws SQLException;
    List<Customer> getAllCustomer() throws SQLException;
    Customer getCustomerById(int id) throws SQLException;
    void deleteCustomer(int id) throws SQLException;

}
