package com.pl.premier_zone.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    void deleteByName(String teamName);

    Optional<Team> findByName(String name);
}
