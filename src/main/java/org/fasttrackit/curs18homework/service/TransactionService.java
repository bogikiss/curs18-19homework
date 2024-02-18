package org.fasttrackit.curs18homework.service;

import org.fasttrackit.curs18homework.exceptions.ResourceNotFoundException;
import org.fasttrackit.curs18homework.model.Transaction;
import org.fasttrackit.curs18homework.model.Type;
import org.fasttrackit.curs18homework.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction with id:%s was not found".formatted(id)));
    }

    public List<Transaction> getAllTransactionsByProduct(String product) {
        return repository.findByProduct(product);
    }

    public List<Transaction> getAllTransactionsByType(Type type) {
        return repository.findByType(type);
    }

    public List<Transaction> getTransactionsBiggerThan(Double minAmount) {
        return repository.findAllByAmountGreaterThan(minAmount);
    }

    public List<Transaction> getTransactionsSmallerThan(Double maxAmount) {
        return repository.findByAmountLessThan(maxAmount);
    }

    public List<Transaction> byTypeAndMin(Double minAmount, Type type) {
        return repository.findByTypeAndMinAmount(type, minAmount);
    }

    public List<Transaction> byTypeAndMax(Double maxAmount, Type type) {
        return repository.findByTypeAndMaxAmount(type, maxAmount);
    }

    public List<Transaction> byMinAndMax(Double maxAmount, Double minAmount) {
        return repository.findByMinAndMaxAmount(maxAmount, minAmount);
    }

    public List<Transaction> byTypeAndMinAndMax(Type type, Double maxAmount, Double minAmount) {
        return repository.findByTypeAndMinAndMaxAmount(type, maxAmount, minAmount);
    }

    public Transaction addNewTransaction(Transaction newTransaction) {
        return repository.save(newTransaction);
    }

    public Transaction replaceTransaction(Long id, Transaction replaceTransaction) {
        Transaction foundTransaction = getTransactionById(id);
        Transaction updatedTransaction = Transaction.builder()
                .id(foundTransaction.getId())
                .product(replaceTransaction.getProduct())
                .type(replaceTransaction.getType())
                .amount(replaceTransaction.getAmount())
                .build();
        return repository.save(updatedTransaction);
    }

    public Transaction deleteById(Long id) {
        Transaction transactionToBeDeleted = getTransactionById(id);
        repository.deleteById(id);
        return transactionToBeDeleted;
    }
}
