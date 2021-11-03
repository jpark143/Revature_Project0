package com.company;

import java.sql.*;

public class EmployeeDaoImpl implements EmployeeDao{
    Connection connection;
    public EmployeeDaoImpl() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addEmpoyee(Employee employee) throws SQLException {
        String sql = "insert into customer (empName, empPassword) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getEmpName());
        preparedStatement.setString(2, employee.getEmpPassword());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Employee added!");
            System.out.println(" ");
        }
        else
            System.out.println("Something went wrong...");
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException{
        String sql2 = "update employee set empName = ?, empPassword = ? where empId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setString(1, employee.getEmpName());
        preparedStatement.setString(2, employee.getEmpPassword());
        int count = preparedStatement.executeUpdate();
        if (count > 0){
            System.out.println("Successfully updated employee info!");
            System.out.println(" ");
        }
        else
            System.out.println("Something went wrong...");
    }

    @Override
    public boolean getEmployee(String empName, String empPassword) throws SQLException {
        String sql3 = "select * from employee where empName = ? and empPassword = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql3);
        preparedStatement.setString(1,empName);
        preparedStatement.setString(2, empPassword);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("Logged in");
            System.out.println(" ");
        }
        else
            System.out.println("Wrong input...");
        return resultSet.next();
    }


//    @Override
//    public void deleteEmployee(Employee employee) throws SQLException{
//        String sql = "delete from employee where empId = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setInt(1, employee.getEmpId());
//        int count = preparedStatement.executeUpdate();
//        if (count > 0){
//            System.out.println("Employee successfully deleted!");
//            System.out.println(" ");
//        }else
//            System.out.println("Something went wrong...");
//    }



}
