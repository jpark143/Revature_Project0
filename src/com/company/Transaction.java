package com.company;

public class Transaction {
    private int trans_id;
    public int acc_from;
    public int acc_to;
    public double amount;

    public Transaction(){

    }
    public Transaction(int trans_id, int acc_from, int acc_to, double amount){
        this.trans_id= trans_id;
        this.acc_from = acc_from;
        this.acc_to = acc_to;
        this.amount = amount;
    }

    public int getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }

    public int getAcc_from() {
        return acc_from;
    }

    public void setAcc_from(int acc_from) {
        this.acc_from = acc_from;
    }

    public int getAcc_to() {
        return acc_to;
    }

    public void setAcc_to(int acc_to) {
        this.acc_to = acc_to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
