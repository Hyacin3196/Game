package game;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8255319694373975038L;
	
	JFrame _frame;
	
	public Window(int width, int height, String title, Game game){
		_frame = new JFrame(title);

		_frame.setPreferredSize(new Dimension(width,height));
		_frame.setMaximumSize(new Dimension(width,height));
		_frame.setMinimumSize(new Dimension(width,height));
		
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setResizable(false);
		_frame.setLocationRelativeTo(null);
		//_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//_frame.setUndecorated(true);
		_frame.add(game);
		_frame.setVisible(true);
		game.start();
		game.requestFocus();
	}
	
	public void setTitle(String title){
		_frame.setTitle(title);
	}
}
