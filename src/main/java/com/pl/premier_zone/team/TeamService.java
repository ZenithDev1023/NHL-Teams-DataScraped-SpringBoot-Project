package com.pl.premier_zone.team;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collections;

@Component
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public List<Team> getTeamsByName(String teamName) {
        return teamRepository.findByTeamName(teamName)
            .map(Collections::singletonList)
            .orElse(Collections.emptyList());
    }

    public List<Team> getTeamByNameAndYear(String teamName, int year) {
        return teamRepository.findByTeamNameAndYear(teamName, year);
    }

    public List<Team> getTeamByNameAndWins(String teamName, int wins) {
        return teamRepository.findByTeamNameAndWins(teamName, wins);
    }

    public List<Team> getTeamByNameAndLosses(String teamName, int losses) {
        return teamRepository.findByTeamNameAndLosses(teamName, losses);
    }





    public List<Team> getTeamByNameSearch(String searchText) {
        return teamRepository.findAll().stream()
                .filter(team -> team.getTeamName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Team> getTeamByYear(int year) {
        return teamRepository.findAll().stream()
                .filter(team -> team.getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Team> getTeamByNation(String searchText) {
        return teamRepository.findAll().stream()
                .filter(team -> team.getTeamName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }


    public Team addTeam(Team team) {
        teamRepository.save(team);
        return team;
    }

    public Team updateTeam(Team updatedTeam) {
        Optional<Team> existingTeam = teamRepository.findByTeamName(updatedTeam.getTeamName());

        if (existingTeam.isPresent()) {
            Team teamToUpdate = existingTeam.get();
            teamToUpdate.setTeamName(updatedTeam.getTeamName());
            teamToUpdate.setYear(updatedTeam.getYear());
            teamToUpdate.setWins(updatedTeam.getWins());
            teamToUpdate.setLosses(updatedTeam.getLosses());
            teamToUpdate.setPct(updatedTeam.getPct());
            teamToUpdate.setGf(updatedTeam.getGf());
            teamToUpdate.setGa(updatedTeam.getGa());
            teamToUpdate.setDiff(updatedTeam.getDiff());

            teamRepository.save(teamToUpdate);
            return teamToUpdate;
        }
        return null;
    }

    @Transactional
    public void deleteTeam(String teamName) {
        teamRepository.deleteByTeamName(teamName);
    }


}
