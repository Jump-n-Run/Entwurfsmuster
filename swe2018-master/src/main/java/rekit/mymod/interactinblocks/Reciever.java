package rekit.mymod.interactinblocks;

import rekit.logic.gameelements.type.DynamicInanimate;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;

public abstract class Reciever extends DynamicInanimate {
	
	public Reciever(Vec start, Vec vec, RGBAColor rgbaColor) {
		super(start,vec,rgbaColor);
	}

	abstract public void performAction();
	
}
