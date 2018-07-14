package rekit.mymod.interactinblocks;

import rekit.core.GameGrid;
import rekit.logic.gameelements.GameElement;
import rekit.logic.gameelements.GameElementFactory;
import rekit.logic.gameelements.type.DynamicInanimate;
import rekit.primitives.geometry.Direction;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;
import rekit.util.ReflectUtils.LoadMe;;

@LoadMe
public class Button extends DynamicInanimate implements IListener {

	private Listener l = new Listener();
    private boolean notrendered = true;
	public final static String BUTTON_1 = "trinity/button_green_01.png";
	public final static String BUTTON_2 = "trinity/button_green_02.png";

	private Button() {
		super();
	}

	public Button(Vec start) {
		super(start, new Vec(1), new RGBAColor(0, 0, 0));
	}

	@Override
	public void reactToCollision(GameElement element, Direction dir) {
		if (dir == Direction.UP) {
			this.act();
		}
	}

	@Override
	public void internalRender(GameGrid f) {
		if(notrendered) {
			notrendered = false;
			for(Reciever x: l.recievers) {
				this.getScene().addGameElement(x);
			}
		}
		f.drawImage(this.getPos(), this.getSize(), "trinity/button_green_01.png", true, true, false, false);
	}

	@Override
	public DynamicInanimate create(Vec startPos, String... options) {
		GameElement e = GameElementFactory.getPrototype(options[0]).create(new Vec(
				this.getPos().x + Integer.parseInt(options[1]), this.getPos().y + Integer.parseInt(options[2])));
	
	Button b = new Button(startPos);
	b.l.recievers.add(e);
		return b;
	}

	@Override
	public void act() {
		l.act();
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
