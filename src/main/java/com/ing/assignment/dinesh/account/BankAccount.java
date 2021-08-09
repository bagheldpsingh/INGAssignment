package com.ing.assignment.dinesh.account;

import com.ing.assignment.dinesh.exceptions.InvalidInputAmountException;
import com.ing.assignment.dinesh.exceptions.LowBalanceException;
import com.ing.assignment.dinesh.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankAccount {
    //private final static Logger LOGGER = Logger.getLogger(BankAccount.class.getName());
    private User user;
    private  AtomicReference<BigDecimal> balance =  new AtomicReference<BigDecimal>(BigDecimal.ZERO);

    public BigDecimal deposit(BigDecimal amount){
        if(amount == null || amount.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidInputAmountException("You can deposite only positive value");
       return balance.accumulateAndGet(amount, (x,y )-> x.add(y));
    }

    public BigDecimal withdraw(BigDecimal amount) throws LowBalanceException,InvalidInputAmountException{
        if(amount == null || amount.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidInputAmountException("You can withdrow only positive value");
        if(balance.accumulateAndGet(amount, (x,y )-> x.subtract(y) ).compareTo(BigDecimal.ZERO) >= 0){
            return balance.get();
        }else  {
            balance.accumulateAndGet(amount, (x,y )-> x.add(y));
            throw new LowBalanceException("Your account balance is low and you can't withdraw money");
        }
    }

    public BigDecimal checkBalance(){
        return balance.get();
    }
   /* public static void main(String ar[]){
        BankAccount bankAccount = new BankAccount();
        System.out.println( bankAccount.deposit(BigDecimal.valueOf(100.01)));
        System.out.println(bankAccount.deposit(BigDecimal.valueOf(200.01)));
        try {
            System.out.println(bankAccount.withdraw(BigDecimal.valueOf(-100.01)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
