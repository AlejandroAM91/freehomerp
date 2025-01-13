package com.github.alejandroam91.freehomerp.api.core.model;

import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public record Account(@NonNull UUID id, @NonNull String code, @NonNull List<Subaccount> subaccounts) {
  public record Subaccount(@NonNull UUID id, @NonNull String name) {
  }
}
