package game.entities.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import game.entities.ID;
import game.handler.Handler;

public abstract class GameObject implements Runnable {

	public double	x				= -100, y = -100;
	public double	width, height;
	public double	centerX			= -100, centerY = -100;
	public ID		id;
	public double	velX			= 0, velY = 0;
	public double	accX			= 0, accY = 0;
	public double	r				= 0;					// angle
	public double	velR			= 0;					// angular
															// velocity
	public double	accR			= 0;					// angular
															// acceleration
	public Handler	_handler;
	public double	_hp;
	public double	_maxHp;
	public int		_damage;
	public int		invincibility	= 0;

	public boolean	up				= false;
	public boolean	down			= false;
	public boolean	left			= false;
	public boolean	right			= false;
	public boolean	slow			= false;
	public boolean	playerShooting	= false;
	public boolean	isPresent		= false;
	public boolean	destroyed		= false;

	double speed;

	public GameObject(int x, int y, ID id) {
		super();
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public void reduceHealth(double damage) {
		double health = _hp - damage;
		if (health > 0) {
			this._hp -= damage;
		} else {
			this._hp = 0;
		}
	}

	public abstract void run();

	public abstract void render(Graphics g);

	public void setHandler(Handler h) {
		_handler = h;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public void setVel(double velX, double velY) {
		this.velX = velX;
		this.velY = velY;
	}

	public ID getID() {
		return this.id;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getVelX() {
		return velX;
	}

	public double getVelY() {
		return velY;
	}
	/*
	public void destroy(){
		try {this.finalize();} catch (Throwable e) {e.printStackTrace();}
	}*/

	public boolean confirmID(ID ID) {
		return (this.id == ID);
	}
}
