package com.pl.premier_zone.team;

import com.pl.premier_zone.team.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    void deleteByTeamName(String teamName);

    Optional<Team> findByTeamName(String teamName);
    List<Team> findByTeamNameAndYear(String teamName, int year);
    List<Team> findByTeamNameAndWins(String teamName, int wins);
    List<Team> findByTeamNameAndLosses(String teamName, int losses);
}
