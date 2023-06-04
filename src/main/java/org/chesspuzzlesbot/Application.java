package org.chesspuzzlesbot;

import org.chesspuzzlesbot.dao.PuzzleDao;
import org.chesspuzzlesbot.entity.Puzzle;

public class Application {
    public static void main(String[] args) {

        PuzzleDao puzzleDao = new PuzzleDao();

        Puzzle puzzle1 = new Puzzle(0, 0, "e5", "oMO2U");

        puzzleDao.savePuzzle(puzzle1);
    }
}




