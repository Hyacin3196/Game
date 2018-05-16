package game.handler;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingWorker;

import game.entities.objects.GameObject;

public class Handler {
	private List<GameObject>	objects	= Collections.synchronizedList(new LinkedList<GameObject>());
	private List<Thread>		threads	= new ArrayList<Thread>();
	int							numOfThreads;

	public Handler(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}

	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).run();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			// if(object.get(i)!=null)
			objects.get(i).render(g);
		}
		/*List<List<GameObject>> listolist = Collections.synchronizedList(new LinkedList<List<GameObject>>());
		threads = new ArrayList<Thread>();
		
		int itemsPerList = objects.size() / numOfThreads;
		for (int i = 0; i < numOfThreads - 1; i++) {
			int start = i * itemsPerList;
			listolist.add(objects.subList(start, start + itemsPerList));
		}
		{
			int start = (numOfThreads - 1) * itemsPerList;
			listolist.add(objects.subList(start, objects.size()));
		}
		
		for (int i = 0; i < objects.size(); i++) {
		}
		
		for (int i = 0; i < listolist.size(); i++) {
			List<GameObject> ObjList = listolist.get(i);
			Thread th = new Thread(new GameObjRunner(ObjList) {
				@Override
				public void run() {
					for (GameObject obj : ObjList) {
						obj.render(g);
					}
				}
			});
			th.start();
			threads.add(th);
		}
		
		for (int i = 0; i < threads.size(); i++) {
			try {
				threads.get(i).join();
		
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}

	public void add(GameObject obj) {
		obj.setHandler(this);
		this.objects.add(obj);
		obj.isPresent = true;
	}

	public void add(int i, GameObject obj) {
		obj.setHandler(this);
		this.objects.add(i, obj);
	}

	public void remove(GameObject obj) {
		obj.isPresent = false;
		this.objects.remove(obj);
	}

	public void toFront(GameObject obj) {
		objects.remove(obj);
		objects.add(obj);
	}

	public void toBack(GameObject obj) {
		objects.remove(obj);
		objects.add(0, obj);
	}

	public int size() {
		return objects.size();
	}

	public GameObject get(int index) {
		return objects.get(index);
	}

}
