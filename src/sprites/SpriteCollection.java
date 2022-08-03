package sprites;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dor Huri
 * this class is for SpriteCollection meaning the object of this
 * clss contain the sprite object list.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites; //the sprite list
    /**
     * this method is the constructor method for class SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * this method is for getting the game sprite list.
     * @return the sprite list
     */
    public  java.util.ArrayList<Sprite> getListSprite() {
        return this.sprites;
    }
    /**
     * this method is for adding the sprite to the sprite list.
     * @param s the sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * this method is for notifying all the sprite in the list that time passed.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the sprite before iterating over them.
        List<Sprite> spriteList = new ArrayList<>(this.sprites);
        //iterating on the sprite list
        for (Sprite s:spriteList) {
            //notifying all the sprite that time passed
            s.timePassed();
        }
    }
    /**
     * this method is for drawing all the sprite on the given DrawSurface.
     * @param d able us to draw on the created screen
     */
    public void drawAllOn(DrawSurface d) {
        // Make a copy of the sprite before iterating over them.
        List<Sprite> spriteList = new ArrayList<>(this.sprites);
        //iterating on the sprite list
        for (Sprite s:spriteList) {
            //drawing the sprite
            s.drawOn(d);
        }
    }
}