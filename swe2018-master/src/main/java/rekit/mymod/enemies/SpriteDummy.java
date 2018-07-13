package rekit.mymod.enemies;

import rekit.core.GameGrid;
import rekit.logic.gameelements.type.Enemy;
import rekit.primitives.geometry.Vec;
import rekit.util.ReflectUtils.LoadMe;

/**
 * Sample Enemy showing Sprite functionality
 *
 * @author Angelo Aracri
 */
@LoadMe
public final class SpriteDummy extends Enemy {

	private long sumTime;

	/**
	 * Prototype constructor used to dynamically
	 * {@link SpriteDummy#create(Vec, String...)} clones without knowing the
	 * concrete type.
	 */
	public SpriteDummy() {
		super();
	}

	/**
	 * Standard constructor that saves the initial position.
	 *
	 * @param startPos
	 *            the initial position of the Enemy.
	 */
	public SpriteDummy(Vec startPos) {
		super(startPos, new Vec(), new Vec(1));
	}

	@Override
	public void internalRender(GameGrid f) {
		int secs = (int) (this.sumTime / 1000f);

		f.drawImage(this.getPos(), this.getSize(), "roboindustries/walker_idle.png", true, true, false, false);
		f.drawImage(this.getPos().addX(2), this.getSize(), "roboindustries/walker_shoot_0" + (1 + secs % 7) + ".png", true, true, false, false);
		f.drawImage(this.getPos().addX(-2), this.getSize(), "roboindustries/walker_walk_03.png", true, true, false, (secs) % 2 == 0);
	}

	@Override
	protected void innerLogicLoop() {
		// Do NOT call super.innerLogicLoop to prevent physics
		this.sumTime += this.deltaTime;
	}

	@Override
	public SpriteDummy create(Vec startPos, String... options) {
		return new SpriteDummy(startPos);
	}
}
