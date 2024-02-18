package org.fasttrackit.curs18homework.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.curs18homework.model.Transaction;
import org.fasttrackit.curs18homework.model.Type;
import org.fasttrackit.curs18homework.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("transactions")
@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @GetMapping
    public List<Transaction> getAllTransactions(String product, Type type, Double minAmount, Double maxAmount) {
        if (product != null) {
            return service.getAllTransactionsByProduct(product);
        }
        if (type != null) {
            return service.getAllTransactionsByType(type);
        }
        if (minAmount != null) {
            return service.getTransactionsBiggerThan(minAmount);
        }
        if (maxAmount != null) {
            return service.getTransactionsSmallerThan(maxAmount);
        }
        if (type != null && minAmount != null) {
            return service.byTypeAndMin(minAmount, type);
        }
        if (type != null && maxAmount != null) {
            return service.byTypeAndMax(maxAmount, type);
        }
        if (minAmount != null && maxAmount != null) {
            return service.byMinAndMax(minAmount, maxAmount);
        }
        if (type != null && minAmount != null && maxAmount != null) {
            return service.byTypeAndMinAndMax(type, minAmount, maxAmount);
        }
        return service.getAllTransactions();
    }

    @GetMapping("{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return service.getTransactionById(id);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction newTransaction) {
        return service.addNewTransaction(newTransaction);
    }

    @PutMapping("{id}")
    public Transaction replaceTransaction(@PathVariable Long id, @RequestBody Transaction replaceTransaction) {
        return service.replaceTransaction(id, replaceTransaction);
    }

    @DeleteMapping("{id}")
    public Transaction deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }
}
