package game.rendering;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import game.utils.managers.TextureManager;

public class Texture {

	private final static Map<String, TextureManager>	texMap	= new HashMap<String, TextureManager>();
	private TextureManager								_manager;

	public Texture(String fileName) {
		TextureManager oldManager = texMap.get(fileName);
		if (oldManager != null) {
			_manager = oldManager;
			_manager.addReference();
		}
		try {
			_manager = new TextureManager(ImageIO.read(getClass().getResource("/homing_missile.png")));
			texMap.put(fileName, _manager);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
