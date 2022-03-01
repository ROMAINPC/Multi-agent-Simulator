package games.cellgames.immigration;

import games.cellgames.CellGame;
import java.awt.Color;
import java.util.Random;
import games.cellgames.Grid.Dir;

/**
 * Jeu de l'immigration
 */
public class ImmigrationGame extends CellGame {

    private static final int THRESHOLD = 3;
    private static final Color BASE_COLOR = Color.BLUE;

    private static final Random random = new Random();
    private IGGrid grid;

    private int nbStates;

    /**
     * Création du jeu
     * 
     * @param cellsSize
     * @param columns    Nombre de cellules en largeur
     * @param rows       Nombre de cellules en hauteur
     * @param nbStates   Nombre d'états
     * @param background Couleur du fond
     */
    public ImmigrationGame(int cellsSize, int columns, int rows, int nbStates, Color background) {
        super(cellsSize, columns, rows, background, new IGGrid(rows, columns, nbStates, BASE_COLOR));
        this.grid = (IGGrid) super.grid;
        this.nbStates = nbStates;
        restart();
    }

    /**
     * Randomize states on top left quarter of game space
     */
    @Override
    protected void begin() {
        for (int r = rows / 4; r < rows / 4 * 3; r++) {
            for (int c = columns / 4; c < columns / 4 * 3; c++) {
                grid.setState(r, c, random.nextInt(nbStates));
            }
        }
    }

    @Override
    protected void compute() {

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int count = 0;
                for (Dir dir : Dir.values()) // count neighbors that are in the state k+1, where k is the state of the
                                             // current cell
                    if (grid.getState(grid.nextRow(r, dir), grid.nextColumn(c, dir)) == grid.getState(r, c) + 1)
                        count++;
                if (count >= THRESHOLD)
                    grid.increment(r, c);
            }
        }
    }

}
