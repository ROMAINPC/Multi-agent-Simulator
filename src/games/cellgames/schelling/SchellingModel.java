package games.cellgames.schelling;

import games.cellgames.CellGame;
import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import games.cellgames.Grid.Dir;

import java.awt.Point;

/**
 * Simulateur du modèle de Schelling
 */
public class SchellingModel extends CellGame {

    private static final int K = 3; // acceptable limit of stranger neighbors
    private static final float OCUPANCY_RATE = 0.99999f;

    private int nbFamilies;

    private LinkedList<Point> emptyHouses;

    private SGrid grid;

    private static final Random random = new Random();

    /**
     * Création du jeu
     * 
     * @param cellsSize   Taille en pixels d'une cellule
     * @param columns     Nombre de cellules en largeur
     * @param rows        Nombre de cellules en hauteur
     * @param nbFamilies  nombre de familles différentes i.e nombre de couleurs
     *                    différentes
     * @param emptyHouses Collection de Points, va manager la réattribution des
     *                    maisons. Doit être vide.
     * @param background  Couleur du fond
     */
    public SchellingModel(int cellsSize, int columns, int rows, int nbFamilies, Color background) {
        super(cellsSize, columns, rows, background, new SGrid(rows, columns, nbFamilies));
        this.grid = (SGrid) super.grid;
        this.nbFamilies = nbFamilies;
        this.emptyHouses = new LinkedList<Point>();
        restart();
    }

    /**
     * Fait emménager les familles à des places aléatoires.
     * 
     * Peuvent se chevaucher.
     */
    @Override
    protected void begin() {
        int gridSize = grid.getColumns() * grid.getRows();
        int population = (int) (gridSize * OCUPANCY_RATE);
        int familySize = (population / nbFamilies);
        for (int family = 1; family <= nbFamilies; family++) {
            for (int i = 0; i < familySize; i++) {
                grid.moveIn(random.nextInt(rows), random.nextInt(columns), family);
            }
        }

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < columns; c++)
                if (grid.isEmpty(r, c))
                    emptyHouses.add(new Point(r, c));
        Collections.shuffle(emptyHouses);
    }

    /**
     * Fait déménager les familles trop entourées par des étrangers.
     * 
     * La place vacante sera réattribuée à la prochaine famille déménageant.
     */
    @Override
    protected void compute() {
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < columns; c++)
                if (!grid.isEmpty(r, c)) {
                    int count = 0;
                    for (Dir dir : Dir.values())
                        if (grid.isStranger(r, c, grid.nextRow(r, dir), grid.nextColumn(c, dir)))
                            count++;
                    if (count > K) {
                        Point dest = emptyHouses.removeFirst(); // LIFO

                        grid.relocate(r, c, dest.x, dest.y);
                        emptyHouses.addFirst(new Point(r, c));
                    }
                }
    }
}
