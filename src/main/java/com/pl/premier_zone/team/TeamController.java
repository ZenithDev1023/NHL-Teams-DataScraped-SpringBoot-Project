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
            @RequestParam(required = false) String teamName,
            @RequestParam(required = false) int year,
            @RequestParam(required = false) int wins,
            @RequestParam(required = false) int losses) {
        if (teamName != null && year >= 1990) {
            return teamService.getTeamByNameAndYear(teamName, year);
        }
        else if (teamName != null) {
            return teamService.getTeamsByName(teamName);
        }
        else if (teamName != null && wins >= 0) {
            return teamService.getTeamByNameAndWins(teamName, wins);
        }
        else if (teamName != null && losses >= 0) {
            return teamService.getTeamByNameAndLosses(teamName, losses);
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
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        Team updateTeam = teamService.updateTeam(team);
        if (updateTeam != null) {
            return new ResponseEntity<>(updateTeam, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{teamName}")
    public ResponseEntity<String> deleteTeam(@PathVariable String teamName) {
        teamService.deleteTeam(teamName);
        return new ResponseEntity<>("Team deleted successfully!", HttpStatus.OK);
    }
}
