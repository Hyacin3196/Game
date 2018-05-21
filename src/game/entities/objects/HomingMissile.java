package game.entities.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import game.ImageLibrary;
import game.entities.ID;

public class HomingMissile extends GameObject {

	private GameObject	_target		= null;
	private Player		_origin		= null;
	private double		_angle		= Math.toRadians(270);
	private double		_turnVelDeg	= 6;
	private int			lifeSpan	= 4500;

	public int _damage;

	public HomingMissile(int x, int y, double bulletSize) {
		super(x, y, ID.HomingMissile);
		width = bulletSize;
		height = bulletSize;
	}

	public HomingMissile(int x, int y, double bulletSize, double angle) {
		super(x, y, ID.HomingMissile);
		width = bulletSize;
		height = bulletSize;
		_angle = Math.toRadians(angle);
	}

	{
		speed = 8;
		_damage = 1000;
	}

	public void run() {
		if (lifeSpan < 0) {
			_handler.remove(this);
			_origin.missileList.remove(this);
		} else {
			lifeSpan--;
		}
		// THE REMOVAL OF THIS IF STATEMENT MEANS THE MISSILE CAN SWITCH BETWEEN
		// TARGETS WHEN THE NEW TARGET IS CLOSER
		// if(_target==null||!_target.isPresent){
		double minDistance = Double.MAX_VALUE;
		for (int i = 0; i < _handler.size(); i++) {
			GameObject obj = _handler.get(i);
			if (obj.id == ID.BasicEnemy) {
				double distance = Math.sqrt(Math.pow(x - obj.x, 2) + Math.pow(y - obj.y, 2));

				if (distance < minDistance) {
					minDistance = distance;
					_target = obj;
				}
			}
		}
		// if (_target == null || !_target.isPresent)
		if (_target == null)
			_handler.remove(this);
		// }

		if (_target != null) {
			double angle2follow = Math
					.toDegrees(Math.atan2(-this.centerY + _target.centerY, _target.centerX - this.centerX));
			double angleDeg = Math.toDegrees(_angle);
			double diff = (angleDeg - angle2follow);
			diff = Math.floorMod((int) diff, 360);

			if (diff >= 0 + _turnVelDeg && diff < 180) {
				angleDeg -= _turnVelDeg;
			} else if (diff >= 180 && diff < 360 - _turnVelDeg) {
				angleDeg += _turnVelDeg;
			} else {
				angleDeg = angle2follow;
			}

			_angle = Math.toRadians(angleDeg);
		}
		velY = speed * Math.sin(_angle);
		velX = speed * Math.cos(_angle);
		if (_target != null && Math.abs(this.centerX - _target.centerX) < _target.width
				&& Math.abs(this.centerY - _target.centerY) < _target.height) {
			_target.reduceHealth(_damage);
			_handler.remove(this);
			_origin.missileList.remove(this);
			_target = null;
		} else {
			x += velX;
			y += velY;
			centerX = x + width / 2;
			centerY = y + height / 2;
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		AffineTransform at = new AffineTransform();
		at.setToRotation(_angle + Math.PI / 2, centerX, centerY);
		g2.setTransform(at);
		g2.drawImage(ImageLibrary.missileImage, (int) (x + width / 6), (int) y, (int) (width * 5 / 6), (int) height,
				null);
		g2.dispose();
	}

	public void setOrigin(Player obj) {
		_origin = obj;
	}
}
