package com.bankit.web.utils;

import com.bankit.web.models.Account;
import com.bankit.web.models.Transaction;
import com.bankit.web.models.TransactionType;

import java.time.LocalDateTime;

public class TransactionUtil {

    public static Transaction transactionUtil(TransactionType type, double amount, String description, LocalDateTime creationDate, Account account ){

        if(type == TransactionType.DEBIT){
            double newAmount = -amount;
            Transaction transactionDebit = new Transaction(type,newAmount,description,creationDate,account);
            //transactionDebit.setAccount(account);
            return transactionDebit;
        }else{
            Transaction transactionCredit = new Transaction(type,amount,description,creationDate,account);
            //transactionCredit.setAccount(account);
            return transactionCredit;
        }

    }
}
