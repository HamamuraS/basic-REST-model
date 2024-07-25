package com.example.demo.model;

import java.util.HashMap;
import java.util.Map;

public class Team {

  private String id;
  private String name;
  private Map<String, Player> players;

  public Team(String id, String name, Map<String, Player> players) {
    this.id = id;
    this.name = name;
    this.players = players;
  }

  public Team (String id, String name) {
    this.id = id;
    this.name = name;
    this.players = new HashMap<>();
  }

  public Team() {
    this.players = new HashMap<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map<String, Player> getPlayers() {
    return players;
  }

  public void setPlayers(Map<String, Player> players) {
    this.players = players;
  }

  public void addPlayer(Player player) {
    if (this.players == null) {
      this.players = new HashMap<>();
    }
    players.put(player.getId(), player);
  }

}
