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
        /*if (product != null) {
            return service.getAllTransactionsByProduct(product);
        }*/

        if (type != null) {
            return service.getAllTransactionsByType(type);
        }
        if (minAmount != null) {
            return service.getTransactionsBiggerThan(minAmount);
        }
        if (maxAmount != null) {
            return service.getTransactionsSmallerThan(maxAmount);
        }
        if (minAmount != null && type != null) {
            return service.byTypeAndMin(minAmount, type);
        }
        return service.getAllTransactions();
    }

    @GetMapping("{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return service.getTransactionById(id);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction newTransaction){
        return service.addNewTransaction(newTransaction);
    }

    @PutMapping("{id}")
    public Transaction replaceTransaction(@PathVariable Long id, @RequestBody Transaction replaceTransaction){
        return service.replaceTransaction(id, replaceTransaction);
    }

    @PatchMapping("{id}")
    public Transaction changeProductAndAmount(@PathVariable Long id, @RequestBody String product, @RequestBody Double amount){
        return service.changeProductAndAmount(id, product, amount);
    }

    @DeleteMapping("{id}")
    public Transaction deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }

    /*@GetMapping("reports/type")
    public Map<Type, Double> groupByTypeToSum(Type type){
        return service.groupByTypeToSum(type);
    }

    @GetMapping("reports/product")
    public Map<String, List<Transaction>> getTransactionsOfProduct(){
        return service.groupByProduct();
    }*/
}
