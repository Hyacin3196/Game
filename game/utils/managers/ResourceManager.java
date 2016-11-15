package game.utils.managers;

/**
 * 
 * @author DarkIris3196
 *
 */
public abstract class ResourceManager {

	protected int _count = 1;

	public void addReference() {
		_count++;
	}

	public boolean removeReference() {
		_count--;
		return _count == 0;
	}
}
