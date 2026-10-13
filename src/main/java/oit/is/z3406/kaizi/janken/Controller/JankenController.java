package oit.is.z3406.kaizi.janken.Controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PatchExchange;

@Controller
public class JankenController {
  @GetMapping("/janken")
  public String startJanken() {
    return "janken.html";
  }

  @PostMapping("/janken")
  public String startJankenWithGreeting(@RequestParam String name, ModelMap model) {
    String greeting = "Hi " + name;
    model.addAttribute("greeting", greeting);
    return "janken.html";
  }
}
