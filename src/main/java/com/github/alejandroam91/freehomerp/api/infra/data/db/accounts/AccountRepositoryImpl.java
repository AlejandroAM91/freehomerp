package com.github.alejandroam91.freehomerp.api.infra.data.db.accounts;

import com.github.alejandroam91.freehomerp.api.core.model.Account;
import com.github.alejandroam91.freehomerp.api.core.model.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
  private final AccountJpaRepository accountJpaRepository;
  private final AccountEntityMapper accountEntityMapper;

  @Override
  public List<Account> findAll() {
    return accountJpaRepository.findAll()
      .stream()
      .map(accountEntityMapper::mapEntityToModel)
      .toList();
  }
}
