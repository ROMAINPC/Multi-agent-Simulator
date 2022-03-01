package games.cellgames;

import games.Game;

import java.awt.Color;
import gui.Rectangle;

/**
 * Classe abstraite jeu de cellules.
 */
public abstract class CellGame extends Game {

    private static final Color BORDER_COLOR = Color.RED;

    protected Grid grid;
    private int cellsSize;
    protected int columns;
    protected int rows;

    /**
     * Création du jeu
     * 
     * @param cellsSize  Taille en pixels d'une cellule
     * @param columns    Nombre de cellules en largeur
     * @param rows       Nombre de cellules en hauteur
     * @param background Couleur du fond
     * @param grid       Grille utilisée, doit être instanciée
     */
    protected CellGame(int cellsSize, int columns, int rows, Color background, Grid grid) {
        super(cellsSize * columns, cellsSize * rows, background);
        this.grid = grid;
        this.cellsSize = cellsSize;
        this.columns = columns;
        this.rows = rows;
        refreshWindow();

    }

    /**
     * 
     * @return La grille utilisée par le jeu
     */
    protected Grid getGrid() {
        return grid;
    }

    /**
     * Calcule les itérations du jeu
     */
    @Override
    public void next() {
        compute(); // compute
        grid.commit();// apply changes
        refreshWindow();// display
    }

    /**
     * Redémarre le jeu.
     */
    @Override
    public void restart() {
        grid.reset();// clear
        begin(); // set begin
        grid.commit();// apply changes
        refreshWindow();// display
    }

    /**
     * Méthode héritée, utiliser plutôt {@link #commit()}
     */
    @Override
    protected void refreshWindow() {
        reset();

        int halfSize = cellsSize / 2;
        // display occupied cells
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < columns; c++)
                addGraphicalElement(new Rectangle(c * cellsSize + halfSize, r * cellsSize + halfSize,
                        grid.getColor(r, c), grid.getColor(r, c), cellsSize));

        // Border
        displayBorder(BORDER_COLOR);
    }

    /**
     * Replace les cellules dans une configuration initiale.
     * 
     * La grille est déjà réinitialisée.
     */
    abstract protected void begin();

    /**
     * Calcul d'une itération de jeu.
     * 
     * Pas besoin de valider la grille.
     */
    abstract protected void compute();
}
