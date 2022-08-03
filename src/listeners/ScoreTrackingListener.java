package listeners;

import sprites.Ball;
import sprites.Block;
import sprites.Counter;
/**
 * @author Dor Huri
 * this class is for ScoreTrackingListener for
 * tracking and updating the score.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter scoreCounter; //the score counter
    /**
     * this method is the constructor method for class ScoreTrackingListener.
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //updating counter score after hit
        this.scoreCounter.increase(5);
    }
}
