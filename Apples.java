
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Apples {
	int xpos, ypos;
	Random ran = new Random();
	
	public Apples(Snake snake) {
		locateApple(snake); // Choose a random apple that is not inside the snakes body
	}
	
	public void update() {
		
	}
	//Find a random apple
	public void locateApple(Snake snake) {
		xpos = ran.nextInt(SnakeGame.width/32);
		ypos = ran.nextInt(SnakeGame.height/32);
		for(int i = 0; i < snake.x.size();i++) {
			if(snake.x.get(i) == xpos && snake.y.get(i) == ypos) {
				locateApple(snake);
			}
		}
	}
	//Render the apple
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(xpos*32, ypos*32, 32, 32);
	}
}
