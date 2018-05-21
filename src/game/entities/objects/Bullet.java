package game.entities.objects;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.entities.ID;

public class Bullet extends GameObject {

	private int border = 0;

	public int _damage;

	public Bullet(int x, int y, ID id, double bulletSize) {
		super(x, y, id);
		if (id == ID.PlayerBullet) {
			velX = 0;
			velY = -10;
			width = bulletSize;
			height = bulletSize;
		}
	}

	{
		_damage = 25;
	}

	@Override
	public void run() {

		if (y < border || y > Game.HEIGHT) {
			_handler.remove(this);
		}
		x += velX;
		y += velY;
		centerX = x + width / 2;
		centerY = y + height / 2;
		for (int i = 0; i < _handler.size(); i++) {
			if (_handler.get(i) != null) {
				GameObject obj = _handler.get(i);
				if (obj.id == ID.BasicEnemy)
					if (Math.abs(this.centerX - obj.centerX) < obj.width * 1
							&& Math.abs(this.centerY - obj.centerY) < obj.height * 1) {
						obj.reduceHealth(_damage);
						_handler.remove(this);
					}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if (y < border || y > Game.HEIGHT) {
			_handler.remove(this);
		}
		if (id == ID.PlayerBullet) {
			g.setColor(Color.white);
		} else if (id == ID.EnemyBullet) {
			g.setColor(Color.black);
		}
		g.fillOval((int) x, (int) y, (int) width, (int) height);
	}

}
