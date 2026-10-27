package oit.is.z3406.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z3406.kaizi.janken.model.Entry;

import oit.is.z3406.kaizi.janken.model.User;
import oit.is.z3406.kaizi.janken.model.UserMapper;

@Controller
public class JankenController {

  @Autowired
  private Entry room;

  @Autowired
  private UserMapper userMapper;

  @GetMapping("/janken")
  public String startJanken(ModelMap model, Principal prin) {
    String loginUser = prin.getName();
    this.room.addUser(loginUser);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("room", this.room);

    // lec04
    ArrayList<User> Users = userMapper.selectAllUser();
    model.addAttribute("users", Users);
    return "janken.html";
  }

  @PostMapping("/janken")
  public String startJankenWithGreeting(@RequestParam String name, ModelMap model) {
    String greeting = "Hi " + name;
    model.addAttribute("greeting", greeting);
    return "janken.html";
  }
}
