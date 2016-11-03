package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingWorker;

public class KeyInput extends KeyAdapter{

	private Handler _handler;

	public KeyInput(Handler handler){
		this._handler = handler;
	}

	public void keyPressed(KeyEvent e){
		KeyPressWorker worker = new KeyPressWorker(e);
		try {
			worker.doInBackground();
		} catch (Exception e1) {e1.printStackTrace();}
	}
	public void keyReleased(KeyEvent e){
		KeyReleaseWorker worker = new KeyReleaseWorker(e);
		try {
			worker.doInBackground();
		} catch (Exception e1) {e1.printStackTrace();}
	}

	class KeyPressWorker extends SwingWorker<Void,Void>{
		KeyEvent event;
		KeyPressWorker(KeyEvent e){
			event = e;
		}
		@Override
		protected Void doInBackground() throws Exception {
			int key = event.getKeyCode();
			if(key == KeyEvent.VK_ESCAPE){System.exit(1);}
			for(int i=0; i<_handler.size();i++){
				GameObject obj = _handler.get(i);
				if(key == KeyEvent.VK_Z){obj.playerShooting = true;}
				if(key == KeyEvent.VK_SHIFT){obj.slow = true;}
				if(obj.getID() == ID.Player2){
					if(key == KeyEvent.VK_W){obj.up = true;}
					if(key == KeyEvent.VK_A){obj.left = true;}
					if(key == KeyEvent.VK_S){obj.down = true;}
					if(key == KeyEvent.VK_D){obj.right = true;}
				}
				if(obj.getID() == ID.Player){
					if(key == KeyEvent.VK_UP){obj.up = true;}
					if(key == KeyEvent.VK_LEFT){obj.left = true;}
					if(key == KeyEvent.VK_DOWN){obj.down = true;}
					if(key == KeyEvent.VK_RIGHT){obj.right = true;}
				}
			}
			return null;
		}

	}
	class KeyReleaseWorker extends SwingWorker<Void,Void>{
		KeyEvent event;
		KeyReleaseWorker(KeyEvent e){
			event = e;
		}
		@Override
		protected Void doInBackground() throws Exception {
			int key = event.getKeyCode();
			for(int i=0; i<_handler.size();i++){
				GameObject obj = _handler.get(i);
				if(key == KeyEvent.VK_Z){obj.playerShooting = false;}
				if(key == KeyEvent.VK_SHIFT){obj.slow = false;}
				if(obj.getID() == ID.Player2){
					if(key == KeyEvent.VK_W){obj.up = false;}
					if(key == KeyEvent.VK_A){obj.left = false;}
					if(key == KeyEvent.VK_S){obj.down = false;}
					if(key == KeyEvent.VK_D){obj.right = false;}
				}
				if(obj.getID() == ID.Player){
					if(key == KeyEvent.VK_UP){obj.up = false;}
					if(key == KeyEvent.VK_LEFT){obj.left = false;}
					if(key == KeyEvent.VK_DOWN){obj.down = false;}
					if(key == KeyEvent.VK_RIGHT){obj.right = false;}
				}
			}
			return null;
		}

	}
}
