package rekit.mymod.inanimates;

import rekit.core.GameGrid;
import rekit.logic.gameelements.GameElement;
import rekit.logic.gameelements.particles.ParticleSpawner;
import rekit.logic.gameelements.particles.ParticleSpawnerOption;
import rekit.logic.gameelements.type.DynamicInanimate;
import rekit.primitives.geometry.Direction;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;
import rekit.util.ReflectUtils.LoadMe;

@LoadMe
public class ParticleDummy extends DynamicInanimate {
	
	ParticleSpawner ps;
	long sumTime;
	
	private ParticleDummy() {
		super();
	}
	
	public ParticleDummy(Vec startPos) {
		super(startPos, new Vec(1), new RGBAColor(0,0,0,0));
		ps = new ParticleSpawner();
		ps.angle = new ParticleSpawnerOption(0.0F,9.869604401F,0.0F,9.869604401F);
		ps.colorR = new ParticleSpawnerOption(100.0F,250.0F,-100.0F,5.0F);
		ps.colorG = new ParticleSpawnerOption(100.0F,250.0F,-100.0F,5.0F);
		ps.colorB = new ParticleSpawnerOption(100.0F,250.0F,-100.0F,5.0F);
		ps.colorA = new ParticleSpawnerOption(230.0F,250.0F,-120.0F,-200.0F);
		ps.timeMin = 0.5F;
		ps.timeMax = 1.0F;
		ps.amountMin = 40;
		ps.amountMax = 50;
		ps.speed = new ParticleSpawnerOption(3.0F,5.0F,-1.0F,1.0F);
	}
	
	
	@Override
	public void internalRender(GameGrid f) {
		
	}
	
	@Override
	public void logicLoop() {
		super.logicLoop();
		sumTime += this.deltaTime;
		if (sumTime >= 2000) {
			sumTime = 0;
			ps.spawn(getScene(), getPos());
		}
	}
	
	@Override
	public void reactToCollision(GameElement element, Direction dir) {
		// not not call super.reactToCollision(element, dir);
	}
	
	
	@Override
	public DynamicInanimate create(Vec startPos, String... options) {
		return new ParticleDummy(startPos);
	}
	
	@Override
	public Integer getZHint() {
		return 1;
	}
	

}
