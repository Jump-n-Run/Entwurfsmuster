package rekit.mymod.interactinblocks;

import rekit.core.GameGrid;
import rekit.logic.gameelements.type.DynamicInanimate;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;
import rekit.util.ReflectUtils.LoadMe;;

@LoadMe
public class Button extends DynamicInanimate implements IListener{
	
	private Listener l;
	
	public final static String BUTTON_1 = "trinity/button_green_01.png";
	public final static String BUTTON_2 = "trinity/button_green_02.png";
	
	private Button() {
		super();
	}
	
	public Button(Vec start) {
		super(start, new Vec(1), new RGBAColor(0,0,0));
		l = new Listener();
		
	}
	
	@Override
	public void internalRender(GameGrid f) {
		f.drawImage(this.getPos(), this.getSize(), "trinity/button_green_01.png", true, true, false, false);
	}

	@Override
	public DynamicInanimate create(Vec startPos, String... options) {
		return new Button(startPos);
	}

	@Override
	public void act(Reciever a) {
		l.act(a);
	}

	@Override
	public void addReciever(Reciever r) {
		l.addReciever(r);
	}

	@Override
	public void removeReciever(Reciever r) {
		l.removeReciever(r);
	}

	@Override
	public void removeAll() {
		l.removeAll();
	}
	
}
