package animation;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Dor Huri
 * this class impelement animation interface for pausing the screen every
 * time we press the stopping key.
 */
public class PauseScreen implements Animation {
    private final boolean stop = false; // boolean for the stopping condition
    /**
     * This method is called for doing one frame of the game each time.
     * @param d the draw surface that apply to the screen the action we did
     */
    public void doOneFrame(DrawSurface d) {
        //setting the background and text of the pause screen
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.WHITE);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    /**
     * This method is for checking if the stop condition of the animation accord.
     * @return if the animation should stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}