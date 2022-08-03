package geometry;

import java.util.List;

/**
 * @author Dor Huri
 * class represent line with a start and a finish point
 * in this class we create methods that take action with
 * line intresection, equals ect.
 */
public class Line {
    private final Point start; //start point of line
    private final Point end;  //end point of line
    private final double gradient; //line gradient
    private final double yAxis;  //y axis of a line
    /**
     * this method is the constructor function for class line.
     * @param x1 x value of start point of line
     * @param y1 y value of end point of line
     * @param x2 x value of start point of line
     * @param y2 y value of end point of line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        if (x1 != x2) {
            //according to the equation to find gradient
            this.gradient = ((y1 - y2) / (x1 - x2));
        } else {
            //otherwise gradient is the length of the line
            this.gradient = Math.abs(y1 - y2);
            //according to the equation of a straight line
        }
        this.yAxis = (y1 - (this.gradient * x1));
    }
    /**
     * this method is the constructor function for class line.
     * @param start start point of line
     * @param end end point of line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        if (this.start.getX() != this.end.getX()) {
            //according to the equation to find gradient
            this.gradient = ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
        } else {
            //otherwise gradient is the length of the line
            this.gradient = Math.abs(this.start.getY() - this.end.getY());
        }
        //according to the equation of a straight line
        this.yAxis = (this.start.getY() - (this.gradient * this.start.getX()));

    }


    /**
     * this method return the length of the line.
     * @return length of this line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * this method return the middle point of the line.
     * @return this middle point of the line
     */
    public Point middle() {
        //mid section formula to get middle x,y
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY  = (this.start.getY() + this.end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * this method return the start point of the line.
     * @return this start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * this method return the the end point of the line.
     * @return this the end point of the line
     */
    public Point end() {
        return this.end;
    }
    /**
     * this method return true if the lines intersect, false otherwise.
     * @param other line variable with start and end point
     * @return true if the lines intersect(have an intersection point), false otherwise
     */
    public boolean isIntersecting(Line other) {
     // checking if the lines are equals
     if (this.equals(other)) {
         return false;
     }
     //checking intersection in case one of the line vertical
     if (this.verticalLine(other) != null) {
         return true;
     }
        //checking intersection in case one of the line diagonal
        if (this.diagonalLine(other) != null) {
         return true;
     }
     double epsilon = Math.pow(10, -2);
     //initializing x,y coordinate of the intersection according to the equation to straight line
     double intersectionX = (other.yAxis - this.yAxis) / (this.gradient - other.gradient);
     double intersectionY = intersectionX * this.gradient + this.yAxis;
     Point intersection = new Point(intersectionX, intersectionY);
     //initializing distance A,B with the sum of the split line (start-intersection+intersection-end)
     double distanceA = (intersection.distance(this.start) + intersection.distance(this.end));
     double distanceB = (intersection.distance(other.start) + intersection.distance(other.end));
     //checking if the point can be on the lines with the current length
        double k = distanceA - this.length(), t = distanceB - other.length();
        return Math.abs(k) <= epsilon && Math.abs(t) <= epsilon;
    }
    /**
     * this method return true if the lines intersect, false otherwise.
     * @param other line variable with start and end point
     * @return true if the lines intersect(have an intersection point), false otherwise
     */
    public Point verticalLine(Line other) {
        double epsilon = Math.pow(10, -2);
        //in case both line are vertical
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()) {
            //checking if line intersect in one of the vertices of the line
            if (this.start.getX() == other.end.getX() && this.start.getY() == other.end.getY()) {
                //returning the intersection point
                return this.start;
            }
            if (this.end.getX() == other.start.getX() && this.end.getY() == other.start.getY()) {
                //returning the intersection point
                return this.end;
            }
            return null;
        }
        //in case first line is vertical and second line diagonal
        if (this.start.getX() == this.end.getX() && other.start.getY() == other.end.getY()) {
            //checking if the point are on both line
             if (this.pointOnLine(new Point(this.start.getX(), other.start.getY()))
                     && other.pointOnLine(new Point(this.start.getX(), other.start.getY()))) {
                 //returning the intersection point
                 return new Point(this.start.getX(), other.start.getY());
             }
             return null;
        }
        //in case second line is vertical and first line diagonal
        if (other.start.getX() == other.end.getX() && this.start.getY() == this.end.getY()) {
            //checking if the point are on both line
            if (this.pointOnLine(new Point(other.start.getX(), this.start.getY()))
                    && other.pointOnLine(new Point(other.start.getX(), this.start.getY()))) {
                //returning the intersection point
                return new Point(other.start.getX(), this.start.getY());
            }
            return null;
        }
        //in case first line is vertical and second line not vertical
        if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()) {
            //initializing x,y coordinate of the intersection according to the equation to straight line
            double intersectionY = this.start.getX() * other.gradient + other.yAxis;
            Point intersectionPoint = new Point(this.start.getX(), intersectionY);
            //initializing distance A,B with the sum of the split line (start-intersection+intersection-end)
            double distanceA = intersectionPoint.distance(this.start) + intersectionPoint.distance(this.end);
            double distanceB = intersectionPoint.distance(other.start) + intersectionPoint.distance(other.end);
            //checking if the point can be on the lines with the current length
            double k = distanceA - this.length(), t = distanceB - other.length();
            if (Math.abs(k) <= epsilon && Math.abs(t) <= epsilon) {
                return intersectionPoint;
            }
            return null;
        }
        //in case second line is vertical and first line not vertical
        if (this.start.getX() != this.end.getX() && other.start.getX() == other.end.getX()) {
            //initializing x,y coordinate of the intersection according to the equation to straight line
            double intersectionY = other.start.getX() * this.gradient + this.yAxis;
            Point intersectionPoint = new Point(other.start.getX(), intersectionY);
            //initializing distance A,B with the sum of the split line (start-intersection+intersection-end)
            double distanceA = intersectionPoint.distance(this.start) + intersectionPoint.distance(this.end);
            double distanceB = intersectionPoint.distance(other.start) + intersectionPoint.distance(other.end);
            //checking if the point can be on the lines with the current length
            double k = distanceA - this.length(), t = distanceB - other.length();
            if (Math.abs(k) <= epsilon && Math.abs(t) <= epsilon) {
                return intersectionPoint;
            }
            return null;
        }
        return null;
    }
    /**
     * this method return true if the lines intersect, false otherwise.
     * @param other line variable with start and end point
     * @return true if the lines intersect(have an intersection point), false otherwise
     */
    public Point diagonalLine(Line other) {
        double epsilon = Math.pow(10, -2);
        //in case both line are diagonal
        if (this.start.getY() == this.end.getY() && other.start.getY() == other.end.getY()) {
            //checking if line intersect in one of the vertices of the line
            if (this.start.getY() == other.end.getY() && this.start.getX() == other.end.getX()) {
                //returning the intersection point
                return this.start;
            }
            if (this.end.getY() == other.start.getY() && this.end.getX() == other.start.getX()) {
                //returning the intersection point
                return this.end;
            }
            return null;
        }
        //in case first line is diagonal and second line not diagonal
        if (this.start.getY() == this.end.getY() && other.start.getY() != other.end.getY()) {
            //initializing x,y coordinate of the intersection according to the equation to straight line
            double intersectionX = (this.start.getY() - other.yAxis) / other.gradient;
            Point intersectionPoint = new Point(intersectionX, this.start.getY());
            //initializing distance A,B with the sum of the split line (start-intersection+intersection-end)
            double distanceA = intersectionPoint.distance(this.start) + intersectionPoint.distance(this.end);
            double distanceB = intersectionPoint.distance(other.start) + intersectionPoint.distance(other.end);
            //checking if the point can be on the lines with the current length
            double k = distanceA - this.length(), t = distanceB - other.length();
            if (Math.abs(k) <= epsilon && Math.abs(t) <= epsilon) {
                return intersectionPoint;
            }
            return null;
        }
        //in case second line is diagonal and first line not diagonal
        if (this.start.getY() != this.end.getY() && other.start.getY() == other.end.getY()) {
            //initializing x,y coordinate of the intersection according to the equation to straight line
            double intersectionX = (other.start.getY() - this.yAxis) / this.gradient;
            Point intersectionPoint = new Point(intersectionX, other.start.getY());
            //initializing distance A,B with the sum of the split line (start-intersection+intersection-end)
            double distanceA = intersectionPoint.distance(this.start) + intersectionPoint.distance(this.end);
            double distanceB = intersectionPoint.distance(other.start) + intersectionPoint.distance(other.end);
            //checking if the point can be on the lines with the current length
            double k = distanceA - this.length(), t = distanceB - other.length();
            if (Math.abs(k) <= epsilon && Math.abs(t) <= epsilon) {
                return intersectionPoint;
            }
            return null;
        }
        return null;
    }

        /**
         * this method return the intersection point if the lines intersect,
         *  and null otherwise.
         * @param other line variable with start and end point
         * @return the intersection point if the lines intersect,
         * and null otherwise
         */
    public Point intersectionWith(Line other) {
        //checking if the line don't intersect with other
        if (!(this.isIntersecting(other))) {
            return null;
        }
        Point point1 = this.verticalLine(other);
        //checking intersection in case one of the line vertical
        if (point1 != null) {
            return point1;
        }
        Point point2 = this.diagonalLine(other);
        //checking intersection in case one of the line diagonal
        if (point2 != null) {
            return point2;
        }
        //initializing x,y coordinate of the intersection according to the equation to straight line
        double intersectionX = (other.yAxis - this.yAxis) / (this.gradient - other.gradient);
        double intersectionY = (intersectionX * this.gradient) + this.yAxis;
        //returning the intersection point
        return new Point(intersectionX, intersectionY);
    }

    /**
     * this method return true is the lines are equal, false otherwise.
     * @param other line variable with start and end point
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        //checking if the 2 lines are equal
        double x1 = this.start.getX(), y1 = this.start.getY(), x2 = this.end.getX(), y2 = this.end.getY();
        double x3 = other.start.getX(), y3 = other.start.getY(), x4 = other.end.getX(), y4 = other.end.getY();
        return (x1 == x3 && y1 == y3 && x2 == x4 && y2 == y4) || (x1 == x4 && y1 == y4 && x2 == x3 && y2 == y3);
    }
    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    /**
     * this method return the closest intersection point of the line
     * to the rectangle start of lines, and null otherwise.
     * @param rect the rectangle we check if intersect with line line
     * @return the closest intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //creating list with the rectangle intersection Points
        List<Point> list = rect.intersectionPoints(this);
        //checking if list is empty
        if (list.isEmpty()) {
            return null;
        }
        //checking if list have only one intersection Point
        if (list.size() == 1) {
            return list.get(0);
        }
        //initializing the min variables with the first intersection Point in the list
        Point minPoint = list.get(0);
        double min = this.start.distance(minPoint);
        //iterating on the list
        for (Point p: list) {
            //checking if the distance is smaller then min
            if (min > this.start.distance(p)) {
                //setting the point and min value
                minPoint = p;
                min = this.start.distance(p);
            }
        }
        //returning the closest intersection point
        return minPoint;
    }
    /**
     * this method return true is the point on the line, false otherwise.
     * @param p the point we check if is on this line
     * @return true is the point on the line, false otherwise
     */
    public boolean pointOnLine(Point p) {
        double epsilon = Math.pow(10, -2);
        //initializing the variables with the max and min of the x,y coordinate of the line
        double x1 = Math.max(this.start.getX(), this.end.getX()), y1 = Math.max(this.start.getY(), this.end.getY());
       double x2 = Math.min(this.start.getX(), this.end.getX()), y2 = Math.min(this.start.getY(), this.end.getY());
       //in case line is vertical
        if (this.start.getX() == this.end.getX()) {
            //checking if the point is in line range
            return p.getX() == this.start.getX() && p.getY() <= y1 && p.getY() >= y2;
        }
        //in case line is diagonal
        if (this.start.getY() == this.end.getY()) {
            //checking if the point is in line range
            return p.getY() == this.start.getY() && p.getX() <= x1 && p.getX() >= x2;
        }
        //checking if the point is in line range
       if (p.getX() <= x1 && p.getX() >= x2 && p.getY() <= y1 && p.getY() >= y2) {
           //checking according to the straight line formula if the point on the line
           return p.getY() - ((this.gradient * p.getX()) + this.yAxis) <= epsilon;
       }
        return false;
    }
}