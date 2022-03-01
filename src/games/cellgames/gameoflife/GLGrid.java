package games.cellgames.gameoflife;

import games.cellgames.Grid;

import java.awt.Color;

/**
 * Grille spécifique pour le jeu de la vie.
 */
public class GLGrid extends Grid {

    /**
     * Constructeur
     * 
     * @param rows
     * @param columns
     * @param alive   Couleur pour les cellules en vie, les autres seront
     *                transparentes.
     */
    public GLGrid(int rows, int columns, Color alive) {
        super(rows, columns, null, false);
        Color[] states = new Color[2];
        states[0] = new Color(0, 0, 0, 0);
        states[1] = alive;

        super.cellsFactory(states);
    }

    /**
     * Tue la cellule
     * 
     * @param row
     * @param column
     */
    public void kill(int row, int column) {
        setState(row, column, 0);
    }

    /**
     * Fait naître une cellule
     * 
     * @param row
     * @param column
     */
    public void spawn(int row, int column) {
        setState(row, column, 1);
    }

    /**
     * 
     * @param row
     * @param column
     * @return true si la cellule est vivante
     */
    public boolean isAlive(int row, int column) {
        return getState(row, column) == 1;
    }

}
