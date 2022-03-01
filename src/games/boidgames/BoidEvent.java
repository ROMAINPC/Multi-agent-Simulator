package games.boidgames;

import java.util.ArrayList;

import games.boidgames.boids.BoidView;
import games.boidgames.boids.FBoid;
import games.boidgames.boids.Neighborhood;
import games.events.Event;
import games.events.EventManager;

public class BoidEvent extends Event {

    private int step;
    private EventManager manager;
    private BoidView bV;
    private ArrayList<ArrayList<BoidView>> boids;
    private int width;
    private int height;

    /**
     * Constructeur
     * 
     * @param date    Date souhaitée d'exécution
     * @param step    Pas de temps entre 2 exécutions
     * @param manager
     * @param bV
     * @param boids
     */
    public BoidEvent(long date, int step, EventManager manager, BoidView bV, ArrayList<ArrayList<BoidView>> boids,
            int width, int height) {
        super(date);
        this.step = step;
        this.manager = manager;
        this.bV = bV;
        this.boids = boids;
        this.width = width;
        this.height = height;
    }

    @Override
    public void execute() {
        FBoid boid = (FBoid) bV.getBoid();
        // compute neighborhoods
        ArrayList<Neighborhood> neighborhoods = new ArrayList<Neighborhood>();
        for (ArrayList<BoidView> group : boids) { // for each neighborhoods
            Neighborhood neighborhood = new Neighborhood(boid);
            for (BoidView bV2 : group)
                if (bV != bV2 && boid.inView(bV2.getBoid()))
                    neighborhood.add(bV2.getBoid());
            if (neighborhood.size() > 0)
                neighborhoods.add(neighborhood);
        }

        // move force boids
        boid.force(neighborhoods);
        boid.move();

        // circular area
        /*
         * NB : boids in border of space will not count boids "on the other side" in
         * their neighborhoods.
         */
        if (boid.getX() >= width)
            boid.teleport(0, boid.getY());
        if (boid.getX() < 0)
            boid.teleport(width, boid.getY());
        if (boid.getY() >= height)
            boid.teleport(boid.getX(), 0);
        if (boid.getY() < 0)
            boid.teleport(boid.getX(), height);

        // PREPARE NEXT EVENT
        manager.addEvent(new BoidEvent(this.getDate() + step, step, manager, bV, boids, width, height));
    }
}
