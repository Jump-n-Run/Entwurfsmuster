package rekit.mymod.inanimates.states;

import net.jafama.FastMath;
import rekit.logic.gameelements.GameElement;
import rekit.mymod.inanimates.FallBox;
import rekit.mymod.inanimates.FallBoxState;
import rekit.primitives.geometry.Direction;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;

public class WaitingState extends FallBoxState {

	private static final long MAX_TIME = 1800; 
	private long timeLeft;
	private Vec visualOffset;
	
	protected WaitingState(FallBox parent) {
		super(parent);
		this.timeLeft = MAX_TIME;
		this.visualOffset = new Vec();
	}

	@Override
	public Vec getVel() {
		return new Vec();
	}
	
	@Override
	public Vec getVisualOffset() {
		return this.visualOffset;
	}


	@Override
	public RGBAColor getColor() {
		float progress = getProgress();
		return new RGBAColor((int) (220 + 35 * progress), (int) (220 - 80 * progress), 30, 255);
	}

	@Override
	public void reactToCollision(GameElement element, Direction dir) {
		// do nothing
	}
	
	private float getProgress() {
		return 1 - timeLeft / (float) MAX_TIME;
	}

	@Override
	public void timePassed(float deltaTime) {
		float progress = getProgress();
		float amp = progress * 0.07f;
		float x = (float) (Math.PI * progress * progress * 12);
		this.visualOffset = new Vec(amp * FastMath.sinQuick(x), 0);
		if ((timeLeft -= deltaTime) <= 0) {
			this.parent.setState(new FallingState(this.parent));
		}
	}

}
