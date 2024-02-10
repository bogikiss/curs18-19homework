package org.fasttrackit.curs18homework.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.curs18homework.model.Transaction;
import org.fasttrackit.curs18homework.model.Type;
import org.fasttrackit.curs18homework.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("transactions")
@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @GetMapping
    public List<Transaction> getAllTransactions(String product, Type type, Integer minAmount, Integer maxAmount){
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
        return service.getAllTransactions();
    }

    @GetMapping("{id}")
    public Transaction getTransactionById(@PathVariable String id) {
        return service.getTransactionById(id);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction newTransaction){
        return service.addNewTransaction(newTransaction);
    }

    @PutMapping("{id}")
    public Transaction replaceTransaction(@PathVariable String id, @RequestBody Transaction replaceTransaction){
        return service.replaceTransaction(id, replaceTransaction);
    }

    @DeleteMapping("{id}")
    public Transaction deleteById(@PathVariable String id){
        return service.deleteById(id);
    }

    @GetMapping("reports/type")
    public Map<Type, List<Transaction>> getTransactionsOfType(){
        return service.groupByType();
    }

    @GetMapping("reports/product")
    public Map<String, List<Transaction>> getTransactionsOfProduct(){
        return service.groupByProduct();
    }
}
