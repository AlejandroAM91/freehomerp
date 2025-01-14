package com.github.alejandroam91.freehomerp.api.infra.api.web;

import com.github.alejandroam91.freehomerp.api.app.usecases.TransactionsUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransactionsWebController {
  private final TransactionsUseCases transactionsUseCases;

  @GetMapping("/transactions")
  public String handle(Model model) {
//    final var transactions = transactionsUseCases.retrieveTransactions();
    final var transactions = List.of(
        new Transaction(
          LocalDate.of(1991, 1, 1),
          "Nacimiento de alex"
//          List.of(
//            new TransactionEntry()
//          ),
//          List.of(
//            new TransactionEntry(),
//            new TransactionEntry()
//          )
        )
      );
    model.addAttribute("transactions", transactions);
    return "transactions";
  }

  private record Transaction(LocalDate date, String description) {
  }
//  private record Transaction(List<TransactionEntry> debit, List<TransactionEntry> credit) {
//  }

//  private record TransactionEntry() {
//  }


//  @GetMapping("/transactions")
//  public String handle(Model model) {
//    model.addAttribute(
//      "transactions", List.of(
//        new Transaction(
//          UUID.randomUUID(), List.of(
//          new TransactionLine(
//            new TransactionEntry(new TransactionAccount("share_capital", "Partner 1"), 1000),
//            new TransactionEntry(new TransactionAccount("banks", "Main"), 2000)
//          ),
//        new TransactionLine(
////          null,
//          new TransactionEntry(new TransactionAccount("share_capital", "Partner 1"), 1000),
//            null
//          )
//        )//,
////      new Transaction(UUID.randomUUID(), List.of(new TransactionLine(), new TransactionLine())),
////      new Transaction(UUID.randomUUID(), List.of(new TransactionLine())),
////      new Transaction(UUID.randomUUID(), List.of(new TransactionLine()))
//        )
//      )
//    );
//    return "transactions";
//  }
//
//  private record Transaction(UUID id, List<TransactionLine> transactionLines) {
//  }
//
//  private record TransactionLine(TransactionEntry credit, TransactionEntry debit) {
//  }
//
//  private record TransactionEntry(TransactionAccount account, float amount) {
//  }
//
//  private record TransactionAccount(String accountCode, String subaccountName) {
//  }
}
