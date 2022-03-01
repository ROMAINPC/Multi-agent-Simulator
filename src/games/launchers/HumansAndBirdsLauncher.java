package games.launchers;

import java.awt.Color;

import games.boidgames.FBoidGame;
import games.boidgames.boids.Bird;
import games.boidgames.boids.Human;

public class HumansAndBirdsLauncher {
    public static void main(String[] args) {
        FBoidGame game = new FBoidGame(1200, 700, Color.BLACK);
        game.addBoids(3, 15, 2, Human.class);
        game.addBoids(60, 10, 1, Bird.class);
    }
}
