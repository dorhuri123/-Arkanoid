package sprites;
import geometry.Point;
import geometry.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dor Huri
 * class represent GameEnvironment object which contain all the Collidable
 * object in our arachnoid game.
 */
public class GameEnvironment {
    private final ArrayList<Collidable> listCollidable; //the Collidable list
    /**
     * this method is the constructor method for class GameEnvironment.
     */
    public GameEnvironment() {
        this.listCollidable = new ArrayList<>();
    }
    // add the given collidable to the environment.
    /**
     * this method is for adding the Collidable to the Collidable list.
     * @param c the Collidable object
     */
    public void addCollidable(Collidable c) {
        listCollidable.add(c);
    }
    /**
     * this method is for getting the Collidable list.
     * @return the Collidable list
     */
    public  java.util.ArrayList<Collidable> getListCollidable() {
        return this.listCollidable;
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    /**
     * this method return the information about the closest collision that is
     * going to occur and if the collision doesn't accord return null.
     * @param trajectory the line we check collision with
     * @return return the information about the closest collision null otherwise
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Make a copy of the sprite before iterating over them.
        List<Collidable> collidableList = new ArrayList<>(this.listCollidable);
        //checking if the collidable list is empty
        if (collidableList.isEmpty()) {
            return null;
        }
        //initializing the min variables with the first Collidable object in the list
        Collidable minCollidable = collidableList.get(0);
        int flag = 0;
        //iterating on Collidable list
        for (Collidable c : collidableList) {
            //if collision accord with Collidable get the closest intersection point
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            //if there is an intersection point
            if (p != null) {
                //update flag
                flag++;
                //setting the minCollidable object
                minCollidable = c;
                //finishing the loop
                break;
            }
        }
        //if a collision didn't accord flag will be 0
        if (flag == 0) {
            return null;
        }
        //if collision accord with Collidable get the closest intersection point
        Point pointMin = trajectory.closestIntersectionToStartOfLine(minCollidable.getCollisionRectangle());
        //initializing the min variable with the first intersection Point in the list(if exist)
        double min = trajectory.start().distance(pointMin);
        //creating CollisionInfo object
        CollisionInfo collisionInfo = new CollisionInfo(pointMin, minCollidable);
        //iterating on Collidable list
        for (Collidable c:collidableList) {
            //if collision accord with Collidable get the closest intersection point
            pointMin = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            //if there is an intersection point and the point is closer
            if (pointMin != null && trajectory.start().distance(pointMin) < min) {
                //setting the min value and the collisionInfo object
                min = trajectory.start().distance(pointMin);
                collisionInfo = new CollisionInfo(pointMin, c);
        }
    }
        //returning the collisionInfo object
     return collisionInfo;
    }

}