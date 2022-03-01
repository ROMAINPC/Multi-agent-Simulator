package games.boidgames.boids;

import java.util.ArrayList;

/**
 * Ensemble de force boids voisins d'un boid particulier.
 * 
 * Fourni des informations sur le groupe.
 * 
 * @see games.boidgames.boids.FBoid
 */
public class Neighborhood {

    private Boid boid;

    private ArrayList<Boid> boids = new ArrayList<Boid>(); // ArrayList because access is faster.

    // Center of mass
    private double xCOM;
    private double yCOM;

    // Closest boid
    private Boid closest;
    private Boid farthest;

    // average angle
    private double angleX;
    private double angleY;

    /**
     * Constructeur
     * 
     * @param boid le boid étudié
     */
    public Neighborhood(Boid boid) {
        this.boid = boid;
        this.xCOM = 0;
        this.yCOM = 0;
        this.angleX = 0;
        this.angleY = 0;
    }

    /**
     * Ajoute un boid dans le voisinage
     * 
     * @param boid
     */
    public void add(Boid boid) {

        // adding
        boids.add(boid);

        // compute COM
        xCOM += boid.getX() / boids.size();
        yCOM += boid.getY() / boids.size();

        // compute closest
        if (closest == null)
            closest = boid;
        else
            closest = Boid.distance(boid, this.boid) < Boid.distance(closest, this.boid) ? boid : closest;

        // compute farthest
        if (farthest == null)
            farthest = boid;
        else
            farthest = Boid.distance(boid, this.boid) > Boid.distance(farthest, this.boid) ? boid : farthest;

        // compute angle
        angleX += Math.cos(boid.getAngle());
        angleY += Math.sin(boid.getAngle());

    }

    /**
     * 
     * @return coordonnée X du centre de masse
     */
    public double getXCOM() {
        return xCOM;
    }

    /**
     * 
     * @return coordonnée Y du centre de masse
     */
    public double getYCOM() {
        return yCOM;
    }

    /**
     * Peut être null
     * 
     * @return Le boid le plus proche du boid étudié dans le voisinage
     */
    public Boid getClosest() {
        return closest;
    }

    /**
     * Peut être null
     * 
     * @return Le boid le plus loin du boid étudié dans le voisinage
     */
    public Boid getFarthest() {
        return farthest;
    }

    /**
     * 
     * @return L'angle moyen du voisinage
     */
    public double getAngle() {
        return Math.atan2(angleY / size(), angleX / size());
    }

    /**
     * 
     * @return Nombre de boids dans le voisinage
     */
    public int size() {
        return boids.size();
    }

}
