package com.company;

import java.sql.SQLException;

public interface EmployeeDao {
    void addEmpoyee(Employee employee) throws SQLException;
    void updateEmployee(Employee employee) throws SQLException;
    boolean getEmployee(String empName, String empPassword) throws SQLException;



    //void deleteEmployee(Employee employee) throws SQLException;

}
