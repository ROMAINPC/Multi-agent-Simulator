package games.cellgames.immigration;

import java.awt.Color;

import games.cellgames.Grid;

/**
 * Grille spécifique pour le jeu de l'immigration
 */
public class IGGrid extends Grid {

    private int nbStates;

    /**
     * Constructeur
     * 
     * @param rows
     * @param columns
     * @param nbStates  Nombre de couleurs
     * @param baseColor Couleur de base du dégradé
     */
    public IGGrid(int rows, int columns, int nbStates, Color baseColor) {
        super(rows, columns, null, false);
        this.nbStates = nbStates;

        // compute colors
        // Set brightness to 100% and modulate Saturation
        Color[] states = new Color[nbStates];
        float[] hsbVals = new float[3];
        Color.RGBtoHSB(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), hsbVals);
        for (int i = 0; i < nbStates - 1; i++)
            states[i] = new Color(Color.HSBtoRGB(hsbVals[0], i * (1.0f / (nbStates - 1)), 1.0f));
        states[nbStates - 1] = new Color(Color.HSBtoRGB(hsbVals[0], 1.0f, 1.0f));

        super.cellsFactory(states);
    }

    /**
     * Change d'état la cellule
     * 
     * @param row
     * @param column
     */
    public void increment(int row, int column) {
        setState(row, column, (getState(row, column) + 1) % nbStates);
    }
}
