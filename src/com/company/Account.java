package com.company;

public class Account {
    private int accountNum;
//    private String accountType;
    private double balance;
    public double amount;
    protected int customer_id;

    public Account(){

    }

    public Account(int accountNum, double balance , int customer_id, double amount){
        this.accountNum = accountNum;
//        this.accountType = accountType;
        this.balance= balance;
        this.customer_id = customer_id;
        this.amount = amount;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

//    public String getAccountType() {
//        return accountType;
//    }
//
//    public void setAccountType(String accountType) {
//        this.accountType = accountType;
//    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

}
