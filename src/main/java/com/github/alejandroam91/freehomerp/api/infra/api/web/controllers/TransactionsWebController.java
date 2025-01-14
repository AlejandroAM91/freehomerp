package com.github.alejandroam91.freehomerp.api.infra.api.web.controllers;

import com.github.alejandroam91.freehomerp.api.app.usecases.TransactionsUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class TransactionsWebController {
  private final TransactionsUseCases transactionsUseCases;

  @GetMapping("/transactions")
  public String handle(Model model, Locale locale) {
//    final var transactions = transactionsUseCases.retrieveTransactions();
    final var transactions = List.of(
      new Transaction(
        LocalDate.of(1991, 1, 1), "Aportacion voluntaria", List.of(
        new TransactionEntryLine(
          new TransactionEntry("banks", "Main account", 2000),
          new TransactionEntry("share_capital", "Partner 1", 1000)
        ), new TransactionEntryLine(null, new TransactionEntry("share_capital", "Partner 2", 1000))
      )
      ), new Transaction(
        LocalDate.of(1991, 1, 1),
        "Aportacion extra",
        List.of(new TransactionEntryLine(
          new TransactionEntry("banks", "Main account", 1000),
          new TransactionEntry("share_capital", "Partner 1", 1000)
        ))
      )
    );

    model.addAttribute("transactions", transactions);
    return "transactions";
  }

  private record Transaction(@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date, String description,
                             List<TransactionEntryLine> entryLines) {
  }

  private record TransactionEntryLine(TransactionEntry debit, TransactionEntry credit) {

  }

  private record TransactionEntry(String accountCode, String subaccountName,
                                  @NumberFormat(style = NumberFormat.Style.CURRENCY) double amount) {
  }
}
