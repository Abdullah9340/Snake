
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Snake implements KeyListener {

	int size, speed;
	int direction = 1;
	int tailCount = 3;
	int timer = 0, timeAm = 3;
	ArrayList<Integer> x;
	ArrayList<Integer> y;
	Game game;

	public Snake(Game game) {
		this.game = game;
		speed = 32;
		size = 32;
		x = new ArrayList<>();
		y = new ArrayList<>();
		init();
	}

	public void init() {
		//Add snake body parts to snake array
		for (int i = 0; i < tailCount; i++) {
			x.add(i, SnakeGame.width / 64 - i);
			y.add(i, 0);
		}

	}

	//Check to see if snake has eaten apple
	public void eatApple(Apples apple) {
		if (x.get(0) == apple.xpos && y.get(0) == apple.ypos) {
			tailCount++;
			x.add(tailCount - 1, x.get(tailCount - 2));
			y.add(tailCount - 1, y.get(tailCount - 2));
			apple.locateApple(this);
		}
	}

	//Loop the snake around the map
	public void loopSnake() {
		final int xMax = SnakeGame.width / 32;
		switch (x.get(0)) {
		case -1:
			x.set(0, SnakeGame.width / 32 - 1);
			break;
		case xMax:
			x.set(0, 0);
			break;
		}
		final int yMax = SnakeGame.height / 32;
		switch (y.get(0)) {
		case -1:
			y.set(0, SnakeGame.height / 32 - 1);
			break;
		case yMax:
			y.set(0, 0);
			break;
		}
	}

	//Move snake
	public void move() {
		for (int i = tailCount - 1; i > 0; i--) {
			x.set(i, x.get(i - 1));
			y.set(i, y.get(i - 1));
		}
		switch (direction) {
		case 0:
			x.set(0, x.get(0) - 1);
			break;
		case 1:
			x.set(0, x.get(0) + 1);
			break;
		case 2:
			y.set(0, y.get(0) - 1);
			break;
		case 3:
			y.set(0, y.get(0) + 1);
			break;
		}
	}

	//Check if snake hits its self
	public void checkC(Game game) {
		for (int i = 1; i < tailCount; i++) {
			if (x.get(0) == x.get(i) && y.get(0) == y.get(i)) {
				game.isGameover = true;
			}
		}
	}

	//Move the snake and check for collisions
	public void update(Apples apple, Game game) {
		loopSnake();
		if (timer == timeAm) {
			timer = 0;
			move();
		} else {
			timer++;
		}
		eatApple(apple);
		checkC(game);
	}
	//Render the snake
	public void render(Graphics g) {
		for (int i = 0; i < tailCount; i++) {
			if (i == 0) {
				g.setColor(Color.green);
				g.fillRect(x.get(i)* 32, y.get(i)*32, 32, 32);
			} else {

				g.setColor(new Color(0,100,0));
				g.fillRect(x.get(i) * 32, y.get(i) * 32, 32, 32);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			direction = 0;
			break;
		case KeyEvent.VK_RIGHT:
			direction = 1;
			break;
		case KeyEvent.VK_UP:
			direction = 2;
			break;
		case KeyEvent.VK_DOWN:
			direction = 3;
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
