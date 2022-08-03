import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;
import java.util.LinkedList;
import java.util.List;
/**
 * @author Dor Huri
 * this class is for activiating
 * the arachnoid game.
 */
public class Ass6Game {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final int FRAMES_PER_SECONDS = 60;
    public static final int NUM_OF_LIFE = 7;

    /**
     * this main is for activating
     * the arachnoid game.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        //creating the gui and all the gui component
        GUI gui = new GUI("Arachnoid Game", GAME_WIDTH, GAME_HEIGHT);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        //creating the animationRunner and the game flow
        AnimationRunner animationRunner = new AnimationRunner(gui, FRAMES_PER_SECONDS);
        GameFlow gameFlow = new GameFlow(animationRunner, NUM_OF_LIFE, keyboardSensor);
        //creating list of the game level and adding to the list
        List<LevelInformation> levelInformationList = new LinkedList<>();
        levelInformationList.add(new DirectHit());
        levelInformationList.add(new WideEasy());
        levelInformationList.add(new Green3());
        levelInformationList.add(new FinalFour());
        //creating empty list;
        List<LevelInformation> levelInformationLevels = new LinkedList<>();
        //if there is line argument we active the game according to the order in the list
        if (args.length >= 1) {
            //looping trow the line argument
            for (String s : args) {
                //if the line argument is a number between 1-4
                try {
                    if (Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 4) {
                        //add level to the new list we created
                        levelInformationLevels.add(levelInformationList.get(Integer.parseInt(s) - 1));
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            //checking if levelInformationLevels is empty
            if (levelInformationLevels.isEmpty()) {
                //running the game by default level 1 to 4
                gameFlow.runLevels(levelInformationList);
                //the list is not empty
            } else {
                //running game levels according to the list order
                gameFlow.runLevels(levelInformationLevels);
            }
            //if there are no argument send start accordingly to order 1-4
        } else {
            //running the game by default level 1 to 4
            gameFlow.runLevels(levelInformationList);
        }
        //closing the game screen
        gui.close();
    }
}
