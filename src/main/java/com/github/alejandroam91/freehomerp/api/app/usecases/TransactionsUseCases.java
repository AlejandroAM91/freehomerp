package com.github.alejandroam91.freehomerp.api.app.usecases;

import com.github.alejandroam91.freehomerp.api.core.model.Transaction;
import com.github.alejandroam91.freehomerp.api.core.model.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsUseCases {
  private final TransactionRepository transactionRepository;

  public List<Transaction> retrieveTransactions() {
    return transactionRepository.findAll();
  }
}
