package rekit.mymod.interactinblocks;

import rekit.logic.gameelements.GameElement;

public interface IListener {
	
	public void act();
	
	public void addReciever(Reciever r);
	
	public void removeReciever(Reciever r);
	
	public void removeAll();

}
