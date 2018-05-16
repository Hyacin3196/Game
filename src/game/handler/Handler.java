package game.handler;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingWorker;

import game.entities.objects.GameObject;

public class Handler {
	private List<GameObject>	object			= Collections.synchronizedList(new LinkedList<GameObject>());
	private List<Thread>		objTickThreads	= new ArrayList<Thread>();
	int							it				= 0;

	public void tick() {
		System.out.println(it++);
		System.out.println("objsize - " + object.size());
		System.out.println("thsize - " + objTickThreads.size());
		objTickThreads = new ArrayList<Thread>();
		for (int i = 0; i < object.size(); i++) {
			// if(object.get(i)!=null)
			// object.get(i).run();

			Thread th = new Thread(object.get(i));
			// th.start();
			object.get(i).run();
			objTickThreads.add(th);
		}

		for (int i = 0; i < objTickThreads.size(); i++) {
			try {
				objTickThreads.get(i).join();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		objTickThreads.clear();
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
