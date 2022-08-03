package animation;
import biuoop.DrawSurface;
/**
 * @author Dor Huri
 * this interface is for the animation played
 * in the arachnoid game.
 */
public interface Animation {
    /**
     * This method is called for doing one frame of the game each time.
     * @param d the draw surface that apply to the screen the action we did
     */
    void doOneFrame(DrawSurface d);
    /**
     * This method is for checking if the stop condition of the animation accord.
     * @return if the animation should stop
     */
    boolean shouldStop();
}