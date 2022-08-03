package sprites;
import geometry.Point;

/**
 * @author Dor Huri
 * this class is for CollisionInfo meaning the object of this
 * clss contain the collidable object and the collision point.
 */
public class CollisionInfo {
    private final Point p; //the collision point
    private final Collidable c; //the collidable object
    /**
     * this method is the constructor method for class CollisionInfo.
     * @param p the collision point
     * @param c the collidable object
     */
    public CollisionInfo(Point p, Collidable c) {
        this.p = p;
        this.c = c;
    }
    /**
     * this method return the point at which the collision occurs.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.p;
    }
    /**
     * this method return the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.c;
    }
}