package game.handler;

import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingWorker;

import game.entities.objects.GameObject;

public class Handler {
	private List<GameObject> object = Collections.synchronizedList(new LinkedList<GameObject>());

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			// if(object.get(i)!=null)
			object.get(i).tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			// if(object.get(i)!=null)
			object.get(i).render(g);
		}
	}

	public void add(GameObject obj) {
		obj.setHandler(this);
		this.object.add(obj);
		obj.isPresent = true;
	}

	public void add(int i, GameObject obj) {
		obj.setHandler(this);
		this.object.add(i, obj);
	}

	public void remove(GameObject obj) {
		obj.isPresent = false;
		this.object.remove(obj);
	}

	public void toFront(GameObject obj) {
		object.remove(obj);
		object.add(obj);
	}

	public void toBack(GameObject obj) {
		object.remove(obj);
		object.add(0, obj);
	}

	public int size() {
		return object.size();
	}

	public GameObject get(int index) {
		return object.get(index);
	}

}
