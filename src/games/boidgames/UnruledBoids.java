package games.boidgames;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import games.Game;
import games.boidgames.boids.Boid;
import games.boidgames.boids.BoidView;

/**
 * Simple simulateur de boids sans règles.
 */
public class UnruledBoids extends Game {

    private ArrayList<BoidView> boids;

    private static final Random random = new Random();

    private static final int RADIUS = 10;
    private static final int MOVE_FREQUENCY = 5;
    private static final int SPEED_RANGE_MIN = 5;
    private static final int SPEED_RANGE_MAX = 10;

    private int width;
    private int height;

    /**
     * Constructeur
     * 
     * @param nbBoids    nombre de boids
     * @param width      Largeur de la fenêtre
     * @param height     Hauteur de la fenêtre
     * @param background Couleur du fond
     */
    public UnruledBoids(int nbBoids, int width, int height, Color background) {
        super(width, height, background);
        boids = new ArrayList<BoidView>();
        this.width = width;
        this.height = height;

        // make boids
        for (int i = 0; i < nbBoids; i++)
            boids.add(new BoidView(new Boid(width / 2, height / 2),
                    new Color(Color.HSBtoRGB(random.nextFloat(), 1.0f, 1.0f)), RADIUS));

        // initial move
        for (BoidView bV : boids)
            setRandomMove(bV.getBoid());

        refreshWindow();
    }

    /**
     * Déplace les boids de manière imprévisible
     */
    @Override
    public void next() {

        for (BoidView bV : boids) {
            if (random.nextInt(MOVE_FREQUENCY) == 0) // is changing move ?
                setRandomMove(bV.getBoid());
            bV.getBoid().move();
        }

        refreshWindow();
    }

    private void setRandomMove(Boid boid) {
        boid.setAngle(Math.toRadians(random.nextInt(360))); // new angle
        boid.setSpeed(SPEED_RANGE_MIN + random.nextInt(SPEED_RANGE_MAX));// new Speed
    }

    /**
     * Replace les boids au centre de la fenêtre
     */
    @Override
    public void restart() {
        for (BoidView bV : boids)
            bV.getBoid().teleport(width / 2, height / 2);

        refreshWindow();
    }

    @Override
    protected void refreshWindow() {
        reset();

        for (BoidView bV : boids)
            addGraphicalElement(bV);
    }

}
