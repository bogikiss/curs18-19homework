package org.fasttrackit.curs18homework;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.curs18homework.model.Transaction;
import org.fasttrackit.curs18homework.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandRunner implements CommandLineRunner {
    private final TransactionRepository repository;

    @Override
    public void run(String... args) throws Exception {

    }
}
