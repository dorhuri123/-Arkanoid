package levels;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.BackgroundLevel3;
import sprites.Block;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author Dor Huri
 * this class impelement LevelInformation interface
 * for level green 3.
 */
public class Green3 implements LevelInformation {
    private static final int NUM_BALLS = 2;
    private static final int NUM_BLOCK = 40;
    private static final int BALL_SPEED = 8;
    private static final int PADDLE_SPEED = 600;
    private static final int PADDLE_WIDTH = 90;
    private static final String LEVEL_NAME = "Green 3";
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    private final ArrayList<Block> blocks = new ArrayList<>();
    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        //creating velocities list
        ArrayList<Velocity> velocities = new ArrayList<>();
        //creating the velocity with speed and angle and adding to velocities list
        velocities.add(Velocity.fromAngleAndSpeed(40, BALL_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(-40, BALL_SPEED));
        return velocities;
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
        return new BackgroundLevel3();
    }
    @Override
    public List<Block> blocks() {
        //blocks color by order in colors list
        ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.GRAY, Color.RED, Color.YELLOW,
                Color.BLUE, Color.WHITE));
        int startPosition = 280, rowY = 150;
        for (Color color : colors) {
            for (int j = startPosition; j < 775; j = j + 50) {
                //creating new block and adding to block list
                Rectangle rectangle = new Rectangle(new Point(j, rowY), BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(rectangle, color);
                blocks.add(block);
            }
            //setting starting point for next line
            rowY += BLOCK_HEIGHT;
            startPosition += BLOCK_WIDTH;
        }
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return NUM_BLOCK;
    }
}
