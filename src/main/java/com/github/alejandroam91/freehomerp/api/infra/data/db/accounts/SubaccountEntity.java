package com.github.alejandroam91.freehomerp.api.infra.data.db.accounts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "subaccounts")
@Getter
@Setter
@NoArgsConstructor
public class SubaccountEntity {
  @Id
  @Column(name = "id", nullable = false)
  private UUID id;
  @JoinColumn(name = "account_id", nullable = false)
  private UUID accountId;
  @Column(name = "name", nullable = false)
  private String name;
}
