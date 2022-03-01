package games.boidgames.boids;

/**
 * Classe représentant un boid pouvant bouger et s'orienter dans un espace 2D
 */
public class Boid {

    // Position
    private double x;
    private double y;
    // Speed
    private double speed;

    // Orientation
    private double angle;

    // Group ID
    private int group;

    /**
     * Crée un boid en (0,0)
     */
    public Boid() {
        this(0, 0);
    }

    /**
     * Création d'un boid immobile orienté vers le haut
     * 
     * @param x
     * @param y
     */
    public Boid(double x, double y) {
        this(x, y, 0);
    }

    /**
     * Création d'un boid immobile
     * 
     * @param x
     * @param y
     * @param angle
     */
    public Boid(double x, double y, double angle) {
        this(x, y, 0, angle);
    }

    /**
     * Création d'un boid
     * 
     * @param x
     * @param y
     * @param speed
     * @param angle En radians dans le sens trigonométrique
     */
    public Boid(double x, double y, double speed, double angle) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = angle;
        this.group = 0;// default group;
    }

    /**
     * 
     * @return Position X du boid
     */
    public double getX() {
        return x;
    }

    /**
     * 
     * @return Position Y du boid (Y down)
     */
    public double getY() {
        return y;
    }

    /**
     * Bouge instantanément le boid à une position donnée
     * 
     * @param x
     * @param y
     */
    public void teleport(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Translate la position du boid
     * 
     * @param dX Décalage horizontal
     * @param dY Décalage vertical
     */
    public void translate(double dX, double dY) {
        this.x += dX;
        this.y += dY;
    }

    /**
     * Déplace le boid suivant sa vitesse et son orientation
     */
    public void move() {
        translate(speed * Math.cos(angle), speed * Math.sin(angle));
    }

    /**
     * 
     * @return Vitesse absolue du boid
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Définie a vitesse absolue du boid
     * 
     * @param speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return l'orientation du boid
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Définie l'orientation du boid.
     * 
     * angle 0 vers la droite. Signe horaire. (Y down)
     * 
     * @param angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Défini le groupe du boid
     * 
     * @param ID
     */
    public void setGroup(int ID) {
        this.group = ID;
    }

    /**
     * 
     * @return Le groupe du boid
     */
    public int getGroup() {
        return group;
    }

    /*
     * 
     * 
     * COMPUTE TOOLS
     * 
     * 
     */

    /**
     * 
     * @param b1
     * @param b2
     * @return La distance entre les deux boids.
     */
    public static double distance(Boid b1, Boid b2) {
        return Math.sqrt(
                (b2.getY() - b1.getY()) * (b2.getY() - b1.getY()) + (b2.getX() - b1.getX()) * (b2.getX() - b1.getX()));
    }

    /**
     * 
     * @param b
     * @param x
     * @param y
     * @return La distance entre le boid et un point donné.
     */
    public static double distance(Boid b, double x, double y) {
        return Math.sqrt((y - b.getY()) * (y - b.getY()) + (x - b.getX()) * (x - b.getX()));
    }

    /**
     * 
     * @param b1
     * @param b2
     * @return L'angle en radians du deuxième boid par rapport au premier. Sur
     *         -pi/pi en Y down.
     */
    public static double angle(Boid b1, Boid b2) {
        return Math.atan2(b2.getY() - b1.getY(), b2.getX() - b1.getX());
    }

    /**
     * 
     * @param b
     * @param x
     * @param y
     * @return L'angle en radians du point par rapport au boid passé en paramètre.
     *         Sur -pi/pi en Y down.
     */
    public static double angle(Boid b, double x, double y) {
        return Math.atan2(y - b.getY(), x - b.getX());
    }

}
