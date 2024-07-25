package com.example.demo;


import com.example.demo.model.League;
import com.example.demo.model.Team;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="DemoRestService", description="League's REST API")
public class DemoRestService {
  @Autowired
  private League league;

  @Operation(summary = "League's Teams", description = "List of teams in the league",
    tags = { "DemoRestService" })
  @GetMapping("/teams")
  public @ResponseBody ResponseEntity<Collection<Team>> getTeams() {
    Collection<Team> teams = league.teams.values();
    return ResponseEntity.ok(teams);
  }


  @Operation(summary = "Team", description = "Team information",
    tags = { "DemoRestService" })
  @GetMapping("/teams/{id}")
  public @ResponseBody ResponseEntity<Team> getTeam(
      @Parameter(description = "Team's id", required = true, example = "1", in = ParameterIn.PATH)
      @PathVariable(value = "id") String id) {
    Team team = league.teams.get(id);
    if (team == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(team);
  }

  @Operation(summary = "updateTeam", description = "Update team information",
    tags = { "DemoRestService" })
  @PutMapping("/teams/{id}")
  public @ResponseBody ResponseEntity<Team> updateTeam(
      @Parameter(description = "Team's id", required = true, example = "1", in = ParameterIn.PATH)
      @PathVariable(value = "id") String id,
      @RequestBody Team team) {
    if (league.teams.get(id) == null) {
      return ResponseEntity.notFound().build();
    }
    Team someTeam = league.teams.put(id, team);
    return ResponseEntity.ok(someTeam);
  }

  @Operation(summary = "addPlayerToTeam", description = "Add a player to a team",
    tags = { "DemoRestService" })
  @PutMapping("/teams/{id}/{playerId}")
  public @ResponseBody ResponseEntity addPlayerToTeam(
      @Parameter(description = "Team's id", required = true, example = "1", in = ParameterIn.PATH)
      @PathVariable(value = "id") String id,
      @Parameter(description = "Player's id", required = true, example = "1", in = ParameterIn.PATH)
      @PathVariable(value = "playerId") String playerId) {
    Team team = league.teams.get(id);
    if (team == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
    }
    if (league.players.get(playerId) == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found");
    }
    if (team.getPlayers() != null && team.getPlayers().containsKey(playerId)) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Player already in team");
    }
    team.addPlayer(league.players.get(playerId));
    return ResponseEntity.ok(league.teams.get(id));
  }

  @Operation(summary = "addTeam", description = "Add a team to the league",
    tags = { "DemoRestService" })
  @PostMapping("/teams")
  public @ResponseBody ResponseEntity addTeam(
      @Parameter(description = "Team ID", required = true, example = "1", in = ParameterIn.QUERY)
          @RequestParam(name = "id") String id,
      @Parameter(description = "Team name", required = true, example = "Team 1", in = ParameterIn.QUERY)
          @RequestParam(name = "name") String name) {
    if (league.teams.get(id) != null) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Team already exists");
    }
    Team newTeam = new Team(id, name);
    league.teams.put(id, newTeam);
    return ResponseEntity.ok(league.teams.get(id));
  }

  @Operation(summary = "deleteTeam", description = "Delete a team from the league",
    tags = { "DemoRestService" })
  @DeleteMapping("/teams/{id}")
  public @ResponseBody ResponseEntity<Team> deleteTeam(
      @Parameter(description = "team's id", required = true, example = "1", in = ParameterIn.PATH)
      @PathVariable(value = "id") String id) {
    Team deletedTeam = league.teams.remove(id);
    if (deletedTeam == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(deletedTeam);
  }


}
