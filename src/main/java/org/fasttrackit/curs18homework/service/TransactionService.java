package org.fasttrackit.curs18homework.service;

import lombok.AllArgsConstructor;
import org.fasttrackit.curs18homework.exceptions.ResourceNotFoundException;
import org.fasttrackit.curs18homework.model.Transaction;
import org.fasttrackit.curs18homework.model.Type;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransactionService {
    private final List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public Transaction getTransactionById(String id) {
        return transactions.stream()
                .filter(transaction -> transaction.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Transaction with id:%s was not found".formatted(id)));
    }

    public List<Transaction> getAllTransactionsByProduct(String product) {
        return transactions.stream()
                .filter(transaction -> transaction.product().equals(product))
                .toList();
    }

    public List<Transaction> getAllTransactionsByType(Type type) {
        return transactions.stream()
                .filter(transaction -> transaction.type().equals(type))
                .toList();
    }

    public List<Transaction> getTransactionsBiggerThan(Integer minAmount) {
        return transactions.stream()
                .filter(transaction -> transaction.amount() > minAmount)
                .toList();
    }

    public List<Transaction> getTransactionsSmallerThan(Integer maxAmount) {
        return transactions.stream()
                .filter(transaction -> transaction.amount() < maxAmount)
                .toList();
    }

    public Transaction addNewTransaction(Transaction newTransaction) {
        String newId = UUID.randomUUID().toString();
        transactions.add(newTransaction.withId(newId));
        return getTransactionById(newId);
    }

    public Transaction replaceTransaction(String id, Transaction replaceTransaction) {
        Transaction foundTransaction = getTransactionById(id);
        deleteById(id);
        Transaction updatedTransaction = Transaction.builder()
                .id(foundTransaction.id())
                .product(replaceTransaction.product())
                .type(replaceTransaction.type())
                .amount(replaceTransaction.amount())
                .build();
        transactions.add(updatedTransaction);
        return updatedTransaction;
    }

    public Transaction deleteById(String id) {
        Transaction transactionToBeDeleted = getTransactionById(id);
        transactions.remove(transactionToBeDeleted);
        return transactionToBeDeleted;
    }

    public Map<Type, List<Transaction>> groupByType() {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::type));
    }

    public Map<String, List<Transaction>> groupByProduct() {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::product));
    }
}
