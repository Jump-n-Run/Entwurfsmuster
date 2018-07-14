package rekit.mymod.interactinblocks;

import rekit.logic.gameelements.type.DynamicInanimate;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;
import rekit.util.ReflectUtils.LoadMe;
@LoadMe
public class Door extends Reciever {

	@Override
	public void performAction() {
		this.setPos(new Vec(this.getPos().y - 1));
		
	}
	private Door(Vec start) {
		super(start, new Vec(1), new RGBAColor(0, 0, 0));
	}

	@Override
	public DynamicInanimate create(Vec startPos, String... options) {
		// TODO Auto-generated method stub
		return new Door(startPos);
	}

}
