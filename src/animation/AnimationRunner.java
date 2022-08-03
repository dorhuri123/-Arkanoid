package animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * @author Dor Huri
 * this class is for making the game annimtion to run
 * according to the game order.
 */
public class AnimationRunner {
    private final GUI gui; //gui of the game
    private final int framesPerSecond; //frames per second
    private final Sleeper sleeper; //the sleeper of the game
    /**
     * this method is the constructor method for class AnimationRunner.
     * @param gui screen of game
     * @param framesPerSecond frames per second
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.sleeper = new Sleeper();
        this.framesPerSecond = framesPerSecond;
    }
    /**
     * This method is called to run the current
     * animation that being played in the game.
     * @param animation the animation we run
     */
    public void run(Animation animation) {
        //setting frame milli seconds
        long millisecondsPerFrame =  (1000 / this.framesPerSecond);
        //while the stopping condition didn't accord
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            //getting the draw surface from the gui
            DrawSurface d = gui.getDrawSurface();
            //doing the animation for one frame then showing on the draw surface
            animation.doOneFrame(d);
            gui.show(d);
            //setting the sleeper timer
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

}
