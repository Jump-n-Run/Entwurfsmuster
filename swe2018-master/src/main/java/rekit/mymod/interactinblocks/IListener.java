package rekit.mymod.interactinblocks;

public interface IListener {
	
	public void act(Reciever a);
	
	public void addReciever(Reciever r);
	
	public void removeReciever(Reciever r);
	
	public void removeAll();

}
