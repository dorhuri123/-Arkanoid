package listeners;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;
import sprites.Counter;
/**
 * @author Dor Huri
 * this class is for BlockRemover for removing block when
 * they hitt the ball.
 */
public class BlockRemover implements HitListener {
    private final GameLevel game; //the game object
    private final Counter remainingBlocks; //the counter of blocks
    /**
     * this method is the constructor method for class BlockRemover.
     * @param game object
     * @param removedBlocks counter of blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    /**
     * This method is called whenever the beingHit object is hit
     * The hitter parameter is the Ball that's doing the hitting
     * when an hit accord on the block will be removed.
     * @param beingHit the block being hit
     * @param hitter the ball hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //removing the block from the game
        beingHit.removeFromGame(this.game);
        //updating counter
        this.remainingBlocks.decrease(1);
    }
}
