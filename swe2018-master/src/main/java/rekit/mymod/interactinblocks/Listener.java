package rekit.mymod.interactinblocks;

import java.util.ArrayList;
import java.util.List;

public abstract class Listener {
	
	protected List<Reciever> recievers;
	
	public Listener() {
		recievers = new ArrayList<Reciever>();
	}
	
	public void act(Reciever a) {
		for(Reciever i: recievers) {
			i.performAction();
		}
	}
	
	public void addReciever(Reciever r) {
		recievers.add(r);
	}
	
	public void removeReciever(Reciever r) {
		recievers.remove(r);
	}
	
	public void removeAll(Reciever r) {
		recievers = new ArrayList<Reciever>();
	}

}
