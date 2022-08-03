package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.HitListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Dor Huri
 * class represent block object which have a rectangle shape and color
 * and also implements intrafaceses Collidable, Sprite and  HitNotifier so when object
 * collid with the block he will removed from the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle; //rectangle shape of the block
    private final java.awt.Color color; //color of this block rectangle
    private final List<HitListener> hitListeners; //list of the block listeners

    /**
     * this method is the constructor method for class block.
     * @param rectangle rectangle shape of the block
     * @param color color of this block rectangle
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }
    /**
     * this method remove the block object from the game.
     * @param g ball game
     */
    public void removeFromGame(GameLevel g) {
        //removing the block object from the game
        g.removeCollidable(this);
        g.removeSprite(this);
    }
    /**
     * this method return the rectangle of this block.
     * @return the rectangle shape of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * this method return the velocity of the object after hitting the block depending
     * on where the object hit the rectangle block.
     * @param collisionPoint the collision point with the block
     * @param currentVelocity the current velocity of the object hitting the block
     * @param hitter the ball that hit the object
     * @return the velocity of the object after hitting the block
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //setting dx, dy with the velocity component
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        //setting the point variables with the rectangular vertices
        Point upperLeft = this.rectangle.getUpperLeft();
        Point upperRight = new Point(upperLeft.getX() + this.rectangle.getWidth(), upperLeft.getY());
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + this.rectangle.getHeight());
        Point bottomRight = new Point(upperLeft.getX() + this.rectangle.getWidth(),
                upperLeft.getY() + this.rectangle.getHeight());
        //setting the line variables with the rectangular edges
        Line line1 = new Line(upperLeft, upperRight);
        Line line2 = new Line(upperLeft, bottomLeft);
        Line line3 = new Line(bottomLeft, bottomRight);
        Line line4 = new Line(bottomRight, upperRight);
        //if the ball hit the horizontal edges of the rectangle
        if (line1.pointOnLine(collisionPoint) || line3.pointOnLine(collisionPoint)) {
            //set the vertical direction of the ball
            currentVelocity.setDy(-dy);
            //if the ball hit the vertical edges of the rectangle
        } else if (line2.pointOnLine(collisionPoint) || line4.pointOnLine(collisionPoint)) {
            //set the horizontal direction of the ball
            currentVelocity.setDx(-dx);
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }
    /**
     * this method is for drawing the block on the given DrawSurface.
     * @param surface1 able us to draw on the created screen
     */
    public void drawOn(DrawSurface surface1) {
        //checking if the block is the border block in that case we draw them without there outline
        if (this.rectangle.getWidth() == 800 || this.rectangle.getHeight() == 600) {
            //setting block color
            surface1.setColor(this.color);
            //drawing the rectangle shape and color on screen
            surface1.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(), (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
        } else {

            //setting block color
            surface1.setColor(Color.BLACK);
            //drawing the rectangle shape and color on screen
            surface1.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
            surface1.setColor(this.color);
            surface1.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        }
    }
    /**
     * this method is from interface sprite and will be implement in the future.
     */
    @Override
    public void timePassed() { }
    /**
     * this method add the block to the game and to the sprite and collidable lists.
     * @param g able us to add the block object to the sprite and collidable lists
     */
    public void addToGame(GameLevel g) {
        //adding object to the sprite and collidable lists
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * this method is for notifying all the listener
     * in the listener list that an hit accord.
     * @param hitter the ball hitting the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * this method add the block listener to the listener list.
     * @param hl able us to add the block listener to the listener list
     */
    @Override
    public void addHitListener(HitListener hl) {
        //add to listener list
        this.hitListeners.add(hl);
    }
    /**
     * this method remove the block listener from the listener list.
     * @param hl able us to remove the block listener from the listener list
     */
    @Override
    public void removeHitListener(HitListener hl) {
        //remove from listener list
        this.hitListeners.remove(hl);
    }
}
