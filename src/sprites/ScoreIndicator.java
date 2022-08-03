package sprites;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import java.awt.Color;
/**
 * @author Dor Huri
 * class represent ScoreIndicator object which apper
 * on the gui screen and update the score accordanly.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score; //the score counter
    private final Point location = new Point(360, 16); //score location on the screen
    private final int fontSize = 16; //size of font of the score
    /**
     * this method is the constructor method for class ScoreIndicator.
     * @param score counter
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /**
     * this method is for drawing the ScoreIndicator on the given DrawSurface.
     * @param d able us to draw on the created screen
     */
    public void drawOn(DrawSurface d) {
        //creating the score string
        String str = "Score: " + score.getValue();
        //setting text color and drawing it on the screen
        d.setColor(Color.BLACK);
        d.drawText((int) location.getX(), (int) location.getY(), str, fontSize);
    }
    /**
     * this method add the ball object to the game.
     * @param g game object
     */
    public void addToGame(GameLevel g) {
        //adding object to the sprite list
        g.addSprite(this);
    }
    @Override
    public void timePassed() {
    }
}
