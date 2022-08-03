package game;
import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import sprites.Counter;
import java.util.List;
/**
 * @author Dor Huri
 * this class is for runnig all the game level in their corect order
 * and stopping the game in case the player is out of life.
 */
public class GameFlow {
    private final AnimationRunner animationRunner; //the animationRunner who run the current level
    private final int numOfLife; //number of life
    private final KeyboardSensor keyboardSensor; //the keyboard sensor field
    /**
     * this method is the constructor method for class KeyPressStoppableAnimation.
     * @param animationRunner the animation we take the key from
     * @param keyboardSensor the keyboard sensor
     * @param numOfLife the key that stop the animation
     */
    public GameFlow(AnimationRunner animationRunner, int numOfLife, KeyboardSensor keyboardSensor) {
        this.animationRunner = animationRunner;
        this.numOfLife = numOfLife;
        this.keyboardSensor = keyboardSensor;
    }
    /**
     * This method is for running each level in his turn
     * and going to next one until there are no levels
     * or the player is out of lives.
     * @param levels the level list that we run
     */
    public void runLevels(List<LevelInformation> levels) {
        //initializing counters
        Counter score = new Counter(0);
        Counter live = new Counter(this.numOfLife);
        //looping trow the levels list
        for (LevelInformation levelInformation: levels) {
            //setting new game level for the current level
            GameLevel gameLevel = new GameLevel(this.keyboardSensor, this.animationRunner,
                    levelInformation, score, live);
            //initializing level
            gameLevel.initialize();
            //while there are balls and lives left
            while (gameLevel.getBlockCounter() > 0 && live.getValue() > 0) {
                //run the current level
                gameLevel.run();
                //if there are block left and no balls
                if (gameLevel.getBlockCounter() > 0 && gameLevel.getBallCounter() == 0) {
                    //live decrease counter
                    live.decrease(1);
                }
            }
            //if there are no live go to end screen
            if (live.getValue() == 0) {
                break;
            }
        }
        //running end screen animation
        this.animationRunner.run(new KeyPressStoppableAnimation(new EndScreen(score, (live.getValue() != 0)),
                this.keyboardSensor, "space"));
    }
}
