package levels;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;
import java.util.List;
/**
 * @author Dor Huri
 * this interface is for LevelInformation whice set
 * for every level the feature of the current level.
 */
public interface LevelInformation {
    /**
     * this method return the number of balls of the level.
     * @return the number of balls of the level
     */
    int numberOfBalls();
    /**
     * this method return the List Velocity balls of the level.
     * @return the List Velocity balls of the level
     */
    List<Velocity> initialBallVelocities();
    /**
     * this method return the paddle Speed of the level.
     * @return the paddle Speed of the level
     */
    int paddleSpeed();
    /**
     * this method return the paddle width of the level.
     * @return the paddle width of the level
     */
    int paddleWidth();
    /**
     * this method return the level Name of the level.
     * @return the level Name of the level
     */
    String levelName();
    /**
     * this method return the Background of the level.
     * @return the Background of the level
     */
    Sprite getBackground();
    /**
     * this method return the List blocks of the level.
     * @return the List blocks of the level
     */
    List<Block> blocks();
    /**
     * this method return the number of blocks To Remove of the level.
     * @return the number of blocks To Remove of the level
     */
    int numberOfBlocksToRemove();
}
