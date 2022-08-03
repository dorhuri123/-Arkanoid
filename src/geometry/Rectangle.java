package geometry;
import java.util.ArrayList;
/**
 * @author Dor Huri
 * class represent rectangle object which have a rectangular vertices point
 * and the width and height of the rectangle
 */
public class Rectangle {
    private final Point upperLeft; //start point of line
    private final Point upperRight; //start point of line
    private final Point bottomLeft; //start point of line
    private final Point bottomRight; //start point of line
    private final double width; //line gradient
    private final double height;  //y axis of a line

    /**
     * this method is the constructor function for class ball.
     * @param upperLeft x,y value of upper left of this rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        //setting the rectangular vertices
        this.upperRight = new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY());
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + this.getHeight());
        this.bottomRight = new Point(upperLeft.getX() + getWidth(), upperLeft.getY() + getHeight());
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    /**
     * this method check if line intersect with rectangle lines and adding
     * there intersection point to the intersectionPoints list.
     * @param line which we check if intersect with rectangle lines
     @return the list of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //setting the line of this rectangle
        Line line1 = new Line(this.getUpperLeft(), this.upperRight);
        Line line2 = new Line(this.getUpperLeft(), this.bottomLeft);
        Line line3 = new Line(this.bottomLeft, this.bottomRight);
        Line line4 = new Line(this.bottomRight, this.upperRight);
        //creating list for the rectangle intersectionPoints
        ArrayList<Point> intersectionPoints = new ArrayList<>();
        //checking if line intersect with line 1
        if (line.isIntersecting(line1)) {
            //adding point to list
            intersectionPoints.add(line.intersectionWith(line1));
        }
        //checking if line intersect with line 2
        if (line.isIntersecting(line2)) {
            //adding point to list
            intersectionPoints.add(line.intersectionWith(line2));
        }
        //checking if line intersect with line 3
        if (line.isIntersecting(line3)) {
            //adding point to list
            intersectionPoints.add(line.intersectionWith(line3));
        }
        //checking if line intersect with line 4
        if (line.isIntersecting(line4)) {
            //adding point to list
            intersectionPoints.add(line.intersectionWith(line4));
        }
        return intersectionPoints;
    }
    /**
     * this method return the width of the rectangle.
     @return return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * this method return the height of the rectangle.
     @return return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * this method return the upper left point of the rectangle.
     @return return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}