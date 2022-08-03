package sprites;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
/**
 * @author Dor Huri
 * this class impelement Sprite interface for the background
 * of level 2.
 */
public class BackgroundLevel2 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 800, 600);
        //drawing the background of the screen
        d.setColor(Color.white);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        int rays = 79;
        d.setColor(new Color(239, 230, 175));
        //drawing the sun rays of the background
        for (int i = 0; i < rays; i++) {
            d.drawLine(152, 152, i * 9, 260);
        }
        //drawing shapes of background 2
        d.fillCircle(151, 151, 62);
        d.setColor(new Color(237, 214, 72));
        d.fillCircle(151, 151, 51);
        d.setColor(new Color(254, 224, 25));
        d.fillCircle(151, 151, 40);
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
