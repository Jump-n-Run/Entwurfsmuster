package rekit.mymod.inanimates.states;

import rekit.logic.gameelements.GameElement;
import rekit.mymod.inanimates.FallBox;
import rekit.mymod.inanimates.FallBoxState;
import rekit.primitives.geometry.Direction;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;

public class IdleState extends FallBoxState {

	public IdleState(FallBox parent) {
		super(parent);
	}

	@Override
	public Vec getVel() {
		return new Vec();
	}
	
	@Override
	public Vec getVisualOffset() {
		return new Vec();
	}

	@Override
	public RGBAColor getColor() {
		return new RGBAColor(220, 220, 30, 255);
	}

	@Override
	public void reactToCollision(GameElement element, Direction dir) {
		if (element.getTeam().isHostile(this.parent.getTeam())) {
			this.parent.setState(new WaitingState(parent));
		}
	}

	@Override
	public void timePassed(float deltaTime) {
		// do nothing
	}


}
