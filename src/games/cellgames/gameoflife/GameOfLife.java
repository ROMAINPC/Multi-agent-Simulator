package games.cellgames.gameoflife;

import java.util.Random;
import java.awt.Color;
import games.cellgames.CellGame;
import games.cellgames.Grid.Dir;

/**
 * Jeu de la vie de John Horton Conway.
 * 
 * La grille est circulaire.
 */
public class GameOfLife extends CellGame {

    private static final int BIRTH_MIN = 3; // spawn conditions
    private static final int BIRTH_MAX = 3;
    private static final int LIVE_MIN = 2; // death conditions
    private static final int LIVE_MAX = 3;
    private static final Color ALIVE_COLOR = Color.ORANGE;

    private static final Random random = new Random();

    private GLGrid grid;

    private int nbCells;

    /**
     * Création du jeu
     * 
     * @param cellsSize  Taille en pixels d'une cellule
     * @param columns    Nombre de cellules en largeur
     * @param rows       Nombre de cellules en hauteur
     * @param background Couleur du fond
     */
    public GameOfLife(int cellsSize, int columns, int rows, Color background) {
        super(cellsSize, columns, rows, background, new GLGrid(rows, columns, ALIVE_COLOR));
        this.grid = (GLGrid) super.grid;
        this.nbCells = 0;
    }

    /**
     * Ajoute des cellules et redémarre le jeu
     * 
     * @param number
     */
    public void addCells(int number) {
        nbCells += number;
        restart();
    }

    /**
     * Replace le nombre de cellules à des positions aléatoires
     * 
     * (Peuvent se chevaucher)
     */
    @Override
    protected void begin() {
        for (int i = 0; i < nbCells; i++)
            grid.spawn(random.nextInt(rows), random.nextInt(columns));
    }

    @Override
    protected void compute() {

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int count = 0;
                for (Dir dir : Dir.values()) // count alive neighbors
                    if (grid.isAlive(grid.nextRow(r, dir), grid.nextColumn(c, dir)))
                        count++;
                if (grid.isAlive(r, c)) { // died ?
                    if (count > LIVE_MAX || count < LIVE_MIN)
                        grid.kill(r, c);
                } else { // born ?
                    if (count >= BIRTH_MIN && count <= BIRTH_MAX)
                        grid.spawn(r, c);
                }
            }
        }

    }
}
