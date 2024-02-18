package org.fasttrackit.curs18homework.repository;

import org.fasttrackit.curs18homework.model.Transaction;
import org.fasttrackit.curs18homework.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByType(Type type);

    List<Transaction> findAllByAmountGreaterThan(Double minAmount);

    List<Transaction> findByAmountLessThan(Double maxAmount);

    List<Transaction> findByProduct(String product);

    @Query(value = "SELECT * FROM TRANSACTION WHERE type=:typevar AND amount>:minAmount", nativeQuery = true)
    List<Transaction> findByTypeAndMinAmount(@Param("typevar") Type type, @Param("minAmount") Double minAmount);

    @Query(value = "SELECT * FROM TRANSACTION WHERE type=:typevar AND amount<:maxAmount", nativeQuery = true)
    List<Transaction> findByTypeAndMaxAmount(@Param("typevar") Type type, @Param("maxAmount") Double maxAmount);

    @Query(value = "SELECT * FROM TRANSACTION WHERE minAmount>:min AND maxAmount<:max", nativeQuery = true)
    List<Transaction> findByMinAndMaxAmount(@Param("min") Double minAmount, @Param("max") Double maxAmount);

    @Query(value = "SELECT * FROM TRANSACTION WHERE type=:typevar AND minAmount>:min AND maxAmount<:max", nativeQuery = true)
    List<Transaction> findByTypeAndMinAndMaxAmount(@Param("typevar") Type type, @Param("min") Double minAmount, @Param("max") Double maxAmount);
}
