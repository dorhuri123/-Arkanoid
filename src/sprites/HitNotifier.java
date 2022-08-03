package sprites;
import listeners.HitListener;
/**
 * @author Dor Huri
 * this interface is for HitNotifier whice update the listener list.
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.
    /**
     * this method is for adding a listener to listener list.
     * @param hl the HitListener we add
     */
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.
    /**
     * this method is for removing a listener from listener list.
     * @param hl the HitListener we remove
     */
    void removeHitListener(HitListener hl);
}

