package game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.SwingWorker;

public class Game extends Canvas implements Runnable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1442798787354930462L;

	Window _frame;

	public final static int WIDTH = 480;

	public final static int HEIGHT = WIDTH / 12 * 9;

	private Thread _thread;
	private boolean _running = false;
	ImageLibrary imageLibrary = new ImageLibrary();

	private Handler _handler;

	public Game(){
		_frame = new Window(WIDTH+6,HEIGHT+29,"Game",this);


		_handler = new Handler();
		this.addKeyListener(new KeyInput(_handler));
		Random r = new Random();

	}

	public synchronized void start(){
		_thread = new Thread(this);
		_thread.start();
		_running = true;
	}

	public synchronized void stop(){
		try{
			_thread.join();
			_running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(_running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta-=1;
			}
			if(_running){
				render();
			}
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				_frame.setTitle("Game fps: "+frames);
				frames = 0;
			} 
		}
		stop();
	}

	private void tick(){
		_handler.tick();
		int numOfEnemies=0;
		for(int i=0; i<_handler.size();i++){
			if(_handler.get(i).id==ID.BasicEnemy){
				numOfEnemies++;
			}
		}
		if(numOfEnemies<1){
			Random r = new Random();
			BasicEnemy enemy = new BasicEnemy((int)(r.nextDouble()*WIDTH),0);//(r.nextInt(WIDTH/3),r.nextInt(HEIGHT/3));
			enemy.setVel(r.nextDouble()*4-2,r.nextDouble()*4-2);
			_handler.add(enemy);
		}
	}

	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);

		_handler.render(g);

		g.dispose();
		bs.show();
	}

	public static void main(String args[]){
		new Game();
	}
}
