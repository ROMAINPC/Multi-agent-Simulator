package games.cellgames;

import java.awt.Color;

/**
 * Grille 2D de cellules pouvant avoir un état associé à une couleur.
 * 
 */
public class Grid {

	private int rows;
	private int columns;
	private Cell[][] cells;
	private int[][] nextStates;

	/**
	 * Même constructeur que le constructeur principal mais permet de ne pas
	 * instancier les cellules, à utiliser dans les classes filles.
	 * 
	 * @param rows
	 * @param columns
	 * @param states
	 * @param createCells
	 */
	protected Grid(int rows, int columns, Color[] states, boolean createCells) {
		this.cells = new Cell[rows][columns];
		this.nextStates = new int[rows][columns];
		this.rows = rows;
		this.columns = columns;

		if (createCells)
			cellsFactory(states);
	}

	/**
	 * Crée une grille.
	 * 
	 * @param rows
	 * @param columns
	 * @param states  Tableau des états possibles pour les cellules.
	 */
	public Grid(int rows, int columns, Color[] states) {
		this(rows, columns, states, true);
	}

	/**
	 * A utiliser dans les classes filles pour instancier les cellules si ce n'est
	 * pas fait à la construction.
	 * 
	 * @param states
	 */
	protected void cellsFactory(Color[] states) {
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < columns; c++) {
				this.cells[r][c] = new Cell(states);
				this.nextStates[r][c] = 0;
			}
	}

	/**
	 * 
	 * @return Nombre de lignes
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * 
	 * @return Nombre de colonnes
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * 
	 * @param row
	 * @param column
	 * @return La couleur de la cellule aux coordonnées spécifiées
	 */
	public Color getColor(int row, int column) {
		return cells[row][column].getColor();
	}

	/**
	 * 
	 * @param row
	 * @param column
	 * @return L'index d'état de la cellule aux coordonnées spécifiées
	 */
	public int getState(int row, int column) {
		return cells[row][column].getState();
	}

	/**
	 * Définit l'état de la cellule aux coordonnées spécifiées.
	 * 
	 * Sera appliqué au prochain appel de {@link #commit()}
	 * 
	 * @param row
	 * @param column
	 * @param state
	 */
	public void setState(int row, int column, int state) {
		nextStates[row][column] = state;
	}

	/**
	 * Applique tous les changements d'états
	 */
	public void commit() {
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < columns; c++)
				cells[r][c].setState(nextStates[r][c]);
	}

	/**
	 * Réinitialise toutes les cellules à l'état 0.
	 */
	public void reset() {
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < columns; c++)
				this.nextStates[r][c] = 0;
		commit();
	}

	/**
	 * 
	 * @param row
	 * @param direction
	 * @return la ligne de la cellule voisine en suivant la direction indiquée
	 */
	public int nextRow(int row, Dir direction) {
		row += direction.dy;
		row = row < 0 ? rows - 1 : row;
		return row % rows;
	}

	/**
	 * 
	 * @param column
	 * @param direction
	 * @return la colonne de la cellule voisine en suivant la direction indiquée
	 */
	public int nextColumn(int column, Dir direction) {
		column += direction.dx;
		column = column < 0 ? columns - 1 : column;
		return column % columns;
	}

	/**
	 * Affiche les indexes des états courants sur la sortie standard.
	 */
	public void printStates() {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++)
				System.out.print(cells[r][c].getState() + " ");
			System.out.println();
		}
	}

	@Override
	public String toString() {
		return "Grid " + getRows() + " x " + getColumns();
	}

	/**
	 * Points cardinaux.
	 * 
	 * Y down
	 */
	public enum Dir {
		N(0, -1), S(0, 1), E(1, 0), W(-1, 0), NW(-1, -1), NE(1, -1), SW(-1, 1), SE(1, 1);

		private int dx;
		private int dy;

		private Dir(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}
	}
}
