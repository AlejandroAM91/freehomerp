package com.github.alejandroam91.freehomerp.api.infra.data.jpa.transactions;

import com.github.alejandroam91.freehomerp.api.core.model.Transaction;
import com.github.alejandroam91.freehomerp.api.core.model.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaTransactionsRepositoryImpl implements TransactionRepository {
  @Override
  public List<Transaction> findAll() {
    return List.of(
      new Transaction(UUID.randomUUID(), LocalDate.of(2025,1,1), "Initial investment", List.of(), List.of()),
      new Transaction(UUID.randomUUID(), LocalDate.of(2025,1,1), "Equity contribution", List.of(), List.of())
    );
  }
}
