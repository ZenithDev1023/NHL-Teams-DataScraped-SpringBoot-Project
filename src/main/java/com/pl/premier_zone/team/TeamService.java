package com.pl.premier_zone.team;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return teamRepository.findAll().stream()
                .filter(team -> teamName.equals(team.getName()))
                .collect(Collectors.toList());
    }

    public List<Team> getTeamByNameSearch(String searchText) {
        return teamRepository.findAll().stream()
                .filter(team -> team.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Team> getTeamByYear(int year) {
        return teamRepository.findAll().stream()
                .filter(team -> team.getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Team> getTeamByNation(String searchText) {
        return teamRepository.findAll().stream()
                .filter(team -> team.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Team> getTeamByNameAndYear(String teamName, int year) {
        return teamRepository.findAll().stream()
                .filter(team -> teamName.equals(team.getName()) && team.getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Team> getTeamByNameAndWins(String teamName, int wins) {
        return teamRepository.findAll().stream()
                .filter(team -> teamName.equals(team.getName()) && team.getWins() == wins)
                .collect(Collectors.toList());
    }

    public List<Team> getTeamByNameAndLosses(String teamName, int losses) {
        return teamRepository.findAll().stream()
                .filter(team -> teamName.equals(team.getName()) && team.getLosses() == losses)
                .collect(Collectors.toList());
    }


    public Team addTeam(Team team) {
        teamRepository.save(team);
        return team;
    }

    public Team updateTeam(Team updatedTeam) {
        Optional<Team> existingTeam = teamRepository.findByName(updatedTeam.getName());

        if (existingTeam.isPresent()) {
            Team teamToUpdate = existingTeam.get();
            teamToUpdate.setName(updatedTeam.getName());
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
        teamRepository.deleteByName(teamName);
    }


}
