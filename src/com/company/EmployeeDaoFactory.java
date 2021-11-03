package com.company;

public class EmployeeDaoFactory {
    private static EmployeeDao dao;
    private EmployeeDaoFactory(){

    }
// contains a method and return EmpDao interface
    public static EmployeeDao getEmployeeDao(){
        if (dao == null){
            dao = new EmployeeDaoImpl();
        }
        return dao;
    }
}
