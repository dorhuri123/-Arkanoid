//209409218
package sprites;
import geometry.Point;
import geometry.Velocity;
import game.GameLevel;
import geometry.Rectangle;
import geometry.Line;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Dor Huri
 * class represent ball object which have a center point size color
 * surface velocity and starting position all this attribute will help us
 * to animate the ball on the gui screen.
 */
public class Ball implements Sprite  {
    public static final int ZERO = 0;
    public static final int PADDLE_MOVEMENT = 11; // paddle movement plus 1
    public static final int RIGHT_MARGIN = 780; // the right border of the gui screen
    public static final int LEFT_MARGIN = 20; // the left border of the gui screen
    private Point center; //center point of ball
    private final int r; // radius of the ball
    private final java.awt.Color color; //color of this ball
    private Velocity v; //velocity of this ball
    private int startPosition; // start position of ball surface
    private  GameEnvironment g;
    /**
     * this method is the constructor function for class ball.
     * @param center x,y value of center point of ball
     * @param r the radius of the ball
     * @param color color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }
    /**
     * this method return the x value of the ball center.
    @return x value of the ball center
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * this method return the y value of the ball center.
     @return y value of the ball center
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * this method return the size of the ball radius.
     @return the size of the ball radius
     */
    public int getSize() {
        return this.r;
    }
    /**
     * this method add the ball object to the game environment.
     * @param game ball game environment
     */
    public void addToGame(GameLevel game) {
        //adding ball object to the game environment
        game.addSprite(this);
    }
    /**
     * this method return the color of the ball.
     @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * this method draw the ball on the given DrawSurface.
     * @param surface1 able us to draw on the created screen
     */
    public void drawOn(DrawSurface surface1) {
        //setting block color
        surface1.setColor(Color.BLACK);
        //drawing the rectangle shape and color on screen
        surface1.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.getSize());
        //setting ball color and filing the ball by surface
        surface1.setColor(this.getColor());
        surface1.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * this method set the velocity of the ball.
     * @param v1 velocity of this ball
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }
    /**
     * this method set the velocity of the ball with the dx,dy component.
     * @param dx represent chance in position in x axis
     * @param dy represent chance in position in y axis
     */
    public void setVelocity(double dx, double dy) {
        //creating new velocity object and setting dx,dy
        this.v = new Velocity(dx, dy);
        this.v.setDx(dx);
        this.v.setDy(dy);
    }
    /**
     * this method get the ball object from the game environment.
     * @return the ball game environment.
     */
    public GameEnvironment getG() {
        return this.g;
    }
    /**
     * this method set the ball object from the game environment.
     * @param game ball game environment.
     */
    public void setG(GameEnvironment game) {
        this.g = game;
    }
        /**
         * this method set the starting point position of the ball in his giving frame.
         * @param startPosition1 start position of ball surface
         */
    public void setStartPosition(int startPosition1) {
        this.startPosition = startPosition1;
    }
    /**
     * this method set the starting point position of the ball in his giving frame.
     * @param center1 start position of ball surface
     */
    public void setCenter(Point center1) {
        this.center = center1;
    }
    /**
     * this method return the starting point position of the ball.
     @return the starting point position of the ball
     */
    public int getStartPosition() {
        return this.startPosition;
    }
    /**
     * this method return the ball velocity.
     @return return the ball velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }
    /**
     * this method check if ball inside the paddle.
     @return return true if the ball inside the paddle or false if not
     */
    public boolean ballInsidePaddle() {
        //getting the collidable object the paddle
        Collidable collidable = this.g.getListCollidable().get(ZERO);
        //getting the rectangle of the paddle
        Rectangle rectangle = collidable.getCollisionRectangle();
        //initializing the variables with the ball and the paddle rectangle coordinates
        double ballX = this.getX(), ballY = this.getY(), upperY = rectangle.getUpperLeft().getY();
        double upperX = rectangle.getUpperLeft().getX(), height = rectangle.getHeight(), width = rectangle.getWidth();
        //checking if the ball is inside the paddle boundary's
        return (ballX <= upperX + width) && (ballX >= upperX) && (ballY <= upperY + height) && (ballY >= upperY);
    }
    /**
     * this method check get the ball out of the paddle
     * in cases the ball is inside her.
     */
    public void getBallOutFromPaddle() {
        //getting the collidable object the paddle
        Collidable collidable = this.g.getListCollidable().get(ZERO);
        //getting the collidable object the paddle
        Rectangle rectangle = collidable.getCollisionRectangle();
        //initializing the variables with the paddle rectangle coordinates and creating point upper right
        double x = rectangle.getUpperLeft().getX(), y = rectangle.getUpperLeft().getY();
        Point upperRight = new Point(x, y);
        /*
        if the ball is closer to the right side get him out on the right side if the ball is
        closer to the left side get him out on the left side
        */
        if (this.center.distance(upperRight) > this.center.distance(rectangle.getUpperLeft())) {
            this.setCenter(new Point((this.getX() - PADDLE_MOVEMENT), y));
        } else {
            this.setCenter(new Point((this.getX() + PADDLE_MOVEMENT), y));
        }
        /*
        for cases the paddle hit the left or right border and get inside the paddle or
        block so instead the ball will go up
        */
        if (this.center.getX() - this.r <= LEFT_MARGIN) {
            this.setCenter(new Point((this.getX() + PADDLE_MOVEMENT), y - this.r));
        } else if (this.center.getX() + this.r >= RIGHT_MARGIN) {
            this.setCenter(new Point((this.getX() - PADDLE_MOVEMENT), y - this.r));
        }
    }
    /**
     * this method remove the ball object from the game environment.
     * @param game ball game environment
     */
    public void removeFromGame(GameLevel game) {
        //removing the ball object from the game environment
        game.removeSprite(this);
    }
    /**
     * this method set the ball movement according to the ball borders so he will be
     * able keep bouncing but stay it the frame.
     */
    public void moveOneStep() {
        //initializing the line trajectory
        Line trajectory = new Line(this.center, this.v.applyToPoint(this.center));
        //checking if a collision accord if not keep moving as expected
        if (this.g.getClosestCollision(trajectory) == null) {
            //setting the ball according to movement
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        //getting the collision point
        Point collision = this.g.getClosestCollision(trajectory).collisionPoint();
        //initializing the variables with the ball center
        double pointX = this.getX(), pointY = this.getY();
        //setting the point according the to the velocity component negative or positive
        if (this.v.getDx() < 0) {
            pointX = collision.getX() + this.r;
        } else if (this.v.getDx() > 0) {
            pointX = collision.getX() - this.r;
        }
        if (this.v.getDy() < 0) {
            pointY = collision.getY() + this.r;
        } else if (this.v.getDy() > 0) {
            pointY = collision.getY() - this.r;
        }
        //getting the closest collidable the ball will hit
        Collidable collidable =  this.g.getClosestCollision(trajectory).collisionObject();
        //initializing the near collision with a point close to the collision point
        Point nearCollision = new Point(pointX, pointY);
        //setting the ball to the near collision point
        this.setCenter(nearCollision);
        //updating the velocity according to the object hit function
        this.setVelocity(collidable.hit(this, collision, this.v));
        //while ball is in the paddle
        while (this.ballInsidePaddle()) {
            //getting the ball out of paddle
            this.getBallOutFromPaddle();
        }
    }
}
