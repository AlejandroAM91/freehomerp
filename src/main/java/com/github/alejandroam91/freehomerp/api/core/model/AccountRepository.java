package com.github.alejandroam91.freehomerp.api.core.model;

import java.util.List;

public interface AccountRepository {
  List<Account> findAll();
}
