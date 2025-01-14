package com.github.alejandroam91.freehomerp.api.core.model;

import java.util.List;

public interface TransactionRepository {
  List<Transaction> findAll();
}
