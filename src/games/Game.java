package games;

import gui.GUISimulator;
import gui.Simulable;
import gui.GraphicalElement;
import java.awt.Color;

import gui.Rectangle;

/**
 * Classe générique de jeu. Faites hériter pour créer des simulations.
 */
public abstract class Game implements Simulable {

    private GUISimulator gui;

    private final static Color transparency = new Color(0, 0, 0, 0);

    /**
     * Crée un nouveau jeu.
     * 
     * @param width   Largeur de la fenêtre
     * @param height  Hauteur de la fenêtre
     * @param bgColor Couleur de fond de la fenêtre
     */
    protected Game(int width, int height, Color bgColor) {
        gui = new GUISimulator(width, height, bgColor);
        gui.setSimulable(this);
    }

    /**
     * Peint un composant graphique
     * 
     * @param component
     */
    protected void addGraphicalElement(GraphicalElement component) {
        gui.addGraphicalElement(component);
    }

    /**
     * Réinitialise la fenêtre avec sa couleur de fond
     */
    protected void reset() {
        gui.reset();
    }

    /**
     * Peint la bordure de la fenêtre.
     * 
     * @param color
     */
    protected void displayBorder(Color color) {
        gui.addGraphicalElement(new Rectangle(gui.getPanelWidth() / 2, gui.getPanelHeight() / 2, color, transparency,
                gui.getPanelWidth(), gui.getPanelHeight()));
    }

    /**
     * Repeint les composants graphiques.
     */
    abstract protected void refreshWindow();

}
