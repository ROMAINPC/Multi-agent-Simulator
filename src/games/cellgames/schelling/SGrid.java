package games.cellgames.schelling;

import java.awt.Color;
import java.util.Random;

import games.cellgames.Grid;

/**
 * Grille spécifique pour le modèle de Schelling
 */
public class SGrid extends Grid {

    private static Random random = new Random();

    /**
     * Constructeur
     * 
     * @param rows
     * @param columns
     * @param nbFamilies Nombre de couleurs de familles
     */
    public SGrid(int rows, int columns, int nbFamilies) {
        super(rows, columns, null, false);

        // compute colors
        Color[] states = new Color[nbFamilies + 1]; // state 0 is for empty cells
        states[0] = new Color(0, 0, 0, 0);
        for (int i = 1; i <= nbFamilies; i++) // random hue
            states[i] = new Color(Color.HSBtoRGB(random.nextFloat(), 1.0f, 1.0f));

        super.cellsFactory(states);
    }

    /**
     * Fait déménager une famille
     * 
     * @param row
     * @param column
     */
    public void relocate(int row, int column, int destRow, int destColumn) {
        setState(destRow, destColumn, getState(row, column));
        setState(row, column, 0);
    }

    /**
     * 
     * @param row
     * @param column
     * @param numFamily
     */
    public void moveIn(int row, int column, int numFamily) {
        setState(row, column, numFamily);
        return;
    }

    /**
     * 
     * @param row
     * @param column
     * @param rowOther
     * @param columnOther
     * @return Vrai si la cellule est occupée par une autre famille
     */
    public boolean isStranger(int row, int column, int rowOther, int columnOther) {
        if (isEmpty(rowOther, columnOther))
            return false;
        return getState(row, column) != getState(rowOther, columnOther);
    }

    /**
     * 
     * @param row
     * @param column
     * @return Vrai si la cellule n'est pas habitée
     */
    public boolean isEmpty(int row, int column) {
        return getState(row, column) == 0;
    }
}
