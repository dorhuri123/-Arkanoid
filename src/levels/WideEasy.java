package levels;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.BackgroundLevel2;
import sprites.Block;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author Dor Huri
 * this class impelement LevelInformation interface
 * for level wide easy.
 */
public class WideEasy implements LevelInformation {
    private static final int NUM_BALLS = 10;
    private static final int NUM_BLOCK = 15;
    private static final int BALL_SPEED = 10;
    private static final int PADDLE_SPEED = 75;
    private static final int PADDLE_WIDTH = 605;
    private static final String LEVEL_NAME = "Wide Easy";
    private static final double BLOCK_WIDTH = 50.8;
    private static final int BLOCK_HEIGHT = 25;
    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        //creating velocities list
        ArrayList<Velocity> velocities = new ArrayList<>();
        double startAngle = 50;
        for (double i = startAngle; i >= -50; i = i - 10) {
            //creating the velocity with speed and angle
            Velocity velocity =  Velocity.fromAngleAndSpeed(i, BALL_SPEED);
            //adding velocity to the velocities list
            velocities.add(velocity);
        }
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
        return new BackgroundLevel2();
    }
    @Override
    public List<Block> blocks() {
        //crating block list
        ArrayList<Block> blocks = new ArrayList<>();
        int y = 260, startX = 20;
        //blocks color by order in colors list
        ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.RED, Color.RED, Color.ORANGE, Color.ORANGE,
                Color.YELLOW, Color.YELLOW, Color.GREEN, Color.GREEN, Color.GREEN, Color.blue, Color.blue,
                Color.pink, Color.pink, Color.cyan, Color.cyan));
        for (double i = startX, j = 0; i <= 733; i = i + BLOCK_WIDTH, j++) {
            //creating new block and adding to block list
            Rectangle rectangle = new Rectangle(new Point(i, y), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rectangle, colors.get((int) j));
            blocks.add(block);
        }
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return NUM_BLOCK;
    }
}
