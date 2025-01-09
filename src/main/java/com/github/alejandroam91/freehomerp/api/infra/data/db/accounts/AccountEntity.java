package com.github.alejandroam91.freehomerp.api.infra.data.db.accounts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {
  @Id
  @Column(name = "id", nullable = false)
  private UUID id;
  @Column(name = "code", nullable = false)
  private String code;
  @OneToMany(mappedBy = "accountId")
  private List<SubaccountEntity> subaccounts;
}
