package games.launchers;

import java.awt.Color;

import games.boidgames.FBoidGame;
import games.boidgames.boids.Human;

public class HumansLauncher {
    public static void main(String[] args) {
        FBoidGame game = new FBoidGame(1200, 700, Color.BLACK);
        game.addBoids(8, 15, 1, Human.class);
        game.addBoids(8, 15, 1, Human.class);
    }
}
