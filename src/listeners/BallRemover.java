package listeners;

import game.GameLevel;
import sprites.Ball;
import sprites.Block;
import sprites.Counter;
/**
 * @author Dor Huri
 * this class is for BallRemover for removing ball when
 * they hitt the death block.
 */
public class BallRemover implements HitListener {
    private final GameLevel game; //the game object
    private final Counter remainingBall; //the counter of balls
    /**
     * this method is the constructor method for class BallRemover.
     * @param game object
     * @param remainingBall counter of balls
     */
    public BallRemover(GameLevel game, Counter remainingBall) {
        this.game = game;
        this.remainingBall = remainingBall;
    }
    /**
     * This method is called whenever the beingHit object is hit
     * The hitter parameter is the Ball that's doing the hitting
     * when an hit accord on the death block the ball is removed.
     * @param beingHit the block being hit
     * @param hitter the ball hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //removing the ball from the game
        hitter.removeFromGame(this.game);
        //updating counter
        this.remainingBall.decrease(1);
    }
}
