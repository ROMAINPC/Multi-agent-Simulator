package games.boidgames.boids;

import java.util.ArrayList;

/**
 * Essaye de suivre ses compères.
 * 
 * Vols en formation circulaire.
 * 
 * Evitent les humains.
 */
public class Bird extends FBoid {

    private static final int FOV = 300;
    private static final int DOV = 50;

    private static final int GROUP_APPROACH = 35;
    private static final int COLLISION_APPROACH = 20;
    private static final double COLLISION_ANGLE = Math.toRadians(20);

    private static final double DRAFT = Math.toRadians(1.5);
    private static final double BRAKE_FACTOR = 0.5;
    private static final double ACC_FACTOR = 2;

    private static final int GENERAL_SPEED = 10;

    private static final int HUMAN_COLLISION = DOV;

    /**
     * Constructeur d'oiseau en position (0,0)
     */
    public Bird() {
        super(0, 0, FOV, DOV); // define fov and dov
    }

    @Override
    public void force(ArrayList<Neighborhood> neighborhoods) {
        this.setSpeed(GENERAL_SPEED);
        for (Neighborhood n : neighborhoods) {
            if (n.getClosest() instanceof Human && Boid.distance(this, n.getClosest()) < HUMAN_COLLISION) {
                this.setSpeed(GENERAL_SPEED * ACC_FACTOR);
                this.setAngle(Boid.angle(this, n.getXCOM(), n.getYCOM()) + Math.PI);
                break;
            } else if (n.getClosest() instanceof Bird) { // only follows its pairs
                if (Boid.distance(this, n.getClosest()) < COLLISION_APPROACH) {// avoiding collision
                    this.setAngle(
                            n.getClosest().getAngle() + COLLISION_ANGLE * Math.signum(angle(this, n.getClosest())));
                } else if (Boid.distance(this, n.getClosest()) > GROUP_APPROACH) { // joining others
                    this.setAngle(Boid.angle(this, n.getXCOM(), n.getYCOM()));
                } else { // align
                    this.setAngle(n.getAngle() + DRAFT);
                    this.setSpeed(GENERAL_SPEED * BRAKE_FACTOR);
                }

            }
        }
    }

}
