package levels;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.BackgroundLevel1;
import sprites.Block;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Dor Huri
 * this class impelement LevelInformation interface
 * for level direct hit.
 */
public class DirectHit implements LevelInformation {
    private static final int ONE = 1;
    private static final int BALL_SPEED = 7;
    private static final int PADDLE_SPEED = 500;
    private static final int PADDLE_WIDTH = 90;
    private static final String LEVEL_NAME = "Direct Hit";
    @Override
    public int numberOfBalls() {
        return ONE;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //setting ball velocity
        Velocity ballVelocity = Velocity.fromAngleAndSpeed(360, BALL_SPEED);
        //creating list and adding ball to list
        ArrayList<Velocity> list = new ArrayList<>();
        list.add(ballVelocity);
        return list;
    }
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    @Override
    public Sprite getBackground() {
        //return the level Background
        return new BackgroundLevel1();
    }
    @Override
    public List<Block> blocks() {
        //crating block list
        ArrayList<Block> list = new ArrayList<>();
        //crating block rectangle
        Rectangle rectangle = new Rectangle(new Point(384, 180), 30, 30);
        Block b = new Block(rectangle, Color.RED);
        //adding the block to list
        list.add(b);
        return list;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return ONE;
    }
}
