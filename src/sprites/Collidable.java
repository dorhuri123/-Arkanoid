package sprites;
import geometry.Rectangle;
import geometry.Velocity;
import geometry.Point;
import biuoop.DrawSurface;
/**
 * @author Dor Huri
 * this interface is for collidable object
 * of the arachnoid game.
 */
public interface Collidable {
    /**
     * this interface method is for getting the
     * the collision shape of the object.
     * @return the collision shape of the object
     */
    Rectangle getCollisionRectangle();
    /**
     * this interface method return the velocity of the object after hitting the collidable.
     * @param collisionPoint the collision point for the collidable with the object
     * @param currentVelocity the current velocity of the object hitting the collidable
     * @param hitter the ball that hit the object
     * @return the collision shape of the object
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
    /**
     * this interface method is for drawing the collidable.
     * @param d the DrawSurface object that able us to draw to the screen
     */
    void drawOn(DrawSurface d);
}