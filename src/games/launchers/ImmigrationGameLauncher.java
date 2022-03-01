package games.launchers;

import java.awt.Color;

import games.cellgames.immigration.ImmigrationGame;

public class ImmigrationGameLauncher {
    public static void main(String[] args) {
        new ImmigrationGame(15, 30, 30, 4, Color.BLACK);
    }
}
