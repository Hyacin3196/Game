package game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

public class ImageLibrary {
	protected static Image missileImage;
	protected static Image playerImage;
	protected static Image enemyImage;
	{
		if(missileImage == null){
			new SwingWorker<Void,Void>(){
				public Void doInBackground(){
					try {
						missileImage = ImageIO.read(getClass().getResource("/homing_missile.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			}.doInBackground();
		}
		
		if(playerImage == null){
			new SwingWorker<Void,Void>(){
				public Void doInBackground(){
					try {
						playerImage = ImageIO.read(getClass().getResource("/gradiusvicviper.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			}.doInBackground();
		}
		
		if(enemyImage == null){
			new SwingWorker<Void,Void>(){
				public Void doInBackground(){
					try {
						enemyImage = ImageIO.read(getClass().getResource("/enemy.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			}.doInBackground();
		}
	}
}
