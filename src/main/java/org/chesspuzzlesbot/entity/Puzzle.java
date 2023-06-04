package org.chesspuzzlesbot.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "puzzles")
public class Puzzle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "timesSolved")
    private Integer timesSolved;

    @Basic
    @Column(name = "timesPlayed")
    private Integer timesPlayed;

    @Basic
    @Column(name = "solution", length = 20)
    private String solution;

    @Basic
    @Column(name = "lichessId", length = 5)
    private String lichessId;

    public Puzzle() {

    }

    public Puzzle(Integer timesSolved, Integer timesPlayed, String solution, String lichessId) {
        this.timesSolved = timesSolved;
        this.timesPlayed = timesPlayed;
        this.solution = solution;
        this.lichessId = lichessId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Integer getTimesSolved() {
        return timesSolved;
    }

    public void setTimesSolved(Integer timesSolved) {
        this.timesSolved = timesSolved;
    }


    public Integer getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(Integer timesPlayed) {
        this.timesPlayed = timesPlayed;
    }


    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }


    public String getLichessId() {
        return lichessId;
    }

    public void setLichessId(String lichessId) {
        this.lichessId = lichessId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puzzle puzzle = (Puzzle) o;
        return id == puzzle.id && Objects.equals(timesSolved, puzzle.timesSolved) && Objects.equals(timesPlayed, puzzle.timesPlayed) && Objects.equals(solution, puzzle.solution) && Objects.equals(lichessId, puzzle.lichessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timesSolved, timesPlayed, solution, lichessId);
    }
}
