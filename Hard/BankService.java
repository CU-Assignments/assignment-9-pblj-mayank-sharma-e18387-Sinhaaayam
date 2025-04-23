package com.example.banking;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BankService {

    public void transferMoney(int fromAccountId, int toAccountId, double amount) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Account fromAccount = session.get(Account.class, fromAccountId);
            Account toAccount = session.get(Account.class, toAccountId);

            if (fromAccount == null || toAccount == null) {
                throw new RuntimeException("Account not found!");
            }

            if (fromAccount.getBalance() < amount) {
                throw new RuntimeException("Insufficient funds!");
            }

            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);

            session.update(fromAccount);
            session.update(toAccount);

            TransactionRecord record = new TransactionRecord(fromAccountId, toAccountId, amount);
            session.save(record);

            tx.commit();
            System.out.println("Transaction successful!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Transaction failed: " + e.getMessage());
        }
    }
}
