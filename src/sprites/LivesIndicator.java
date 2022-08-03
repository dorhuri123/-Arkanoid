package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;

import java.awt.Color;
/**
 * @author Dor Huri
 * class represent LivesIndicator object which apper
 * on the gui screen and update the lives accordanly.
 */
public class LivesIndicator implements Sprite {
    private final Counter lives; //the live counter
    private final Point location = new Point(120, 16); //LivesIndicator location on the screen
    private final int fontSize = 16; //size of font of the LivesIndicator
    /**
     * this method is the constructor method for class LivesIndicator.
     * @param lives counter
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }
    /**
     * this interface method is for drawing the sprite to the screen.
     * @param d the DrawSurface object that able us to draw to the screen
     */
    @Override
    public void drawOn(DrawSurface d) {
        //setting the text of the LivesIndicator
        d.setColor(Color.BLACK);
        d.drawText((int) location.getX(), (int) location.getY(), "Lives: " + this.lives.getValue(), fontSize);
    }
    /**
     * this method add the LivesIndicator object to the game level.
     * @param g the game level
     */
    public void addToGame(GameLevel g) {
        //adding object to the sprite list
        g.addSprite(this);
    }
    /**
     * this interface method is for notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
