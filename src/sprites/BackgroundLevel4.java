package sprites;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
/**
 * @author Dor Huri
 * this class impelement Sprite interface for the background
 * of level 4.
 */
public class BackgroundLevel4 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 800, 600);
        //drawing the background of the screen
        d.setColor(new Color(23, 136, 210));
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.white);
        //drawing the line on the left side
        for (int i = 0; i < 10; i++) {
            d.drawLine(90 + (i * 10), 401, 65 + (i * 10), 605);
        }
        //drawing clouds of the left side
        d.setColor(new Color(205, 205, 205));
        d.fillCircle(90, 395, 24);
        d.fillCircle(110, 415, 29);
        d.setColor(new Color(188, 188, 188));
        d.fillCircle(120, 385, 30);
        d.setColor(new Color(169, 169, 169));
        d.fillCircle(150, 415, 24);
        d.fillCircle(170, 395, 33);
        d.setColor(Color.white);
        //drawing the line on the right side
        for (int i = 0; i < 10; i++) {
            d.drawLine(590 + (i * 10), 521, 570 + (i * 10), 605);
        }
        //drawing clouds of the right side
        d.setColor(new Color(205, 205, 205));
        d.fillCircle(590, 495, 24);
        d.fillCircle(610, 515, 28);
        d.setColor(new Color(188, 188, 188));
        d.fillCircle(630, 505, 30);
        d.setColor(new Color(169, 169, 169));
        d.fillCircle(650, 525, 24);
        d.fillCircle(670, 515, 33);
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
