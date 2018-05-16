package game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLibrary {
	public static Image	missileImage;
	public static Image	playerImage;
	public static Image	enemyImage;
	{
		if (missileImage == null) {
			try {
				missileImage = ImageIO.read(new File("resources\\textures\\homing_missile.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (playerImage == null) {
			try {
				playerImage = ImageIO.read(new File("resources\\textures\\gradiusvicviper.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (enemyImage == null) {
			try {
				enemyImage = ImageIO.read(new File("resources\\textures\\enemy.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
