package org.fasttrackit.curs18homework.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@With
@Builder (toBuilder = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String product;
    @Column
    private Type type;
    @Column
    private Double amount;
}
