package sprites;
import biuoop.DrawSurface;
import game.GameLevel;

/**
 * @author Dor Huri
 * this interface is for sprite object
 * of the arachnoid game.
 */
public interface Sprite {
    /**
     * this interface method is for drawing the sprite to the screen.
     * @param d the DrawSurface object that able us to draw to the screen
     */
    void drawOn(DrawSurface d);
    /**
     * this interface method is for notify the sprite that time has passed.
     */
    void timePassed();
    /**
     * this method add the sprite object to the game level.
     * @param g the game level
     */
    void addToGame(GameLevel g);
}