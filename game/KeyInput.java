package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author DarkIris3196
 *
 */
public class KeyInput implements KeyListener {

	private static boolean[]	keys			= new boolean[256];
	private static boolean[]	previousKeys	= new boolean[256];

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	/**
	 * to be called after the tick method in Game class
	 */
	public void update() {
		previousKeys = keys;
	}

	public boolean isDown(int keyCode) {
		return keys[keyCode];
	}

	public boolean isPressed(int keyCode) {
		return !previousKeys[keyCode] && keys[keyCode];
	}

	public boolean isReleased(int keyCode) {
		return previousKeys[keyCode] && !keys[keyCode];
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
