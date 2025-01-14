package com.github.alejandroam91.freehomerp.api.core.model;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record Transaction(@NonNull UUID id, @NonNull LocalDate date, @NonNull String description,
                          @NonNull List<Entry> debitEntries, @NonNull List<Entry> creditEntries) {
  public record Entry(@NonNull UUID accountId, float amount) {
  }
}
