package games.events;

/**
 * Évènement
 */
public abstract class Event implements Comparable<Event> {

    private long date;

    /**
     * Constructeur
     * 
     * @param date Date souhaitée d'exécution
     */
    public Event(long date) {
        this.date = date;
    }

    /**
     * 
     * @return Date souhaitée d'exécution
     */
    public long getDate() {
        return date;
    }

    /**
     * Tache à réaliser par l'évènement
     */
    public abstract void execute();

    @Override
    public int compareTo(Event other) {
        if (other.getDate() == getDate())
            return 0;
        return (int) (getDate() - other.getDate());
    }
}
