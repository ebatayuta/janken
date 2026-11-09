package oit.is.z3406.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z3406.kaizi.janken.model.Entry;

import oit.is.z3406.kaizi.janken.model.User;
import oit.is.z3406.kaizi.janken.model.UserMapper;
import oit.is.z3406.kaizi.janken.model.Match;
import oit.is.z3406.kaizi.janken.model.MatchMapper;

@Controller
public class JankenController {

  @Autowired
  private Entry room;

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private MatchMapper matchMapper;

  @GetMapping("/janken")
  public String startJanken(ModelMap model, Principal prin) {
    String loginUser = prin.getName();
    this.room.addUser(loginUser);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("room", this.room);

    // lec04
    ArrayList<User> Users = userMapper.selectAllUser();
    model.addAttribute("users", Users);
    ArrayList<Match> Matches = matchMapper.selectAllMatch();
    model.addAttribute("matches", Matches);
    return "janken.html";
  }

  @PostMapping("/janken")
  public String startJankenWithGreeting(@RequestParam String name, ModelMap model) {
    String greeting = "Hi " + name;
    model.addAttribute("greeting", greeting);
    return "janken.html";
  }

  @GetMapping("/match")
  public String matching(@RequestParam Integer id, ModelMap model, Principal prin) {
    String loginUser = prin.getName();
    this.room.addUser(loginUser);
    User user = userMapper.selectById(id);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("opponent", user.getUserName());
    return "match.html";
  }

  @GetMapping("/fight")
  public String jankenMatch(@RequestParam String hand, ModelMap model, Principal prin) {
    String u1Name = prin.getName();
    String u2Name = "CPU";
    int u1Id = userMapper.selectByUserName(u1Name).getId();
    int u2Id = userMapper.selectByUserName(u2Name).getId();
    String u1Hand = hand;
    String u2Hand = "Gu";
    String result;

    Match match = new Match();
    match.setUser1(u1Id);
    match.setUser2(u2Id);
    match.setUser1Hand(u1Hand);
    match.setUser2Hand(u2Hand);
    matchMapper.insertMatches(match);

    if (u1Hand.equals("Gu")) {
      result = "Draw";
    } else if (u1Hand.equals("Choki")) {
      result = "You lose...";
    } else if (u1Hand.equals("Pa")) {
      result = "You win!";
    } else {
      result = "エラー";
    }
    model.addAttribute("login_user", u1Name);
    model.addAttribute("opponent", u2Name);
    model.addAttribute("u1Hand", u1Hand);
    model.addAttribute("u2Hand", u2Hand);
    model.addAttribute("result", result);

    return "match.html";
  }
}
