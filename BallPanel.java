
/*
 * Concepts needed:
 *  - paintComponent @Override
 *  - gravity
 *  - OOP using Ball object
 *  - Color, clearRect, setColor, drawLine
 */
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

/* 
 IS-A:  AnimatedPanel
 HAS-A: 
    - floor
    - Collection of Balls

 DOES:
    - Knows about Balls and asks them to move & draw themselves
    - Deals with click events
*/
public class BallPanel extends AnimatedPanel {

    private List<Ball> balls = new ArrayList<>();
    private static final int FLOOR = Main.HEIGHT - 100;

    @Override
    public void updateAnimation() {
        moveBalls();
    }

    public BallPanel() {
        addEventHandlers();
        createBalls();
    }

    /*
     * Create the initial number of balls on our panel
     */
    private void createBalls() {
        for (int count = 0; count < 40; count++) {
            addBall();
        }
    }

    /*
     * This method will create a single ball and add it to our list.
     * Add the ball relatively high on the screen
     */
    public void addBall() {
        int size = (int) (Math.random() * 10) + 50;
        int x = (int) (Math.random() * (Main.WIDTH - size)) + size/2;
        int y = (int) (Math.random() * (Main.HEIGHT/ 2));
        
        Ball ball = new Ball(x, y, size, FLOOR, getRandColor());
        balls.add(ball);
    }

    private void addEventHandlers() {
        // a mouse listener requires a full interface with lots of methods.
        // to get around having implement all, we use the MouseAdapter class
        // and override just the one method we're interested in.

        // Hook up the mousePressed event to call onMouseClicked
        // using an inline/inner anonymous class
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                onMouseClicked(me);
            }
        });
    }

    /**
     * The method that gets called when the user clicks the mouse.
     * This will add the piece, update the local board and UI.
     * 
     * @param me The MouseEvent data structure provided by the event.
     */
    private void onMouseClicked(MouseEvent me) {
        // get the coordinates of the click event.
        int x = me.getX();
        int y = me.getY();
        System.out.printf("Mouse Clicked at (%d, %d)\n", x, y);

        // Do something else creative with click events
        checkAndInflateBalls(x, y);
    }

    private void checkAndInflateBalls(int x, int y) {
        for (Ball ball: balls) {
            if (ball.inside(x, y)) {
                ball.inflate();
            }
        }
    }

    public void moveBalls() {
        //System.out.println("move balls");
        for (Ball ball: balls) {
            ball.move();
        }
    }

    // do NOT override paint(Graphics g).
    // Use paintComponent(Graphics g) because...
    // paint(Graphics g) will draw borders and children--too much!
    //
    // Question: What does this @Override do?
    // A:
    @Override
    public void paintComponent(Graphics g) {
        // Always call our superclass implementation first
        super.paintComponent(g);

        this.setBackground(Color.WHITE);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.PINK);
        g.drawLine(0, BallPanel.FLOOR, this.getWidth(), BallPanel.FLOOR);
        
        for (Ball ball: balls) {
            ball.draw(g);
        }
    }
}
