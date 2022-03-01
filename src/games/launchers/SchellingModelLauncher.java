package games.launchers;

import java.awt.Color;

import games.cellgames.schelling.SchellingModel;

public class SchellingModelLauncher {
    public static void main(String[] args) {
        new SchellingModel(15, 60, 30, 4, Color.BLACK);
    }
}
