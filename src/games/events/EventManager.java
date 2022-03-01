package games.events;

import java.util.PriorityQueue;

public class EventManager {

    private long currentDate; // last executed

    PriorityQueue<Event> queue = new PriorityQueue<Event>();

    /**
     * Constructeur
     */
    public EventManager() {
        currentDate = 0;
    }

    /**
     * Ajoute un évènement dans le manager. Si la date de l'évènement est antérieure
     * au présent il ne se passe rien.
     * 
     * @param e
     */
    public void addEvent(Event e) {
        if (e.getDate() <= currentDate)
            return;
        queue.add(e);
    }

    /**
     * Execute les prochains Event de même date
     */
    public void next() {
        if (isFinished())
            return;
        currentDate++;
        while (queue.peek().getDate() == currentDate)
            queue.remove().execute();
    }

    /**
     * 
     * @return Vrai si il n'y a plus d'évènements en attente
     */
    public boolean isFinished() {
        return queue.isEmpty();
    }

    /**
     * réinitialise le temps à 0 et vide le manager
     */
    public void restart() {
        currentDate = 0;
        queue.clear();
    }

}
