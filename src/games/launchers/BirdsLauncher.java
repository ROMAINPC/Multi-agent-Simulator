package games.launchers;

import java.awt.Color;

import games.boidgames.FBoidGame;
import games.boidgames.boids.Bird;

public class BirdsLauncher {
    public static void main(String[] args) {
        FBoidGame game = new FBoidGame(1100, 700, Color.BLACK);
        game.addBoids(90, 10, 1, Bird.class);
    }
}
