
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	private JFrame frame;
	private Canvas canvas;
	
	//Init a display
	public Display(String title, int width, int height) {
		frame = new JFrame(title);
		frame.setSize(width,height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getJFrame() {
		return frame;
	}
}
