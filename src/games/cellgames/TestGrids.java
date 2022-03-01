package games.cellgames;

import java.awt.Color;

import games.cellgames.Grid.Dir;
import games.cellgames.gameoflife.GLGrid;
import games.cellgames.immigration.IGGrid;

public class TestGrids {
    public static void main(String[] args) {

        System.out.println("Tests sur les cellules :");

        Color[] states = { Color.RED, Color.YELLOW, Color.GREEN };

        Cell cell1 = new Cell(states);
        System.out.println(cell1);
        Cell cell2 = new Cell(states);
        cell2.setState(1);
        System.out.println(cell2);
        Cell cell3 = new Cell(states);
        cell3.setState(2);
        System.out.println(cell3);

        System.out.println("Tests sur une grille :");
        Grid grid1 = new Grid(3, 5, states);
        System.out.println(grid1);
        grid1.printStates();
        System.out.println();
        grid1.setState(1, 3, 2);
        grid1.printStates();
        System.out.println();
        grid1.commit();
        grid1.printStates();
        System.out.println();
        grid1.reset();
        grid1.printStates();

        System.out.println("Tests sur une grille pour le jeu de la vie:");
        GLGrid grid2 = new GLGrid(3, 3, Color.RED);
        System.out.println(grid2.getColor(0, 0));
        System.out.println(grid2.isAlive(1, 1));
        grid2.spawn(1, 0);
        grid2.printStates();
        grid2.commit();
        System.out.println();
        grid2.printStates();
        System.out.println(grid2.getColor(1, 0));

        System.out.println("Tests sur une grille pour le jeu de la vie:");
        IGGrid grid3 = new IGGrid(3, 3, 4, Color.ORANGE);
        grid3.printStates();
        grid3.increment(0, 0);
        grid3.increment(0, 1);
        grid3.increment(0, 2);
        grid3.increment(1, 0);
        grid3.increment(1, 1);
        grid3.commit();
        grid3.increment(0, 1);
        grid3.increment(0, 2);
        grid3.increment(1, 0);
        grid3.increment(1, 1);
        grid3.commit();
        grid3.increment(0, 2);
        grid3.increment(1, 0);
        grid3.increment(1, 1);
        grid3.commit();
        grid3.increment(1, 0);
        grid3.increment(1, 1);
        grid3.commit();
        grid3.increment(1, 1);
        grid3.commit();
        System.out.println();
        grid3.printStates();

        System.out.println("Tests utilitaires de direction :");
        System.out.println(grid3.nextColumn(0, Dir.N));
        System.out.println(grid3.nextColumn(0, Dir.E));
        System.out.println(grid3.nextColumn(0, Dir.W));
        System.out.println(grid3.nextRow(2, Dir.S));
        System.out.println(grid3.nextRow(2, Dir.NW));
    }
}
