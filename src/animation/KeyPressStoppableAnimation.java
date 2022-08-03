package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Dor Huri
 * this class impelement animation interface for taking the action
 * for the stop key of the animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean stop = false; // boolean for the stopping condition
    private final Animation animation; //the animation we take the key from
    private final KeyboardSensor keyboardSensor; //the keyboard sensor field
    private final String key; //the key that stop the animation
    private boolean isPressed = true; //boolean for checking if key pressed
    /**
     * this method is the constructor method for class KeyPressStoppableAnimation.
     * @param animation the animation we take the key from
     * @param keyboardSensor the keyboard sensor
     * @param key the key that stop the animation
     */
    public KeyPressStoppableAnimation(Animation animation, KeyboardSensor keyboardSensor, String key) {
        this.animation = animation;
        this.keyboardSensor = keyboardSensor;
        this.key = key;
    }
    /**
     * This method is called for doing one frame of the game each time.
     * @param d the draw surface that apply to the screen the action we did
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        //do the animation frame
        this.animation.doOneFrame(d);
        //checking iif the keyboard key is pressed
        if (this.keyboardSensor.isPressed(this.key)) {
            //checking if the key already pressed
            if (!this.isPressed) {
                //stopping condition
                this.stop = true;
            } else {
                //marking is pressed as false for stopping in the next key pressed
                this.isPressed = false;
            }
        }
    }
    /**
     * This method is for checking if the stop condition of the animation accord.
     * @return if the animation should stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
