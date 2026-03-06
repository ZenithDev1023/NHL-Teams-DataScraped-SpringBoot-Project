package com.pl.premier_zone.team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="player_statistics")
public class Team {
    // Fields
    @Id
    @Column(name="team_name")
    private String teamName;

    private int year;

    private int wins;

    private int losses;

    private float pct;

    private int gf;

    private int ga;

    private int diff;

    // Default Constructor
    public Team() {}

    // Parameterized Constructor
    public Team(String teamName, int year, int wins, int losses, float pct, int gf, int ga, int diff) {
        this.teamName = teamName;
        this.year = year;
        this.wins = wins;
        this.losses = losses;
        this.pct = pct;
        this.gf = gf;
        this.ga = ga;
        this.diff = diff;
    }

    // Getters
    public String getTeamName() { return teamName; }
    public int getYear() { return year; }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public float getPct() { return pct; }
    public int getGf() { return gf; }
    public int getGa() { return ga; }
    public int getDiff() { return diff; }


    // Setters
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public void setYear(int year) { this.year = year; }
    public void setWins(int wins) { this.wins = wins; }
    public void setLosses(int losses) { this.losses = losses; }
    public void setPct(float pct) { this.pct = pct; }
    public void setGf(int gf) { this.gf = gf; }
    public void setGa(int ga) { this.ga = ga; }
    public void setDiff(int diff) { this.diff = diff; }
}
