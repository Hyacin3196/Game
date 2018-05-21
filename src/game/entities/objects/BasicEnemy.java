package game.entities.objects;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.ImageLibrary;
import game.entities.ID;

public class BasicEnemy extends GameObject {
	public int _maxHp;

	public BasicEnemy(int x, int y) {
		super(x, y, ID.BasicEnemy);
		_maxHp = 1000;
		_hp = _maxHp;
	}

	{
		width = 15;
		height = 15;
	}

	@Override
	public void run() {
		if (_hp == 0) {
			_handler.remove(this);
			Game.player.enemiesKilled++;
		}
		x += velX;
		y += velY;

		centerX = x + width / 2;
		centerY = y + height / 2;
		if (y < 0 || y > Game.HEIGHT - 15) {
			velY *= -1;
			if (y <= 0) {
				y = 0;
			} else if (y > Game.HEIGHT - 15) {
				y = Game.HEIGHT - 15;
			}
		}
		if (x < 0 || x > Game.WIDTH - 15) {
			velX *= -1;
			if (x <= 0) {
				x = 0;
			} else if (x > Game.WIDTH - 15) {
				x = Game.WIDTH - 15;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		// g.setColor(new
		// Color((int)(255*((100.0-hp)/100.0)),(int)(255*(hp/100.0)),0));
		g.setColor(new Color(127, 127, 127));
		g.drawImage(ImageLibrary.enemyImage, (int) x, (int) y, (int) width, (int) height, null);
		if (_hp < _maxHp) {
			g.setColor(Color.green);
			g.fillRect((int) x, (int) y - 5, (int) (width * (_hp / _maxHp)), 2);
			g.setColor(Color.red);
			g.fillRect((int) (x + width * (_hp / _maxHp)), (int) y - 5,
					(int) Math.ceil(width * ((_maxHp - _hp) / _maxHp)), 2);
		}
	}

}
