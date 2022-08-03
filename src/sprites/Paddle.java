package sprites;
import geometry.Rectangle;
import geometry.Point;
import geometry.Velocity;
import game.GameLevel;
import geometry.Line;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Dor Huri
 * class represent paddle object which have a rectangle shape color and KeyboardSensor object
 * and also implements intrafaceses Collidable, Sprite so when object collid
 * with the paddle he will bounce of him.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard; //paddle KeyboardSensor object
    private Rectangle rectangle; //rectangle shape of the paddle
    private final java.awt.Color color; //color of this paddle
    /**
     * this method is the constructor method for class paddle.
     * @param keyboard paddle KeyboardSensor object
     * @param rectangle rectangle shape of the paddle
     * @param color color of this paddle rectangle
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, java.awt.Color color) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * this method is for moving the paddle left.
     */
    public void moveLeft() {
        //checking if the paddle is in the end of the left side of the screen
        if (this.rectangle.getUpperLeft().getX() <= 10) {
            return;
        }
        //setting the new location for the paddle after the left movement
        double newX = this.rectangle.getUpperLeft().getX() - 10, newY = this.rectangle.getUpperLeft().getY();
        this.rectangle = new Rectangle(new Point(newX, newY), this.rectangle.getWidth(), this.rectangle.getHeight());

    }
    /**
     * this method is for moving the paddle right.
     */
    public void moveRight() {
        //checking if the paddle is in the end of the right side of the screen
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() >= 790) {
            return;
        }
        //setting the new location for the paddle after the right movement
        double newX = this.rectangle.getUpperLeft().getX() + 10, newY = this.rectangle.getUpperLeft().getY();
        this.rectangle = new Rectangle(new Point(newX, newY), this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    // Sprite
    /**
     * this method is for updating the paddle that timed passed and move
     * according to the received input from keyboard.
     */
    public void timePassed() {
        //if the left key is pressed in the keyboard
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            //move paddle left
            this.moveLeft();
        }
        //if the right key is pressed in the keyboard
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            //move paddle right
            this.moveRight();
        }
    }
    /**
     * this method is for drawing the paddle on the given DrawSurface.
     * @param surface1 able us to draw on the created screen
     */
    public void drawOn(DrawSurface surface1) {
        //setting paddle color
        surface1.setColor(this.color);
        //drawing the rectangle shape and color on screen
        surface1.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface1.setColor(this.color);
        surface1.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }
    /**
     * this method return the rectangle of this paddle.
     * @return the rectangle shape of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * this method return the velocity of the object after hitting the paddle depending
     * on where the object hit the rectangle paddle.
     * @param collisionPoint the collision point with the paddle
     * @param currentVelocity the current velocity of the object hitting the paddle
     * @param hitter the ball that hit the object
     * @return the velocity of the object after hitting the paddle
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //setting dx, dy with the velocity component and the divided length
        double lengthPart = this.rectangle.getWidth() / 5, dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        //getting the new speed vector
        double newSpeed = Math.sqrt((dx * dx) + (dy * dy));
        //setting the point of the rectangle
        Point upperLeft = this.rectangle.getUpperLeft();
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + this.rectangle.getHeight());
        Point bottomRight = new Point(upperLeft.getX() + this.rectangle.getWidth(),
                upperLeft.getY() + this.rectangle.getHeight());
        Point upperRight = new Point(upperLeft.getX() + this.rectangle.getWidth(), upperLeft.getY());
        //setting the line in the side of the rectangle
        Line left = new Line(upperLeft, bottomLeft);
        Line right = new Line(upperRight, bottomRight);
        //in case we are in the first area of paddle or we hit the left side paddle
        if (upperLeft.distance(collisionPoint) <= lengthPart || left.pointOnLine(collisionPoint)) {
            //return the updated velocity
            return Velocity.fromAngleAndSpeed(-60, newSpeed);
            //in case we are in the second area of paddle
        } else if (upperLeft.distance(collisionPoint) <= 2 * lengthPart) {
            //return the updated velocity
            return Velocity.fromAngleAndSpeed(-30, newSpeed);
            //in case we are in the third area of paddle
        } else if (upperLeft.distance(collisionPoint) <= 3 * lengthPart) {
            //return the updated velocity
            return Velocity.fromAngleAndSpeed(0, newSpeed);
            //in case we are in the fourth area of paddle
        } else if (upperLeft.distance(collisionPoint) <= 4 * lengthPart) {
            //return the updated velocity
            return Velocity.fromAngleAndSpeed(30, newSpeed);
            //in case we are in the fifth area of paddle
        } else if (upperLeft.distance(collisionPoint) <= 5 * lengthPart || right.pointOnLine(collisionPoint)) {
            //return the updated velocity
            return Velocity.fromAngleAndSpeed(60, newSpeed);
        }
        return currentVelocity;
    }
    /**
     * this method add the paddle to the game and to the sprite and collidable lists.
     * @param g able us to add the paddle object to the sprite and collidable lists
     */
    public void addToGame(GameLevel g) {
        //adding object to the sprite and collidable lists
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * this method return the x value of the paddle rectangle.
     @return x value of the ball center
     */
    public double getX() {
        return this.rectangle.getUpperLeft().getX();
    }
    /**
     * this method return the y value of the paddle rectangle.
     @return x value of the ball center
     */
    public double getY() {
        return this.rectangle.getUpperLeft().getY();
    }
}
