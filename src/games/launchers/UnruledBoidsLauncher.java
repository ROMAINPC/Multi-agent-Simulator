package games.launchers;

import java.awt.Color;

import games.boidgames.UnruledBoids;

public class UnruledBoidsLauncher {
    public static void main(String[] args) {
        new UnruledBoids(15, 700, 600, Color.BLACK);
    }
}
