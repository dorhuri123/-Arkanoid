package geometry;
/**
 * @author Dor Huri
 * class that represent 2 double as point on the x,y axis
 * and make action accordinly.
 */
public class Point {
    private final double x;  // x coordinate
    private final double y;  // y coordinate

    /**
     * this method is the constructor function for class point.
     * @param x x axis coordinate
     * @param y y axis coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this method return the distance of this point to the other point.
     * @param other Point variable with x,y coordinate
     * @return the distance between 2 points
     */
    public double distance(Point other) {
        //distance formulae
        return Math.sqrt(((this.x - other.x) * (this.x -  other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * this method return true if the points are equal, false otherwise.
     * @param other Point variable with x,y coordinate
     * @return boolean value true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * this method return the x axis value of this point.
     * @return x axis value of this point
     */
    public double getX() {
        return x;
    }
    /**
     * this method return the y axis value of this point.
     * @return y axis value of this point
     */
    public double getY() {
        return y;
    }
}