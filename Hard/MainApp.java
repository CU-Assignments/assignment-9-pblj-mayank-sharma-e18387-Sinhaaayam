package com.example.banking;

public class MainApp {
    public static void main(String[] args) {
        BankService bankService = new BankService();

        // Setup: create two accounts (only once)
        /*
        Account acc1 = new Account("Alice", 1000.0);
        Account acc2 = new Account("Bob", 500.0);
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var tx = session.beginTransaction();
            session.save(acc1);
            session.save(acc2);
            tx.commit();
        }
        */

        // 1. Successful transfer
        bankService.transferMoney(1, 2, 200.0);

        // 2. Failed transfer (insufficient funds)
        bankService.transferMoney(1, 2, 5000.0);
    }
}
