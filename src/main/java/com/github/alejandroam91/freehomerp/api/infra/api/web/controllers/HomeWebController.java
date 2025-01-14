package com.github.alejandroam91.freehomerp.api.infra.api.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {
  @GetMapping("/")
  public String handle(Model model) {
    return "home";
  }
}
