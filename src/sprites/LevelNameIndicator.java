package sprites;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import java.awt.Color;
/**
 * @author Dor Huri
 * class represent LevelNameIndicator object which apper
 * on the gui screen accordanly to the level name.
 */
public class LevelNameIndicator implements Sprite {
    private final String levelName; //the Level Name
    private final Point location = new Point(530, 16); //LevelNameIndicator location on the screen
    private final int fontSize = 16; //size of font of the LevelNameIndicator
    /**
     * this method is the constructor method for class LevelNameIndicator.
     * @param levelName the Level Name
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }
    /**
     * this interface method is for drawing the sprite to the screen.
     * @param d the DrawSurface object that able us to draw to the screen
     */
    @Override
    public void drawOn(DrawSurface d) {
        //setting the text of the LevelNameIndicator
        d.setColor(Color.BLACK);
        d.drawText((int) location.getX(), (int) location.getY(), "Level Name: " + this.levelName, fontSize);
    }
    /**
     * this interface method is for notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
    /**
     * this method add the LivesIndicator object to the game level.
     * @param g the game level
     */
    public void addToGame(GameLevel g) {
        //adding object to the sprite list
        g.addSprite(this);
    }
}
