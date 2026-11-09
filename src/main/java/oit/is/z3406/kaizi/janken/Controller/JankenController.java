package oit.is.z3406.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import oit.is.z3406.kaizi.janken.model.MatchInfo;
import oit.is.z3406.kaizi.janken.model.MatchInfoMapper;

@Controller
public class JankenController {
  /*
   * @Autowired
   * private Entry room;
   */
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private MatchMapper matchMapper;
  @Autowired
  private MatchInfoMapper matchInfoMapper;

  @GetMapping("/janken")
  public String startJanken(ModelMap model, Principal prin) {
    String loginUser = prin.getName();
    // this.room.addUser(loginUser);
    model.addAttribute("login_user", loginUser);
    // model.addAttribute("room", this.room);

    // lec04
    ArrayList<User> Users = userMapper.selectAllUser();
    model.addAttribute("users", Users);
    ArrayList<Match> Matches = matchMapper.selectAllMatch();
    model.addAttribute("matches", Matches);

    // lec05
    ArrayList<MatchInfo> activeMatchInfoes = matchInfoMapper.selectActiveMatchInfo();
    model.addAttribute("aMInfoes", activeMatchInfoes);

    return "janken.html";
  }

  /*
   * @PostMapping("/janken")
   * public String startJankenWithGreeting(@RequestParam String name, ModelMap
   * model) {
   * String greeting = "Hi " + name;
   * model.addAttribute("greeting", greeting);
   * return "janken.html";
   * }
   */

  @GetMapping("/match")
  public String matching(@RequestParam Integer id, ModelMap model, Principal prin) {
    String loginUser = prin.getName();
    User user = userMapper.selectById(id);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("opponent", user.getUserName());
    model.addAttribute("opponentId", id);
    return "match.html";
  }

  @GetMapping("/fight/{id}")
  public String jankenMatch(@PathVariable Integer id, @RequestParam String hand, ModelMap model, Principal prin) {
    String u1Name = prin.getName();
    int u1Id = userMapper.selectByUserName(u1Name).getId();
    String u1Hand = hand;

    model.addAttribute("login_user", u1Name);

    ArrayList<MatchInfo> mI = matchInfoMapper.selectMatchingMatchInfo(u1Id);
    if (!mI.isEmpty()) {
      MatchInfo matchingMI = mI.get(0);
      matchingMI.setIsActive(false);

      Match matchingMatch = new Match();
      matchingMatch.setUser1(matchingMI.getUser1());
      matchingMatch.setUser2(u1Id);
      matchingMatch.setUser1Hand(matchingMI.getUser1Hand());
      matchingMatch.setUser2Hand(u1Hand);
      matchingMatch.setIsActive(true);

      matchMapper.insertMatches(matchingMatch);
      matchInfoMapper.updateById(matchingMI);

      model.addAttribute("matchResult", matchingMatch);
    } else {
      User u2 = userMapper.selectById(id);
      int u2Id = u2.getId();

      MatchInfo matchInfo = new MatchInfo();
      matchInfo.setUser1(u1Id);
      matchInfo.setUser2(u2Id);
      matchInfo.setUser1Hand(u1Hand);
      matchInfoMapper.insertMatchInfo(matchInfo);
    }

    return "wait.html";
  }
}
