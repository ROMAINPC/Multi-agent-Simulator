package games.boidgames;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import games.Game;
import games.boidgames.boids.BoidView;
import games.boidgames.boids.FBoid;
import games.events.EventManager;

/**
 * Simulateur de boids.
 * 
 * Ajoutez des boids, le jeu simulera leurs déplacements.
 * 
 * L'espace est limité et circulaire. La réinitialisation du jeu dispersera les
 * boids.
 */
public class FBoidGame extends Game {

    private static final Random random = new Random();

    private static final Color BORDER_COLOR = Color.DARK_GRAY;

    private ArrayList<ArrayList<BoidView>> boids;
    private EventManager manager;

    private int width;
    private int height;

    private int ID;

    /**
     * Constructeur
     * 
     * @param width      Largeur de la fenêtre
     * @param height     Hauteur de la fenêtre
     * @param background Couleur du fond
     */
    public FBoidGame(int width, int height, Color background) {
        super(width, height, background);
        boids = new ArrayList<ArrayList<BoidView>>();
        this.width = width;
        this.height = height;
        this.manager = new EventManager();
    }

    /**
     * Ajoute des boids au jeu
     * 
     * @param quantity nombre de boids
     * @param raidus   taille des boids
     * @param step     pas de temps d'exécution
     * @param cls      type des boids
     */
    public void addBoids(int quantity, int radius, int step, Class<? extends FBoid> cls) {

        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive.");

        Color groupColor = new Color(Color.HSBtoRGB(random.nextFloat(), 1.0f, 1.0f));

        try {
            // create list
            ArrayList<BoidView> list = new ArrayList<BoidView>();
            for (int i = 0; i < quantity; i++) {
                BoidView bV = new BoidView(cls.getDeclaredConstructor().newInstance(), groupColor, radius);
                list.add(bV);
                bV.getBoid().setGroup(ID);
                manager.addEvent(new BoidEvent(1, step, manager, bV, boids, width, height));
            }

            ID++;
            boids.add(list); // add to boids global list
        } catch (Exception e) {
            e.printStackTrace();
        }
        restart();
    }

    /**
     * Simulation des force boids.
     */
    @Override
    public void next() {

        manager.next();

        refreshWindow();
    }

    /**
     * Disperse les boids dans l'espace.
     */
    @Override
    public void restart() {
        for (ArrayList<BoidView> list : boids)
            for (BoidView bV : list)
                bV.getBoid().teleport(random.nextInt(width), random.nextInt(height));

        refreshWindow();
    }

    /**
     * 
     * @return Le nombre total de boids en jeu.
     */
    public int getTotalBoids() {
        return boids.size();
    }

    @Override
    protected void refreshWindow() {
        reset();

        for (ArrayList<BoidView> list : boids)
            for (BoidView bV : list)
                addGraphicalElement(bV);

        // Border
        displayBorder(BORDER_COLOR);
    }

}
