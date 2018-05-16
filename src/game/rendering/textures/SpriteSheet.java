package game.rendering.textures;

import game.rendering.Texture;

/**
 * 
 * @author DarkIris3196
 *
 */
public class SpriteSheet {

	private Texture	_texture;
	private int		_width, _height;

	public SpriteSheet(Texture texture, int size) {
		this(texture, size, size);
	}

	public SpriteSheet(Texture texture, int width, int height) {
		this._texture = texture;
		this._width = width;
		this._height = height;
	}

	public Texture getTexture() {
		return this._texture;
	}

	public int getWidth() {
		return this._width;
	}

	public int getHeight() {
		return this._height;
	}
}
