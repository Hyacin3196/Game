package game.handler;

import java.util.Collections;
import java.util.List;

import game.entities.objects.GameObject;

public class GameObjRunner implements Runnable {
	protected List<GameObject> list;

	public GameObjRunner(List<GameObject> list) {
		this.list = Collections.synchronizedList(list);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
