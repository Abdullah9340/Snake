
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

	private Display display;
	private boolean running = false;

	Graphics g;
	BufferStrategy bs;

	int width, height, FPS = 30;
	boolean isGameover = false;
	String title;

	Snake snake;
	Apples apple;

	Thread thread;

	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.start();
	}

	// Init objects
	public void init() {
		display = new Display(title, width, height);
		snake = new Snake(this);
		apple = new Apples(snake);
		display.getJFrame().addKeyListener(snake);
	}

	public void update() {
		//update the snake
		if (!isGameover) {
			snake.update(apple, this);
		}
	}

	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		g.clearRect(0, 0, width, height);
		// Draw
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		//Check to see if the game is over
		if (isGameover) {
			g.setColor(Color.white);
			g.setFont(new Font("SansSerif", Font.PLAIN, 18));
			g.drawString("You Died", SnakeGame.width/2 - 32, SnakeGame.height/2);
		} else {
			snake.render(g);
			apple.render(g);
		}
		// End Draw
		bs.show();
		g.dispose();
	}

	public void run() {
		init();

		//Create constant framerate
		double timePerTick = 1000000000 / FPS;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;

			if (delta >= 1) {
				update();
				render();
				delta--;
			}

		}
	}

	public void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		if (!running) {
			return;
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
