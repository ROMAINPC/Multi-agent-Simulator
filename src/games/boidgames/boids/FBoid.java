package games.boidgames.boids;

import java.util.ArrayList;

/**
 * Un boid force est une classe abstraite.
 * 
 * Il permet la création de boid soumis à une force paramétrable.
 * 
 * Les boid force ont un champ de vision et une distance de vision
 */
public abstract class FBoid extends Boid {

    // Field of View
    private double fov; // radians

    // Distance vision
    private double distance;

    /**
     * Création d'un boid force immobile orienté vers le haut
     * 
     * @param x
     * @param y
     * @param fov      Champ de vision en degrés
     * @param distance Distance de vision en px
     */
    public FBoid(double x, double y, double fov, double distance) {
        this(x, y, 0, fov, distance);
    }

    /**
     * Création d'un boid immobile
     * 
     * @param x
     * @param y
     * @param angle
     * @param fov      Champ de vision en degrés
     * @param distance Distance de vision en px
     */
    public FBoid(double x, double y, double angle, double fov, double distance) {
        this(x, y, 0, angle, fov, distance);
    }

    /**
     * Création d'un boid
     * 
     * @param x
     * @param y
     * @param speed
     * @param angle    En radians dans le sens trigonométrique
     * @param fov      Champ de vision en degrés
     * @param distance Distance de vision en px
     */
    public FBoid(double x, double y, double speed, double angle, double fov, double distance) {
        super(x, y, speed, angle);
        this.fov = Math.toRadians(fov);
        this.distance = distance;
    }

    /**
     * 
     * @return Champ de vision en radians.
     */
    public double getFOV() {
        return fov;
    }

    /**
     * 
     * @return Distance de vision en pixels.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * 
     * @param boid
     * @return Vrai si le boid passé en paramètre est dans le champ de vision et à
     *         bonne distance du boid actuel.
     */
    public boolean inView(Boid boid) {
        // test distance
        if (Boid.distance(this, boid) > this.getDistance())
            return false;

        // test FOV
        if (Math.abs(Boid.angle(this, boid)) > (getFOV() / 2.0))
            return false;

        return true;
    }

    /**
     * Calcule le prochain changement de déplacement du boid.
     * 
     * Doit mettre à jour sa vitesse et son orientation.
     * 
     * @param neighborhoods Voisinages de boids du même type. Permet d'adapter le
     *                      comportement en fonction du type de voisins.
     */
    abstract public void force(ArrayList<Neighborhood> neighborhoods);

}
