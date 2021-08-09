package com.ing.assignment.dinesh.main;

import com.ing.assignment.dinesh.account.BankAccount;
import com.ing.assignment.dinesh.model.User;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class BankApplication {
    private final List<BankAccount> bankAccount;
    private final Scanner sc;
    private  List<BankAccount> lstBankAccount;

    public BankApplication() {
        bankAccount = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    public BankAccount getABankAccountForUser(String userName, String password) {
        BankAccount bankAccount1= null;
        User user = User.builder().usename(userName).password(password).build();
        Optional<BankAccount> bankAccounts = lstBankAccount.stream().filter(x-> x.getUser().getUsename().equalsIgnoreCase(user.getUsename())).findFirst();
        if(bankAccounts.isPresent()){
            return bankAccounts.get();
        }
        return null;

    }

    private void initializeUser(){
        lstBankAccount= new ArrayList<>();
        User user1 = User.builder().usename("1111").password("12345").build();
        User user2 = User.builder().usename("2222").password("12345").build();
        User user3 = User.builder().usename("3333").password("12345").build();
        lstBankAccount.add(new BankAccount(user1, new AtomicReference<BigDecimal>(BigDecimal.ZERO)));
        lstBankAccount.add(new BankAccount(user2, new AtomicReference<BigDecimal>(BigDecimal.ZERO)));
        lstBankAccount.add(new BankAccount(user3, new AtomicReference<BigDecimal>(BigDecimal.ZERO)));
    }

    public void loginAccount() {
        System.out.println("Please enter yourname:");
        String username = sc.next();
        System.out.println("Please enter your password:");
        String password = sc.next();
        BankAccount bankAccount = getABankAccountForUser(username,password);
        if (bankAccount != null) {
            boolean exitRequested = false;
            while (!exitRequested) {
                printAccountMenu();
                int choice = Integer.parseInt(sc.next());
                switch (choice) {
                    case 1:
                        System.out.println("Please enter deposit amount:");
                        BigDecimal depositAmount = sc.nextBigDecimal();
                        bankAccount.deposit(depositAmount);
                        System.out.println("your current balance is: " +bankAccount.checkBalance());
                        break;
                    case 2:
                        System.out.println("Please enter withdrawal amount:");
                        BigDecimal withdrawAmount = sc.nextBigDecimal();
                        bankAccount.withdraw(withdrawAmount);
                        System.out.println("your current balance is: " +bankAccount.checkBalance());
                        break;
                    case 3:
                        System.out.println("your current balance is: " +bankAccount.checkBalance());
                        break;
                    case 4:
                        exitRequested = true;
                        break;
                    default:
                        System.out.println("Wrong input");
                        break;
                }
            }
        }
    }

    private void printAccountMenu(){
        System.out.println("What would you like to do?" + "\n" +
                "\r" + "1.Deposit money" + "\n" +
                "\r" + "2.Withdrawal money" + "\n" +
                "\r" + "3.Check Balance" + "\n" +
                "\r" + "4.Exit");
    }

    public static void main(String args[]){
        BankApplication bankApplication = new BankApplication();
        bankApplication.initializeUser();
        bankApplication.loginAccount();



    }
}
