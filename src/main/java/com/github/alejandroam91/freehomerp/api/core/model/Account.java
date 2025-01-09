package com.github.alejandroam91.freehomerp.api.core.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Account {
  @NonNull
  private final UUID id;
  @NonNull
  private final String code;
  @NonNull
  private final List<Subaccount> subaccounts;

  public static Account create(@NonNull final String code) {
    return new Account(UUID.randomUUID(), code, List.of());
  }

  @Getter
  @RequiredArgsConstructor
  public static class Subaccount {
    @With
    private final UUID id;
    @NonNull
    private final String name;
  }
}
