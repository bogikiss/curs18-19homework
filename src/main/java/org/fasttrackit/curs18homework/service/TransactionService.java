package org.fasttrackit.curs18homework.service;

import lombok.AllArgsConstructor;
import org.fasttrackit.curs18homework.exceptions.ResourceNotFoundException;
import org.fasttrackit.curs18homework.model.Transaction;
import org.fasttrackit.curs18homework.model.Type;
import org.fasttrackit.curs18homework.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PresentationDirection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    public TransactionService(TransactionRepository repository){
        this.repository = repository;
    }

    //private final List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction with id:%s was not found".formatted(id)));
    }

    /*public List<Transaction> getAllTransactionsByProduct(String product) {
        return repository.findAllByProduct;
    }*/

    public List<Transaction> getAllTransactionsByType(Type type) {
        return repository.findByType(type);
    }

    public List<Transaction> getTransactionsBiggerThan(Integer minAmount) {
        return repository.findByMinAmount(minAmount);
    }

    public List<Transaction> getTransactionsSmallerThan(Integer maxAmount) {
        return repository.findByMaxAmount(maxAmount);
    }

    public List<Transaction> byTypeAndMin(Integer minAmount, Type type) {
        return repository.findByTypeAndMin(type, minAmount);
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

    //NEM MŰKÖDIK, NINCS KÉSZ
    /*public Map<Type, Double> groupByTypeToSum(Type type) {
        return repository.groupByTypeToSum(type);
    }

    public Map<String, List<Transaction>> groupByProduct() {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::product));
    }*/


    //valoszinuleg nem jo, en talaltam ki, google PATCH!!
    public Transaction changeProductAndAmount(Long id, String product, Double amount) {
        Transaction foundTransaction = getTransactionById(id);
        Transaction updatedTransaction = Transaction.builder()
                .id(foundTransaction.getId())
                .product(product)
                .type(foundTransaction.getType())
                .amount(amount)
                .build();
        return repository.save(updatedTransaction);
    }
}
