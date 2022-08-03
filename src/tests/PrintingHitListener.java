package tests;
import listeners.HitListener;
import sprites.Ball;
import sprites.Block;
/**
 * @author Dor Huri
 * this class is for PrintingHitListener for testing the removal of block.
 */
public class PrintingHitListener implements HitListener {
    /**
     * This method is called whenever the beingHit object is hit
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block being hit
     * @param hitter the ball hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
