package com.pl.premier_zone.team;

import com.sun.net.httpserver.HttpsServer;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/team")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getTeams(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int year,
            @RequestParam(required = false) int wins,
            @RequestParam(required = false) int losses) {
        if (name != null && year >= 1990) {
            return teamService.getTeamByNameAndYear(name, year);
        }
        else if (name != null) {
            return teamService.getTeamsByName(name);
        }
        else if (name != null && wins >= 0) {
            return teamService.getTeamByNameAndWins(name, wins);
        }
        else if (name != null && losses >= 0) {
            return teamService.getTeamByNameAndLosses(name, losses);
        }
        else {
            return teamService.getTeams();
        }
    }


    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        Team createTeam = teamService.addTeam(team);
        return new ResponseEntity<>(createTeam, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Team> updatePlayer(@RequestBody Team team) {
        Team updateTeam = teamService.updateTeam(team);
        if (updateTeam != null) {
            return new ResponseEntity<>(updateTeam, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/(teamName)")
    public ResponseEntity<String> deletePlayer(@PathVariable String teamName) {
        teamService.deleteTeam(teamName);
        return new ResponseEntity<>("Player deleted successfully!", HttpStatus.OK);
    }
}
