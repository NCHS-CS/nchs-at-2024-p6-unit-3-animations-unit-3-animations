import java.awt.Graphics;

public class SparksPanel extends AnimatedPanel {
	
	@Override
	public void updateAnimation() {
	}
	
	public SparksPanel() {

	}
		
	// do NOT override paint(Graphics g). 
    // Use paintComponent(Graphics g) because...
	// paint(Graphics g) will draw borders and children--to much!
	@Override
	public void paintComponent(Graphics g){  
        super.paintComponent(g);

	}
}