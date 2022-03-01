package games.ballgames.bouncingballs;

import gui.Oval;

import java.util.Random;

import games.Game;
import games.ballgames.Balls;

import java.awt.Color;

/**
 * Simulateur d'un ensemble de balles.
 * 
 * Les balles rebondissent sur les murs.
 * 
 */
public class BouncingBalls extends Game {

    static final Random random = new Random();

    private Balls balls;
    private int ballsSize;
    private int width;
    private int height;
    private int speed = 1; // fixed

    /**
     * Création du jeu
     * 
     * @param ballsSize  Taille en pixels d'une balle
     * @param width      Nombre de balles plaçables en largeur
     * @param height     ombre de balles plaçables en hauteur
     * @param background Couleur du fond
     */
    public BouncingBalls(int ballsSize, int width, int height, Color background) {
        super(ballsSize * width, ballsSize * height, background);
        this.balls = new Balls();
        this.ballsSize = ballsSize;
        this.width = width;
        this.height = height;
        refreshWindow();
    }

    /**
     * Ajoute des balles dans l'espace du simulateur à des positions aléatoires.
     * 
     * @param number
     */
    public void addBalls(int number) {
        for (int i = 0; i < number; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            balls.addBall(x, y, random.nextInt(2) == 0 ? speed : -speed, random.nextInt(2) == 0 ? speed : -speed);
        }
        refreshWindow();
    }

    /**
     * Calcule le déplacement des balles
     */
    @Override
    public void next() {

        // compute bounce
        for (int i = 0; i < balls.getNbBalls(); i++) {
            if (balls.getX(i) >= width - 1)
                balls.setHSpeed(i, -speed);
            if (balls.getY(i) >= height - 1)
                balls.setVSpeed(i, -speed);
            if (balls.getX(i) <= 0)
                balls.setHSpeed(i, speed);
            if (balls.getY(i) <= 0)
                balls.setVSpeed(i, speed);
        }

        // move balls
        balls.translate();

        // display
        refreshWindow();
    }

    /**
     * Réinitialise les balles à leurs positions de départ
     */
    @Override
    public void restart() {
        // reset positions
        balls.reInit();

        // display
        refreshWindow();
    }

    @Override
    protected void refreshWindow() {
        reset();
        int halfSize = ballsSize / 2;
        // Border
        displayBorder(Color.RED);

        for (int i = 0; i < balls.getNbBalls(); i++)
            addGraphicalElement(new Oval(balls.getX(i) * ballsSize + halfSize, balls.getY(i) * ballsSize + halfSize,
                    Color.BLUE, Color.BLUE, ballsSize));

    }

}
