package game;
import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.Collidable;
import sprites.Counter;
import sprites.GameEnvironment;
import sprites.LevelNameIndicator;
import sprites.LivesIndicator;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import java.awt.Color;
import java.util.ArrayList;
/**
 * @author Dor Huri
 * class represent gmae object which run and initialize
 * our arachnoid game.
 */
public class GameLevel implements Animation {
    public static final int PADDLE_HEIGHT = 20; //paddle height
    public static final int BALL_SIZE = 5; //ball size
    public static final int BLOCK1_WIDTH = 20; //block1 width
    public static final int BLOCK1_HEIGHT = 600; //block1 height
    public static final int BLOCK2_WIDTH = 800; //block2 width
    public static final int BLOCK2_HEIGHT = 20; //block2 height
    public static final int BLOCK3_WIDTH = 800; //block3 width
    public static final int BLOCK3_HEIGHT = 20; //block3 height
    public static final int BLOCK4_WIDTH = 20; //block4 width
    public static final int BLOCK4_HEIGHT = 600; //block4 height
    public static final int GUI_WIDTH = 800; //gui width screen
    public static final int GUI_HEIGHT = 600; //gui height screen
    public static final int ZERO = 0;
    public static final int FOUR = 4;
    public static final Color PADDLE_COLOR = Color.yellow;
    private final SpriteCollection sprites; //for approaching all the sprite in the game
    private final GameEnvironment environment; //for approaching all the collidable in the game
    private final Counter remainingBlocks; //counter for blocks
    private final Counter remainingBall; //counter for balls
    private final Counter score; //counter for the score
    private final Counter counterNumOfLife; //counter for number of life
    private final AnimationRunner runner; //the animationRunner who run the current level
    private Boolean running; //boolean for the stopping condition
    private final biuoop.KeyboardSensor keyboard; //the keyboard sensor field
    private final LevelInformation levelInformation; //the levelInformation who tell us what is the current level
    private Paddle paddle; //the paddle of the current level
    /**
     * this method is the constructor method for class game.
     * @param keyboard the keyboard sensor
     * @param runner the animationRunner who run the current level
     * @param levelInformation tell us what is the current level
     * @param score counter for the score
     * @param counterNumOfLife counter for number of life
     */
    public GameLevel(KeyboardSensor keyboard, AnimationRunner runner, LevelInformation levelInformation,
                     Counter score, Counter counterNumOfLife) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        //initializing counters
        this.remainingBlocks = new Counter(0);
        this.remainingBall = new Counter(0);
        this.score = score;
        this.runner = runner;
        this.running = true;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
        this.counterNumOfLife = counterNumOfLife;
    }
    /**
     * this method is for adding collidable to the collidable list.
     * @param c the collidable object added
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * this method is for adding sprite to the sprite list.
     * @param s the sprite object added
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * this method is for removing collidable to the collidable list.
     * @param c the collidable object removed
     */
    public void removeCollidable(Collidable c) {
        this.environment.getListCollidable().remove(c);
    }
    /**
     * this method is for removing sprite to the sprite list.
     * @param s the sprite object removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.getListSprite().remove(s);
    }
    /**
     * this method return the number of the remaining Blocks.
     @return return the number of the remaining Blocks
     */
    public int getBlockCounter() {
        return this.remainingBlocks.getValue();
    }
    /**
     * this method return the number of the remaining balls.
     @return return the number of the remaining balls
     */
    public int getBallCounter() {
        return this.remainingBall.getValue();
    }
    /**
     * This method is for initialize the balls of the level.
     */
    private void initializeBalls() {
        //looping trow the ball Velocities of the levels
        for (Velocity velocity: this.levelInformation.initialBallVelocities()) {
            //setting the x position of the center ball
            double x = this.paddle.getX() + (this.levelInformation.paddleWidth() / 2.0D);
            //creating new ball object
            Ball ball = new Ball(new Point(x, this.paddle.getY() - 11), BALL_SIZE, Color.WHITE);
            //setting the ball velocity and game, adding it to the game
            ball.setVelocity(velocity);
            ball.setG(this.environment);
            ball.addToGame(this);
            //increasing number of ball by 1
            this.remainingBall.increase(1);
        }
    }
    /**
     * This method is for initialize the balls of the level.
     */
    private void initializePaddles() {
        //setting the x position of the paddle
        double paddleX = (GUI_WIDTH / 2.0D) - (this.levelInformation.paddleWidth() / 2.0D), paddleY = GUI_HEIGHT - 35;
        //creating the Rectangle of the paddle
        Rectangle paddleRectangle = new Rectangle(new Point(paddleX, paddleY),
                this.levelInformation.paddleWidth(), PADDLE_HEIGHT);
        //creating new paddle object
        this.paddle = new Paddle(this.keyboard, paddleRectangle, PADDLE_COLOR);
        //adding the paddle to the game
        paddle.addToGame(this);
    }
    /**
     * This method is for initialize the Walls of the level.
     */
    private void initializeGameWalls() {
        //creating the 4 walls rectangles
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle(new Point(ZERO, ZERO), BLOCK1_WIDTH, BLOCK1_HEIGHT));
        rectangles.add(new Rectangle(new Point(ZERO, 600), BLOCK2_WIDTH, BLOCK2_HEIGHT));
        rectangles.add(new Rectangle(new Point(ZERO, ZERO), BLOCK3_WIDTH, BLOCK3_HEIGHT));
        rectangles.add(new Rectangle(new Point(780, 20), BLOCK4_WIDTH, BLOCK4_HEIGHT));
        for (int i = ZERO; i < FOUR; i++) {
            //creating the death block
            if (i == 1) {
                //creating the death block
                Block deathBlock = new Block(rectangles.get(i), Color.GRAY);
                //adding the death block to the game
                deathBlock.addToGame(this);
                //creating ball remover listener
                BallRemover ballRemover = new BallRemover(this, this.remainingBall);
                //adding listener for rhe death bloc
                deathBlock.addHitListener(ballRemover);
            } else {
                //crating the block and adding them to the game
                Block block = new Block(rectangles.get(i), Color.GRAY);
                block.addToGame(this);
            }
        }
    }
    /**
     * This method is for initialize the Blocks of the level.
     */
    private void initializeBlocks() {
        //creating the score listener and the blockRemover
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        //looping trow the blocks of the levels
        for (Block block: this.levelInformation.blocks()) {
            //adding the block listeners and adding it to the game
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            block.addToGame(this);
            //increasing number of block by 1
            this.remainingBlocks.increase(1);
        }
    }
    /**
     * This method is for initialize the Indicators of the level.
     */
    private void initializeIndicators() {
        //creating and setting the level indicators
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.counterNumOfLife);
        livesIndicator.addToGame(this);
        LevelNameIndicator levelNameIndicator = new LevelNameIndicator(this.levelInformation.levelName());
        levelNameIndicator.addToGame(this);
    }

        /**
         * this method is for initialize a new game and creating
         * all the game object(blocks, balls , paddle).
         */
    public void initialize() {
        //adding the background of the level to the game
        this.levelInformation.getBackground().addToGame(this);
        //initializing the level objects
        initializePaddles();
        initializeGameWalls();
        initializeBlocks();
        initializeIndicators();
    }
    // Run the game -- start the animation loop.
    /**
     * this method is for running the game and starting the
     * animation loop of the arachnoid game.
     * all the game object(blocks, balls , paddle).
     */
    public void run() {
        //initialing level balls
        initializeBalls();
        //creating the countdown
        CountdownAnimation countDown = new CountdownAnimation(2.0D, 3, this.sprites);
        //running the countdown
        this.runner.run(countDown);
        this.running = true;
        //running the game level
        this.runner.run(this);
    }
    /**
     * This method is called for doing one frame of the game each time.
     * @param d the draw surface that apply to the screen the action we did
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        //checking if there are no blocks to remove
        if (this.remainingBlocks.getValue() == ZERO) {
            //increase score by 100
            this.score.increase(100);
            //end game
            this.running = false;
        }
        //checking if there are no ball to remove
        if (this.remainingBall.getValue() == ZERO) {
            //end game
            this.running = false;
        }
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")) {
            this.runner.run(new KeyPressStoppableAnimation(new PauseScreen(), this.keyboard, "space"));
        }
        //drawing all the sprites object
        this.sprites.drawAllOn(d);
            /*
            notifying to all the sprite object that the time is passed so they can
            chance there component according to what according in the game
             */
        this.sprites.notifyAllTimePassed();
    }
    /**
     * This method is for checking if the stop condition of the animation accord.
     * @return if the animation should stop
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}