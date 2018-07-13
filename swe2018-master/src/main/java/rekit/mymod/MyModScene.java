package rekit.mymod;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import rekit.config.GameConf;
import rekit.logic.GameModel;
import rekit.logic.ILevelScene;
import rekit.logic.level.LevelFactory;
import rekit.logic.scene.LevelScene;
import rekit.mymod.enemies.Pizza;
import rekit.mymod.enemies.SpriteDummy;
import rekit.mymod.inanimates.FlyingText;
import rekit.mymod.inanimates.ParticleDummy;
import rekit.persistence.level.LevelDefinition;
import rekit.persistence.level.LevelType;
import rekit.primitives.geometry.Vec;
import rekit.util.LambdaUtil;

/**
 * A test scene which can be used in {@link GameConf#DEBUG} context.
 */
public final class MyModScene extends LevelScene {

	private MyModScene(GameModel model) {
		super(model, LevelFactory.createLevel(LambdaUtil.invoke(MyModScene::getTestLevel)));
	}

	private static LevelDefinition getTestLevel() throws IOException {
		PathMatchingResourcePatternResolver resolv = new PathMatchingResourcePatternResolver();
		Resource res = resolv.getResource("/conf/mymod.dat");
		return new LevelDefinition(res.getInputStream(), LevelType.Test);
	}

	/**
	 * Create the scene by model and options
	 *
	 * @param model
	 *            the model
	 * @param options
	 *            the options
	 * @return the new scene
	 */
	public static ILevelScene create(GameModel model, String... options) {
		return new MyModScene(model);
	}

	@Override
	  public void init() {
	    super.init();
	    // Change this to add a custom handler when the player attacks (space key)
	    this.setAttackHandler((a) -> this.addGameElement(new FlyingText(this.getPlayer().getPos().addY(-1.5F), "Attack")));
	  }

	@Override
	public void start() {
		super.start();
				
		// Adding a GameElement to the scene can be done in two ways:
		// 1)	Here in this scene, using the constructor
		//		For develop and debug.
		//		Can be helpful when GameElements need references to other GameElements
		Pizza pizza = new Pizza(new Vec(12, 4));
		this.addGameElement(pizza);
		
		// 2)	Via the level generator
		//		preferable, as you can design levels better this way.
		// 		see the test level in resources/conf/mymod.dat
		//		The level generator creates the GameElements like:
		//		Pizza pizza = (Pizza) GameElementFactory.getPrototype("Pizza").create(new Vec(12, 4), String... options);
		//		so make sure to parse any options in the create-methode, if required
		
		// Uncomment this to add a global color filter
		// this.getModel().setFilter(Filter.get(LightFilter.class));
				
		
		this.addGameElement(new FlyingText(new Vec(12, 2), "This is\nthe Pizza added\nin MyModScene"));
		
		this.addGameElement(new FlyingText(new Vec(36, 2), "SpriteDummy shows how\nto render Sprites"));
		this.addGameElement(new SpriteDummy(new Vec(36, 5)));
		
		this.addGameElement(new FlyingText(new Vec(48, 2), "ParticleDummy shows how\nto use the\nParticleSpawner"));
		this.addGameElement(new ParticleDummy(new Vec(48, 5)));
		
		this.addGameElement(new FlyingText(new Vec(58, 2), "Enjoy the rest of\nthe level and then\nget started ;)"));
	}
	
	
}
