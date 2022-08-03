//209409218
package geometry;
/**
 * @author Dor Huri
 * class represent the velocity of an object by position changes in x,y axis
 */
public class Velocity {
    private double dx; //chance in position in x axis
    private double dy; //chance in position in y axis
    /**
     * this method is the constructor function for class Surface.
     * @param x chance in position in x axis
     * @param y chance in position in y axis
     */
    public Velocity(double x, double y) {
        this.dx = x;
        this.dy = y;
    }
    /**
     * this method is for setting the dx of the velocity.
     * @param x chance in position in x axis
     */
    public void setDx(double x) {
        this.dx = x;
    }
    /**
     * this method is for setting the dy of the velocity.
     * @param y chance in position in y axis
     */
    public void setDy(double y) {
        this.dy = y;
    }
    /**
     * this method is for getting the dx of the velocity.
     * @return the dx velocity
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * this method is for getting the dy of the velocity.
     * @return the dy velocity
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * this method is for setting the velocity of an object with the angle
     * and speed of the object.
     * @param angle of object depending on the chances in x,y axis
     * @param speed of object
     * @return Velocity of an object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //initializing the dx dy component with trigonometric equation of right angle triangle
        double dx = Math.cos(Math.toRadians(angle - 90)) * speed;
        double dy = Math.sin(Math.toRadians(angle - 90)) * speed;
        //returning the updated velocity
        return new Velocity(dx, dy);
    }
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    /**
     * this method Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     * @param p point object which we apply the change to
     * @return new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}