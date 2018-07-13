package rekit.mymod.inanimates;

import rekit.logic.gameelements.GameElement;
import rekit.primitives.geometry.Direction;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;

public abstract class FallBoxState {
	
	protected final FallBox parent;
	
	protected FallBoxState(FallBox parent) {
		this.parent = parent;
	}
	
	public abstract Vec getVel();
	
	public abstract Vec getVisualOffset();
	
	public abstract RGBAColor getColor();
	
	public abstract void reactToCollision(GameElement element, Direction dir);
	
	public abstract void timePassed(float deltaTime);
}
