package game;

import java.awt.Graphics;

public abstract class GameObject {

	protected double	x				= -100, y = -100;
	protected double	width, height;
	protected double	centerX			= -100, centerY = -100;
	protected ID		id;
	protected double	velX			= 0, velY = 0;
	protected double	accX			= 0, accY = 0;
	protected double	r				= 0;					// angle
	protected double	velR			= 0;					// velocity
	protected double	accR			= 0;					// angular
																// acceleration
	protected Handler	_handler;
	protected double	_hp;
	protected double	_maxHp;
	protected int		_damage;
	protected int		invincibility	= 0;

	boolean isPresent = false;

	double speed;

	public GameObject(int x, int y, ID id) {
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

	public abstract void tick();

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
	 * public void destroy(){ try {this.finalize();} catch (Throwable e)
	 * {e.printStackTrace();} }
	 */
}
