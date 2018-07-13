package rekit.mymod.inanimates.states;

import rekit.logic.gameelements.GameElement;
import rekit.mymod.inanimates.FallBox;
import rekit.mymod.inanimates.FallBoxState;
import rekit.primitives.geometry.Direction;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;

public class FallingState extends FallBoxState {

	private static final float ACCEL = 2;
	private static final float MAX_VEL = 10;
	private float currentVel;
	
	protected FallingState(FallBox parent) {
		super(parent);
	}

	@Override
	public Vec getVel() {
		return new Vec(0, currentVel);
	}
	
	@Override
	public Vec getVisualOffset() {
		return new Vec();
	}

	@Override
	public RGBAColor getColor() {
		return new RGBAColor(255, 140, 30);
	}

	@Override
	public void reactToCollision(GameElement element, Direction dir) {
		// do nothing
	}

	@Override
	public void timePassed(float deltaTime) {
		currentVel = Math.min(MAX_VEL, currentVel + ACCEL * (deltaTime / 1000));
	}
}
