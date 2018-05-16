package game.rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import game.utils.managers.TextureManager;

/**
 * 
 * @author DarkIris3196
 *
 */
public class Texture {

	private final static Map<String, TextureManager>	texMap	= new HashMap<String, TextureManager>();
	private TextureManager								_manager;
	private String										_fileName;

	public Texture(String fileName) {
		this._fileName = fileName;
		TextureManager oldManager = texMap.get(fileName);
		if (oldManager != null) {
			_manager = oldManager;
			_manager.addReference();
		}
		try {
			_manager = new TextureManager(
					ImageIO.read(getClass().getResource("./resources/textures/" + fileName + ".png")));
			texMap.put(fileName, _manager);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void render(Graphics g, double x, double y, double width, double height) {
		g.drawImage(_manager.getImage(), (int) x, (int) y, (int) width, (int) height, null);
	}

	public void render(Graphics g, double x, double y) {
		g.drawImage(_manager.getImage(), (int) x, (int) y, null);
	}

	public BufferedImage getImage() {
		return this._manager.getImage();
	}

	@Override
	protected void finalize() throws Throwable {
		if (_manager.removeReference() && !_fileName.isEmpty())
			texMap.remove(_fileName);
		super.finalize();
	}
}
