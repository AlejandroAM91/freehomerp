package com.github.alejandroam91.freehomerp.api.infra.data.jpa.accounts;

import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

interface JpaAccountClient extends ListCrudRepository<JpaAccount, UUID> {
}
