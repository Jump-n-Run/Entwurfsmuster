package rekit.mymod.interactinblocks;

import rekit.logic.gameelements.type.DynamicInanimate;
import rekit.primitives.geometry.Vec;

public class Door extends DynamicInanimate implements Reciever {

	@Override
	public void performAction() {
		this.setPos(new Vec(this.getPos().y - 1));
		
	}

	@Override
	public DynamicInanimate create(Vec startPos, String... options) {
		// TODO Auto-generated method stub
		return null;
	}

}
