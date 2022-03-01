package games.launchers;

import java.awt.Color;

import games.ballgames.bouncingballs.BouncingBalls;

public class BouncingBallsLauncher {

    public static void main(String[] args) {

        BouncingBalls ballSim = new BouncingBalls(15, 50, 30, Color.BLACK);
        ballSim.addBalls(5);
        ballSim.addBalls(5);

    }
}
