package animation;
import biuoop.DrawSurface;
import sprites.Counter;
import java.awt.Color;
/**
 * @author Dor Huri
 * this class impelement animation interface for
 * making a screen when the game end.
 */
public class EndScreen implements Animation {
    private final Counter score; //the score counter
    private final boolean youWin; // boolean for win or loose
    private final boolean stopGame = false; // boolean for the stopping condition
    /**
     * this method is the constructor method for class PauseScreen.
     * @param score the score counter
     * @param youWin boolean for win or loose
     */
    public EndScreen(Counter score, boolean youWin) {
        this.score = score;
        this.youWin = youWin;
    }
    /**
     * This method is called for doing one frame of the game each time.
     * @param d the draw surface that apply to the screen the action we did
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        //setting end screen background
        d.setColor(Color.gray);
        d.fillRectangle(0, 0, 800, 600);
        //if you win the game
        if (this.youWin) {
            //displaying the message for wining
            d.setColor(Color.GREEN);
            String message = "You Win Your score is: " + this.score.getValue();
            d.drawText(100, 300, message, 45);
        } else {
            //displaying the message for loosing
            d.setColor(Color.RED);
            String message = "Game Over. Your score is: " + this.score.getValue();
            d.drawText(50, 300, message, 45);
        }
        //setting the bottom message
        d.setColor(Color.BLACK);
        d.drawText(100, 500, "press space to continue", 55);
    }
    /**
     * This method is for checking if the stop condition of the animation accord.
     * @return if the animation should stop
     */
    @Override
    public boolean shouldStop() {
        return this.stopGame;
    }
}
