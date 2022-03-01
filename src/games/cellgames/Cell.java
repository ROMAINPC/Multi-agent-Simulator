package games.cellgames;

import java.awt.Color;

/**
 * Une simple cellule. Peut être dans différents états et avoir une couleur pour
 * chacun.
 * 
 */
public class Cell {

	private int currentState;
	private Color[] states;

	/**
	 * Construit une cellule.
	 * 
	 * Elle sera à l'état 0.
	 * 
	 * @param states Fixe sa liste d'états possibles
	 */
	public Cell(Color[] states) {
		if (states == null || states.length == 0)
			throw new IllegalArgumentException("Please give an array with at least one color.");
		this.states = states;
		this.currentState = 0;
	}

	public void setState(int i) {
		if (i < 0 || i >= states.length)
			throw new IllegalArgumentException("Wrong index.");
		currentState = i;
	}

	/**
	 * 
	 * @return L'index de l'état courant
	 */
	public int getState() {
		return currentState;
	}

	/**
	 * 
	 * @return La couleur de l'état courant
	 */
	public Color getColor() {
		return states[currentState];
	}

	@Override
	public String toString() {
		return "Cellule état " + getState() + " "
				+ String.format("#%02X%02X%02X", getColor().getRed(), getColor().getGreen(), getColor().getBlue());
	}

}
