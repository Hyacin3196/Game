package game.rendering.textures;

import java.awt.image.BufferedImage;

public class Sprite {
	private SpriteSheet		_spriteSheet;
	private BufferedImage	_image;

	public Sprite(SpriteSheet spriteSheet, int x, int y) {
		this._spriteSheet = spriteSheet;
		this._image = _spriteSheet.getTexture().getImage().getSubimage(
				(x * _spriteSheet.getWidth()) - _spriteSheet.getWidth(),
				(y * _spriteSheet.getHeight()) - spriteSheet.getHeight(), spriteSheet.getWidth(),
				spriteSheet.getHeight());
	}

	public void changeImage(int x, int y) {
		this._image = _spriteSheet.getTexture().getImage().getSubimage(
				(x * _spriteSheet.getWidth()) - _spriteSheet.getWidth(),
				(y * _spriteSheet.getHeight()) - _spriteSheet.getHeight(), _spriteSheet.getWidth(),
				_spriteSheet.getHeight());
	}
}
