package sprites;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
/**
 * @author Dor Huri
 * this class impelement Sprite interface for the background
 * of level 1.
 */
public class BackgroundLevel1 implements Sprite {
    private static final int CENTER_X = 400;
    private static final int CENTER_Y = 200;
    private static final int RADIUS = 80;
    private static final int SIZE_CHANGE = 30;
    @Override
    public void drawOn(DrawSurface d) {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 800, 600);
        //drawing the background of the screen
        d.setColor(Color.BLACK);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.BLUE);
        //drawing shapes of background 1
        d.drawCircle(CENTER_X, CENTER_Y, RADIUS);
        d.drawCircle(CENTER_X, CENTER_Y, RADIUS + SIZE_CHANGE);
        d.drawCircle(CENTER_X, CENTER_Y, RADIUS + SIZE_CHANGE * 2);
        d.drawLine(CENTER_X, 50, CENTER_X, 350);
        d.drawLine(220, 195, 585, 195);
    }
    @Override
    public void addToGame(GameLevel g) {
        //adding object to the sprite and collidable lists
        g.addSprite(this);
    }

    @Override
    public void timePassed() {
    }
}
