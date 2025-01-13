package com.github.alejandroam91.freehomerp.api.infra.data.jpa.accounts;

import com.github.alejandroam91.freehomerp.api.core.model.Account;
import com.github.alejandroam91.freehomerp.api.core.model.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
class JpaAccountRepositoryImpl implements AccountRepository  {
  private final JpaAccountClient jpaAccountClient;
  private final JpaAccountMapper jpaAccountMapper;

  @Override
  public List<Account> findAll() {
    return jpaAccountClient.findAll()
      .stream()
      .map(jpaAccountMapper::mapEntityToModel)
      .toList();
  }
}
