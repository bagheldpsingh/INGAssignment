package com.ing.assignment.dinesh.account;


import com.ing.assignment.dinesh.exceptions.InvalidInputAmountException;
import com.ing.assignment.dinesh.exceptions.LowBalanceException;
import org.junit.Assert;

import java.math.BigDecimal;

public class BankAccountTest {
    private BankAccount bankAccount;


    @org.junit.Test(expected = InvalidInputAmountException.class)
    public void depositNull() {
        bankAccount = new BankAccount();
        bankAccount.deposit(null);
    }

    @org.junit.Test(expected = InvalidInputAmountException.class)
    public void depositNegativeValue() {
        bankAccount = new BankAccount();
        bankAccount.deposit(BigDecimal.valueOf(-10.01));
    }

    @org.junit.Test()
    public void depositPostiveValue() {
        bankAccount = new BankAccount();
        bankAccount.deposit(BigDecimal.valueOf(10.01));
        Assert.assertEquals(bankAccount.checkBalance(), BigDecimal.valueOf(10.01));
    }


    @org.junit.Test(expected = InvalidInputAmountException.class)
    public void withdrawNullValue() {
        bankAccount = new BankAccount();
        bankAccount.withdraw(null);
    }

    @org.junit.Test(expected = InvalidInputAmountException.class)
    public void withdrawNegativeValue() {
        bankAccount = new BankAccount();
        bankAccount.withdraw(BigDecimal.valueOf(-1000.00));
    }

    @org.junit.Test
    public void withdrawCorrectAmount() {
        bankAccount = new BankAccount();
        bankAccount.deposit(BigDecimal.valueOf(10.01));
        bankAccount.deposit(BigDecimal.valueOf(20.01));
        bankAccount.deposit(BigDecimal.valueOf(30.01));
        bankAccount.withdraw(BigDecimal.valueOf(9.01));
        Assert.assertEquals( BigDecimal.valueOf(51.02), bankAccount.checkBalance());
    }

    @org.junit.Test(expected = LowBalanceException.class)
    public void withdrawAmountMoreThanBalance() {
        bankAccount = new BankAccount();
        bankAccount.deposit(BigDecimal.valueOf(10.01));
        bankAccount.deposit(BigDecimal.valueOf(20.01));
        bankAccount.withdraw(BigDecimal.valueOf(99.01));
    }

    @org.junit.Test
    public void checkBalance() {
        bankAccount = new BankAccount();
        Assert.assertEquals( bankAccount.checkBalance(), BigDecimal.ZERO);
        bankAccount.deposit(BigDecimal.valueOf(60.00));
        Assert.assertEquals( BigDecimal.valueOf(60.00), bankAccount.checkBalance());
    }
}