package games.boidgames.boids;

import java.util.ArrayList;
import java.util.Random;

/**
 * Se balade mais parfois se réuni
 */
public class Human extends FBoid {

    private static final Random random = new Random();

    private static final int FOV = 360;
    private static final int DOV = 150;
    private static final int SPEED = 10;
    private static final int MOVE_FREQUENCY = 50;
    private static final int SOCIAL_FREQUENCY = 100;
    private static final int MEETING_DISTANCE = 50;

    private int nextRandom;
    private int socialNeed;

    /**
     * Constructeur d'humain en position (0,0)
     */
    public Human() {
        super(0, 0, FOV, DOV); // define fov and dov
        nextRandom = 0;
        socialNeed = SOCIAL_FREQUENCY;
    }

    @Override
    public void force(ArrayList<Neighborhood> neighborhoods) {

        // random move
        if (nextRandom <= 0) {
            this.setAngle(Math.toRadians(random.nextInt(360))); // new angle
            nextRandom = (int) (MOVE_FREQUENCY * random.nextDouble());
        } else
            nextRandom--;

        this.setSpeed(SPEED);

        // searching social interaction
        if (socialNeed <= 0)
            for (Neighborhood n : neighborhoods)
                if (n.getClosest().getGroup() == this.getGroup()) {
                    this.setAngle(Boid.angle(this, n.getXCOM(), n.getYCOM()));
                    if (Boid.distance(this, n.getXCOM(), n.getYCOM()) <= MEETING_DISTANCE)
                        this.setSpeed(0);

                    if (socialNeed < -SOCIAL_FREQUENCY * 3)
                        socialNeed = (int) (SOCIAL_FREQUENCY * random.nextDouble());
                }

        socialNeed--;

    }

}
