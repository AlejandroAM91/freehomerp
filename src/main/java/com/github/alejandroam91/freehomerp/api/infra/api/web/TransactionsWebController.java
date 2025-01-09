package com.github.alejandroam91.freehomerp.api.infra.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TransactionsWebController {
  @GetMapping("/transactions")
  public String handle(Model model) {
    return "transactions";
  }
}
