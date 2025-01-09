package com.github.alejandroam91.freehomerp.api.app.usecases;

import com.github.alejandroam91.freehomerp.api.core.model.Account;
import com.github.alejandroam91.freehomerp.api.core.model.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountsUseCases {
  private final AccountRepository accountRepository;

  public List<Account> retrieveAccounts() {
    return accountRepository.findAll();
  }
}
