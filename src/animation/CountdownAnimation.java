package animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.Counter;
import sprites.SpriteCollection;
/**
 * @author Dor Huri
 * this class impelement animation interface for doing a countdown every
 * time we start an animation.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds; //number of second of the countdown
    private final int countFrom; //the number we start the count from
    private final SpriteCollection gameScreen; //the sprite that are displayed in the game screen
    private boolean running = false; // boolean for the stopping condition
    private final Counter counter; //counter for count displayed on the game screen
    /**
     * this method is the constructor method for class CountdownAnimation.
     * @param numOfSeconds number of second of the countdown
     * @param countFrom the number we start the count from
     * @param gameScreen the sprite that are displayed in the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        //setting new counter
        this.counter = new Counter(this.countFrom);
    }
    /**
     * This method is called for doing one frame of the game each time.
     * @param d the draw surface that apply to the screen the action we did
     */
    public void doOneFrame(DrawSurface d) {
        //setting new sleeper
        Sleeper sleeper = new Sleeper();
        //drawing all the sprite
        this.gameScreen.drawAllOn(d);
        //stopping condition for the countdown
        if (this.counter.getValue() == -1) {
            //for stopping the countdown animation
            this.running = true;
        } else {
            //setting color and drawing on the text on the screen
            d.setColor(java.awt.Color.WHITE);
            d.drawText(370, 400, this.counter.getValue() + "...", 50);
            //decreasing counter by 1
            counter.decrease(1);
            //setting sleep so the game will stop for the exact time we want
            long sleep = (long) ((this.numOfSeconds / this.countFrom) * 1100);
            //stopping the game with the sleeper
            sleeper.sleepFor(sleep);
        }
    }
    /**
     * This method is for checking if the stop condition of the animation accord.
     * @return if the animation should stop
     */
    public boolean shouldStop() {
        return this.running;
    }
}