package rekit.mymod.interactinblocks;

import java.util.ArrayList;
import java.util.List;

import rekit.logic.gameelements.GameElement;

public class Listener implements IListener{
	
	protected List<Reciever> recievers;
	
	public Listener() {
		recievers = new ArrayList<Reciever>();
	}
	
	public void act() {
		for(Reciever i: recievers) {
			i.performAction();
		}
	}
	
	public void removeReciever(Reciever r) {
		recievers.remove(r);
	}
	
	public void removeAll() {
		recievers.clear();
	}

	@Override
	public void addReciever(Reciever e) {
		// TODO Auto-generated method stub
		
	}


}
