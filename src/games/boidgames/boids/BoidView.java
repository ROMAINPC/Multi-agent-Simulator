package games.boidgames.boids;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.GraphicalElement;

/**
 * Composant graphique associé à un Boid.
 * 
 * Les boids sont des triangles équilatéraux.
 */
public class BoidView implements GraphicalElement {

    private Boid boid;
    private Color color;
    private int radius;

    /**
     * Constructeur
     * 
     * @param boid   Boid instancié
     * @param color  Couleur du corps
     * @param radius Taille des boids
     */
    public BoidView(Boid boid, Color color, int radius) {
        this.boid = boid;
        this.color = color;
        this.radius = radius;
    }

    /**
     * 
     * @return Boid associé au composant
     */
    public Boid getBoid() {
        return boid;
    }

    /**
     * Dessin du composant
     * 
     * @param arg0 Panneau graphique (java awt)
     */
    @Override
    public void paint(Graphics2D arg0) {

        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        // head
        xPoints[0] = (int) (boid.getX() + radius * Math.cos(boid.getAngle()));
        yPoints[0] = (int) (boid.getY() + radius * Math.sin(boid.getAngle()));
        // back left
        xPoints[1] = (int) (boid.getX() + radius * Math.cos(boid.getAngle() + Math.toRadians(-120)));
        yPoints[1] = (int) (boid.getY() + radius * Math.sin(boid.getAngle() + Math.toRadians(-120)));
        // back right
        xPoints[2] = (int) (boid.getX() + radius * Math.cos(boid.getAngle() + Math.toRadians(120)));
        yPoints[2] = (int) (boid.getY() + radius * Math.sin(boid.getAngle() + Math.toRadians(120)));

        arg0.setColor(color);
        arg0.fillPolygon(xPoints, yPoints, 3);
        arg0.setColor(Color.BLACK);
        arg0.drawLine(xPoints[0], yPoints[0], (xPoints[1] + xPoints[2]) / 2, (yPoints[1] + yPoints[2]) / 2);

    }

}
