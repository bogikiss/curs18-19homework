package org.fasttrackit.curs18homework.repository;

import org.fasttrackit.curs18homework.model.Transaction;
import org.fasttrackit.curs18homework.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByType(Type type);
    List<Transaction> findByMinAmount(Integer minAmount);
    List<Transaction> findByMaxAmount(Integer maxAmount);
    List<Transaction> findByTypeAndMin(Type type, Integer minAmount);

    //Map<Type, Double> groupByTypeToSum(Type type);
    
    /*@Query("select t from Transaction t where t.type=:typevar"){
        List<Transaction> findAllByTypeQuery(@Param("typevar") Type type);
    }*/
}
