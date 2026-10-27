package oit.is.z3406.kaizi.janken.model;

public class User {
  int id;
  String userName;

  public User() {

  }

  public User(int id2, String name) {
    this.id = id2;
    this.userName = name;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String name) {
    this.userName = name;
  }
}
