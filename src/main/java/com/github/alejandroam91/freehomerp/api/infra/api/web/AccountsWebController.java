package com.github.alejandroam91.freehomerp.api.infra.api.web;

import com.github.alejandroam91.freehomerp.api.app.usecases.AccountsUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AccountsWebController {
  private final AccountsUseCases accountsUseCases;

  @GetMapping("/accounts")
  public String handle(Model model) {
    final var accounts = accountsUseCases.retrieveAccounts();
    model.addAttribute("accounts", accounts);
    return "accounts";
  }
}
