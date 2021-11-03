package com.company;

public class Employee {
    private int empId;
    private String empName;
    private String empPassword;

    public Employee(){

    }

    public Employee(int empId, String empName, String empPassword) {
        this.empId = empId;
        this.empName = empName;
        this.empPassword = empPassword;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

}
