package com.example.demo.model;

import java.util.HashMap;
import java.util.Map;

public class League {

  public Map<String, Team> teams = new HashMap<>();
  public Map<String, Player> players = new HashMap<>();

  public League() {
    Player pepe = new Player("1", "Pepe", "Perez");
    players.put(pepe.getId(), pepe);
    Player juan = new Player("2", "Juan", "Lopez");
    players.put(juan.getId(), juan);
    Player luis = new Player("3", "Luis", "Gomez");
    players.put(luis.getId(), luis);
    Player maria = new Player("4", "Maria", "Garcia");
    players.put(maria.getId(), maria);
    Player ana = new Player("5", "Ana", "Martinez");
    players.put(ana.getId(), ana);

    Map<String, Player> players1 = new HashMap<>();
    players1.put(pepe.getId(), pepe);
    players1.put(juan.getId(), juan);
    Team team1 = new Team("1", "Team 1", players1);
    teams.put(team1.getId(), team1);

    Map<String, Player> players2 = new HashMap<>();
    players2.put(luis.getId(), luis);
    players2.put(maria.getId(), maria);
    Team team2 = new Team("2", "Team 2", players2);
    teams.put(team2.getId(), team2);
  }

  public Map<String, Team> getTeams() {
    return teams;
  }

  public void setTeams(Map<String, Team> teams) {
    this.teams = teams;
  }

  public Map<String, Player> getPlayers() {
    return players;
  }

  public void setPlayers(Map<String, Player> players) {
    this.players = players;
  }


}
