package games.launchers;

import java.awt.Color;
import games.cellgames.gameoflife.GameOfLife;

public class GameOfLifeLauncher {
    public static void main(String[] args) {
        GameOfLife conwaySim = new GameOfLife(15, 50, 30, Color.BLACK);
        conwaySim.addCells(200);

    }
}
