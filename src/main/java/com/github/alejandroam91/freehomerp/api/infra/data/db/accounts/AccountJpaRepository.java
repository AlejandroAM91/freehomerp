package com.github.alejandroam91.freehomerp.api.infra.data.db.accounts;

import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface AccountJpaRepository extends ListCrudRepository<AccountEntity, UUID> {
}
