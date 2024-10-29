import java.awt.Color;
import java.awt.Graphics;

/* 
 IS-A:  Object (no parent)
 HAS-A: 
    - (x,y) Coordinate
    - vertical velocity
    - understanding of where the floor is
    - a size (diameter)
    - color
    - STATIC gravity force acting on it
 DOES:
    - Draws itself
    - Moves itself according to gravity
    - Can tell if (x,y) is inside the ball (for clicking)
*/
public class Ball {

	private int x;
    private int y;
    private int size;
    private int floor;
    private Color color;
    private int yVelocity;

    // Allow an external entity to change our global gravity
	public static int gravity = -3;
    
    /*
    * Create a Ball with these values
    */
	public Ball(int x, int y, int size, int floor, Color color) {
		this.x = x;
        this.y = y;
        this.size = size;
        this.floor = floor;
        this.color = color;
        this.yVelocity = 0;
	}
	
	public boolean inside(int x, int y) {
		int xCenter = this.x + size/2;
        int yCenter = this.y + size/2;
        
        return Math.hypot(xCenter - x, yCenter - y) <= size;
	}

    public void inflate() {
        this.size += 10;
    }

    /*
    * Update the vertical velocity according to gravity
    * Updates the (x, y) coordinate given its velocity
    *    Assures that it stays above the floor
    *    Bounces off the floor if it hits the floor 
    * 
    */
	public void move() {
        yVelocity -= gravity;
        y += yVelocity;

        if (y + size > floor) {
            y -= yVelocity;
            yVelocity *= -1;
        }
    }

    /*
    * Draws the ball using the Graphics object.
    */
	public void draw(Graphics g) {
        
        // consider drawing it a random color each time to illustrate some animation
        // using AnimatedPanel::getRandColor
        g.setColor(color);
        g.fillOval(x, y, size, size);
	}
	
}
