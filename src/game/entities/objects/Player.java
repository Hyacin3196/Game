package game.entities.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.SwingWorker;

import game.Game;
import game.ImageLibrary;
import game.entities.ID;
import game.input.KeyInput;
import javafx.scene.input.KeyCode;

public class Player extends GameObject {

	int		framesPerShot		= 3;
	int		framesPerMissile	= 5;
	int		frameAfterShot		= 0;
	int		deaths;
	boolean	isColliding			= false;
	boolean	hasCollided			= true;
	int		enemiesKilled		= 0;

	List<GameObject>	missileList;
	KeyInput			keyList;

	public Player(int x, int y, KeyInput keyRecog) {
		this(x, y, ID.Player, keyRecog);
	}

	public Player(int x, int y, ID id, KeyInput keyRecog) {
		super(x, y, id);
		this.keyList = keyRecog;
		missileList = new ArrayList<GameObject>();
	}

	{
		width = 20;
		height = 30;
		deaths = 0;
	}

	@Override
	public void run() {
		if (isDown(KeyEvent.VK_SHIFT)) {
			speed = 2.5;
		} else {
			speed = 5;
		}
		if (id == ID.Player2) {
			speed = 0.5;
		}
		if (isDown(KeyEvent.VK_UP)) {
			velY = -speed;
		} else if (isDown(KeyEvent.VK_DOWN)) {
			velY = speed;
		} else {
			velY = 0;
		}
		if (isDown(KeyEvent.VK_LEFT)) {
			velX = -speed;
		} else if (isDown(KeyEvent.VK_RIGHT)) {
			velX = speed;
		} else {
			velX = 0;
		}
		x += velX;
		y += velY;
		centerX = x + width / 2;
		centerY = y + height / 2;
		// velX = 0;
		// velY = 0;
		if (x < 0) {
			x = 0;
		} else if (x > Game.WIDTH - width) {
			x = Game.WIDTH - width;
		}
		if (y < 0) {
			y = 0;
		} else if (y > Game.HEIGHT - height) {
			y = Game.HEIGHT - height;
		}
		if (id == ID.Player) {
			if (isDown(KeyEvent.VK_Z) && invincibility == 0) {
				if (frameAfterShot >= framesPerShot * framesPerMissile)
					frameAfterShot = 0;
				if (frameAfterShot % framesPerMissile == 0) {
					if (missileList.size() < 21)
						missile();
				}
				if (frameAfterShot % framesPerShot == 0) {
					shoot();
				}
				frameAfterShot++;
			} else {
				frameAfterShot = 0;
			}
		}
		if (!(invincibility == 0)) {
			invincibility--;
		}
		if (isColliding && !hasCollided) {
			deaths++;
			invincibility = 75;
			hasCollided = true;
			/*for (int i = 0; i < missileList.size(); i++) {
				_handler.remove(missileList.get(i));
			}*/
			missileList.clear();
		}
		isColliding = false;
		for (int i = 0; i < _handler.size(); i++) {
			GameObject obj = _handler.get(i);
			if (obj.id == ID.BasicEnemy) {
				if (Math.abs(this.centerX - obj.centerX) < obj.width / 2
						&& Math.abs(this.centerY - obj.centerY) < obj.height / 2) {
					if (invincibility == 0) {
						isColliding = true;
					}
				}
			}
		}
		if (!isColliding) {
			hasCollided = false;
		}
	}

	@Override
	public void render(Graphics g) {
		if (this.id == ID.Player2) {
			g.setColor(Color.green);
		} else if (this.id == ID.Player) {
			g.setColor(new Color(255, 0, 0));
		}
		if (invincibility > 45) {
			if (invincibility % 4 == 0)
				g.drawImage(ImageLibrary.playerImage, (int) x, (int) y, (int) width, (int) height, null);
		} else {
			if (invincibility % 2 == 0)
				g.drawImage(ImageLibrary.playerImage, (int) x, (int) y, (int) width, (int) height, null);
		}
		if (invincibility > 0)
			g.setColor(Color.red);
		else
			g.setColor(Color.white);
		g.drawString("Deaths: " + deaths, 0, 10);
		g.setColor(Color.white);
		g.drawString("Enemies destroyed: " + enemiesKilled, 0, 25);

		g.drawString("Controls: ArrowKeys to move, Z to shoot, Shift to 'slow movement'", 0, Game.HEIGHT - 5);
	}

	public void shoot() {
		double bulletSize = 10;
		int offset = 8;
		if (isDown(KeyEvent.VK_SHIFT))
			offset = 2;
		Bullet bullet = new Bullet((int) (x + width / 2 - bulletSize / 2), (int) (y + (height / 2)), ID.PlayerBullet,
				bulletSize);
		bullet.setVel(Math.sin(Math.toRadians(offset)) * 20, Math.cos(Math.toRadians(offset)) * -20);
		_handler.add(bullet);
		bullet = new Bullet((int) (x + width / 2 - bulletSize / 2), (int) (y + (height / 2)), ID.PlayerBullet,
				bulletSize);
		bullet.setVel(Math.sin(Math.toRadians(0)) * 20, Math.cos(Math.toRadians(0)) * -20);
		_handler.add(bullet);
		bullet = new Bullet((int) (x + width / 2 - bulletSize / 2), (int) (y + (height / 2)), ID.PlayerBullet,
				bulletSize);
		bullet.setVel(Math.sin(Math.toRadians(-offset)) * 20, Math.cos(Math.toRadians(-offset)) * -20);
		_handler.add(bullet);
	}

	public void missile() {
		double bulletSize = 10;
		HomingMissile homing = new HomingMissile((int) (x + width / 2 - bulletSize / 2), (int) (y + (height / 2)),
				bulletSize, -90);
		_handler.add(homing);
		missileList.add(homing);
		homing.setOrigin(Player.this);
		homing = new HomingMissile((int) (x + width / 2 - bulletSize / 2), (int) (y + (height / 2)), bulletSize, -120);
		_handler.add(homing);
		missileList.add(homing);
		homing.setOrigin(Player.this);
		homing = new HomingMissile((int) (x + width / 2 - bulletSize / 2), (int) (y + (height / 2)), bulletSize, -60);
		_handler.add(homing);
		missileList.add(homing);
		homing.setOrigin(Player.this);
	}

	private boolean isDown(int code) {
		return keyList.isDown(code);
	}

}
