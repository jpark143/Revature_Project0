package com.company;

public class TransactionDaoFactory {
    private static TransactionDao dao;
    private TransactionDaoFactory(){

    }

    public static TransactionDao getTransactionDao(){
        if (dao == null){
            dao = new TransactionDaoImpl();
        }
        return dao;
    }
}
