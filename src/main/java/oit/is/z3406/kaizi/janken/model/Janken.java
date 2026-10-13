package oit.is.z3406.kaizi.janken.model;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PatchExchange;

@Controller
public class Janken {

  @PostMapping("/playJanken")
  public String playJanken(@RequestParam String hand, ModelMap model) {
    String pHand = "";
    String cHand = "グー";
    String result = "";

    if (hand != null)
      pHand = hand;

    if (pHand.equals("グー")) {
      result = "Draw";
    } else if (pHand.equals("チョキ")) {
      result = "You lose...";
    } else if (pHand.equals("パー")) {
      result = "You win!";
    } else {
      result = "エラー";
    }

    model.addAttribute("pHand", pHand);
    model.addAttribute("cHand", cHand);
    model.addAttribute("result", result);
    return "janken";
  }
}
