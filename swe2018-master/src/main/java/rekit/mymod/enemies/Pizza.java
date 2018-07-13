package rekit.mymod.enemies;

import java.util.Random;

import net.jafama.FastMath;
import rekit.core.GameGrid;
import rekit.logic.gameelements.GameElement;
import rekit.logic.gameelements.type.Enemy;
import rekit.primitives.geometry.Direction;
import rekit.primitives.geometry.Frame;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;
import rekit.util.ReflectUtils.LoadMe;

/**
 * Sample Enemy that has rudimentary functionality. It bounces on the floor and
 * damages if hit from below or the sides but is vulnerable from the top.
 *
 * @author Angelo Aracri
 */
@LoadMe
public final class Pizza extends Enemy {			

	private static final int POINTS = 20;
	private static final RGBAColor COLOR_OUTER = new RGBAColor(226,150,68,255);
	private static final RGBAColor COLOR_INNER = new RGBAColor(248,199,101,255);
	private static final RGBAColor COLOR_SALAMI = new RGBAColor(213,87,65,255);
	private static final Vec SIZE_OUTER = new Vec(0.7f, 0.7f);
	private static final Vec SIZE_INNER = new Vec(0.6f, 0.6f);
	private static final Vec SIZE_SALAMI = new Vec(0.1f, 0.1f);
	private static int NUM_SALAMI = 6;
	private static float ANGLE_SPEED = 0.012f;
	private static Vec JUMP_VELOCITY = new Vec(0.0f, -15.0f);

	private static Random RNG = new Random();
	private float[] radius;
	private float currentAngle;

	/**
	 * Prototype constructor used to dynamically {@link Pizza#create(Vec, String...)}
	 * clones without knowing the concrete type.
	 */
	public Pizza() {
		super();
	}

	/**
	 * Standard constructor that saves the initial position.
	 *
	 * @param startPos
	 *            the initial position of the Enemy.
	 */
	public Pizza(Vec startPos) {
		super(startPos, new Vec(), Pizza.SIZE_OUTER);

		this.radius = new float[Pizza.NUM_SALAMI];
		for (int i = 0; i < Pizza.NUM_SALAMI; i++) {
			this.radius[i] = 0.1f + Pizza.RNG.nextFloat() * 0.15f;
		}
	}

	@Override
	public void internalRender(GameGrid f) {
		f.drawCircle(this.getPos(), this.getSize(), Pizza.COLOR_OUTER);
		f.drawCircle(this.getPos(), Pizza.SIZE_INNER, Pizza.COLOR_INNER);

		float phi = this.currentAngle;
		for (int i = 0; i < Pizza.NUM_SALAMI; i++) {
			// get polar coordinates
			phi += 2 * Math.PI / Pizza.NUM_SALAMI;
			float rad = this.radius[i];

			// convert to x, y
			Vec pos = new Vec(FastMath.sinQuick(phi) * rad, FastMath.cosQuick(phi) * rad);

			// SALAMI
			f.drawCircle(this.getPos().add(pos), Pizza.SIZE_SALAMI, Pizza.COLOR_SALAMI);
		}

		// f.drawImage(pos, size, imagePath, ingame, usefilter, mirrorX, mirrorY);
		// f.drawCircle(pos, size, color);
		// f.drawRectangle(pos, size, color);
		// f.drawRoundRectangle(pos, size, color, arcWidth, arcHeight);
		// f.drawLine(a, b, ingame, usefilter);
		// f.drawPath(startPos, pts, color, usefilter);
		// f.drawPolygon(polygon, color, fill);
		// f.drawText(pos, text, options, ingame);
	}

	@Override
	protected void innerLogicLoop() {
		// Do usual entity logic (applying velocity, ...)
		super.innerLogicLoop();

		// newAngle = time * angleSpeed
		// deltaTime is time in ms since last call of innerLogicLoop
		this.currentAngle += this.deltaTime * Pizza.ANGLE_SPEED;
	}

	@Override
	public void reactToCollision(GameElement element, Direction dir) {
		// Only continue if the element is hostile to the enemy
		// (meaning element is Player)
		if (!this.getTeam().isHostile(element.getTeam())) {
			return;
		}

		// If hit from above:
		if (dir == Direction.UP) {
			// give the player points
			this.getScene().getPlayer().addPoints(Pizza.POINTS);
			// Let the player jump 
			element.killBoost();
			// kill the enemy
			this.addDamage(1);
		} else {
			// Touched dangerous side
			// Give player damage
			element.addDamage(1);
			// Kill the enemy itself
			this.destroy();
		}
	}

	@Override
	public void collidedWithSolid(Frame collision, Direction dir) {
		// standard behavior, that prevents clipping into other blocks
		super.collidedWithSolid(collision, dir);

		// upward push
		this.setVel(Pizza.JUMP_VELOCITY);
	}

	@Override
	public Pizza create(Vec startPos, String... options) {
		return new Pizza(startPos);
	}
}
