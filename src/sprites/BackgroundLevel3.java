package sprites;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
/**
 * @author Dor Huri
 * this class impelement Sprite interface for the background
 * of level 3.
 */
public class BackgroundLevel3 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 800, 600);
        //drawing the background of the screen
        d.setColor(new Color(43, 131, 21));
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        //drawing shapes of background 3
        d.setColor(new Color(62, 58, 58));
        d.fillRectangle(100, 400, 30, 50);
        d.setColor(new Color(47, 43, 40));
        d.fillRectangle(60, 450,  105, 150);
        d.setColor(new Color(79, 75, 73));
        d.fillRectangle(109, 199, 11, 201);
        d.setColor(new Color(215, 173, 103));
        d.fillCircle(114, 199, 13);
        d.setColor(new Color(244, 77, 55));
        d.fillCircle(114, 199, 9);
        d.setColor(Color.white);
        d.fillCircle(114, 199, 4);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                //drawing white rectangle on "building"
                d.fillRectangle(70 + (j * 18), 460 + (i * 32), 13, 25);
            }
        }
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
