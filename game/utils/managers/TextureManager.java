package game.utils.managers;

import java.awt.image.BufferedImage;

/**
 * 
 * @author DarkIris3196
 *
 */
public class TextureManager extends ResourceManager {

	private BufferedImage _image;

	public TextureManager(BufferedImage image) {
		this._image = image;
	}

	public BufferedImage getImage() {
		return _image;
	}
}
