package oit.is.z3406.kaizi.janken.model;

public class Match {
  int id;
  int user1;
  int user2;
  String user1Hand;
  String user2Hand;

  public Match() {

  }

  public Match(int id, int user1, int user2, String u1Hand, String u2Hand) {
    this.id = id;
    this.user1 = user1;
    this.user2 = user2;
    this.user1Hand = u1Hand;
    this.user2Hand = u2Hand;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUser1() {
    return this.user1;
  }

  public void setUser1(int user1) {
    this.user1 = user1;
  }

  public int getUser2() {
    return this.user2;
  }

  public void setUser2(int user2) {
    this.user2 = user2;
  }

  public String getUser1Hand() {
    return this.user1Hand;
  }

  public void setUser1Hand(String u1Hand) {
    this.user1Hand = u1Hand;
  }

  public String getUser2Hand() {
    return this.user2Hand;
  }

  public void setUser2Hand(String u2Hand) {
    this.user2Hand = u2Hand;
  }
}
