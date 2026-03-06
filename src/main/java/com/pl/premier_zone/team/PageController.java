package com.pl.premier_zone.team;

import com.pl.premier_zone.team.Team;
import com.pl.premier_zone.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class PageController {

    private final TeamService teamService;

    @Autowired
    public PageController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/")
    public String home() {
        return "teams";
    }

    @GetMapping("/teams")
    public String teamsPage(Model model) {
        List<Team> teams = teamService.getTeams();
        model.addAttribute("teams", teams);
        return "teams";
    }
}

