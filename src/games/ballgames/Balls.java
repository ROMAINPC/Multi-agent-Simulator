package games.ballgames;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Ensemble de quelques balles déplaçables et réinitialisables.
 * 
 * Chaque balle est un point de coordonnées entières.
 */
public class Balls {
	private int nbBalls;
	private ArrayList<Point> initCoordinates;

	private ArrayList<Point> currentCoordinates;
	private ArrayList<Point> currentSpeeds;

	/**
	 * Construction de l'ensemble de balles avec des coordonnées initiales définies
	 * 
	 * @param initCoordinates vont être passées par valeur
	 */
	public Balls(int[][] initCoordinates) {
		this();
		addBalls(initCoordinates);
	}

	/**
	 * Construction d'un ensemble de balles vides
	 */
	public Balls() {
		this.currentCoordinates = new ArrayList<Point>();
		this.initCoordinates = new ArrayList<Point>();
		this.currentSpeeds = new ArrayList<Point>(); // default to 0
		this.nbBalls = 0;
	}

	/**
	 * Translate toutes les balles
	 * 
	 * @param dx
	 * @param dy
	 */
	public void translate(int dx, int dy) {
		for (Point p : currentCoordinates)
			p.translate(dx, dy);
	}

	/**
	 * Déplace toutes les balles suivant leur vitesse. Voir
	 * {@link #setSpeed(int index, int dx, int dy)}
	 */
	public void translate() {
		for (int i = 0; i < this.getNbBalls(); i++)
			currentCoordinates.get(i).translate(currentSpeeds.get(i).x, currentSpeeds.get(i).y);
	}

	/**
	 * Remet toutes les balles à leurs positions initiales
	 */
	public void reInit() {
		for (int i = 0; i < this.getNbBalls(); i++)
			currentCoordinates.get(i).setLocation(initCoordinates.get(i));
	}

	/**
	 * 
	 * @return nombre de balles présentes
	 */
	public int getNbBalls() {
		return nbBalls;
	}

	/**
	 * Retire la balle désignée par son numéro (index)
	 * 
	 * @param index
	 */
	public void removeBall(int index) {
		initCoordinates.remove(index);
		currentCoordinates.remove(index);
		currentSpeeds.remove(index);
		this.nbBalls--;
	}

	/**
	 * Ajoute une série de balles
	 * 
	 * @param initCoordinates
	 */
	public void addBalls(int[][] initCoordinates) {
		for (int[] p : initCoordinates)
			addBall(p[0], p[1]);
	}

	/**
	 * Ajoute une balle aux coordonnées spécifiées
	 * 
	 * @param x
	 * @param y
	 */
	public void addBall(int x, int y) {
		this.addBall(x, y, 0, 0);
	}

	/**
	 * Ajoute une balle aux coordonnées spécifiées, permet de préciser sa vitesse
	 * initiale.
	 * 
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 */
	public void addBall(int x, int y, int dx, int dy) {
		initCoordinates.add(new Point(x, y));
		currentCoordinates.add(new Point(x, y));
		currentSpeeds.add(new Point(dx, dy));
		this.nbBalls++;
	}

	/**
	 * @param index Index d'une balle
	 * @return Coordonnée X
	 */
	public int getX(int index) {
		return currentCoordinates.get(index).x;
	}

	/**
	 * @param index Index d'une balle
	 * @return Coordonnée Y
	 */
	public int getY(int index) {
		return currentCoordinates.get(index).y;
	}

	/**
	 * Définie la vitesse horizontale d'une balle.
	 * 
	 * @param index
	 * @param dx
	 */
	public void setHSpeed(int index, int dx) {
		currentSpeeds.get(index).x = dx;
	}

	/**
	 * Définie la vitesse verticale d'une balle.
	 * 
	 * @param index
	 * @param dy
	 */
	public void setVSpeed(int index, int dy) {
		currentSpeeds.get(index).y = dy;
	}

	@Override
	public String toString() {
		String s = new String("{ ");
		for (Point p : currentCoordinates)
			s += "(" + p.x + ";" + p.y + ") ";
		return s + "}";
	}

}