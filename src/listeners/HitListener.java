package listeners;
import sprites.Ball;
import sprites.Block;
/**
 * @author Dor Huri
 * this interface is for HitListener object
 * of the arachnoid game.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block being hit
     * @param hitter the ball hitting the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
