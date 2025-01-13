package com.github.alejandroam91.freehomerp.api.infra.data.jpa.accounts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
class JpaAccount {
  @Id
  @Column(name = "id", nullable = false)
  private UUID id;
  @Column(name = "code", nullable = false)
  private String code;
  @OneToMany(mappedBy = "accountId")
  private List<Subaccount> subaccounts;

  @Entity(name = "subaccounts")
  @Getter
  @Setter
  @NoArgsConstructor
  static class Subaccount {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @JoinColumn(name = "account_id", nullable = false)
    private UUID accountId;
    @Column(name = "name", nullable = false)
    private String name;
  }
}
