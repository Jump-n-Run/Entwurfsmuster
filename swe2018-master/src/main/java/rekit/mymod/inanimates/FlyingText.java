package rekit.mymod.inanimates;

import rekit.config.GameConf;
import rekit.core.GameGrid;
import rekit.logic.gameelements.GameElement;
import rekit.logic.gameelements.type.DynamicInanimate;
import rekit.primitives.TextOptions;
import rekit.primitives.geometry.Direction;
import rekit.primitives.geometry.Polygon;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;
import rekit.util.ReflectUtils.LoadMe;

@LoadMe()
public class FlyingText extends DynamicInanimate {

	private String text;
	private TextOptions textOptions;
	private Polygon triangle;
	
	
	@SuppressWarnings("unused")
	private FlyingText() {
		super();
	}
	
	public FlyingText(Vec startPos, String text) {
		super(startPos, new Vec(1, 1), new RGBAColor(210, 80, 40));
		this.text = text;
		this.textOptions = new TextOptions(new Vec(-0.5f, -2), 20, color, GameConf.GAME_TEXT_FONT, 0);
		
		this.triangle = new Polygon(getPos().add(new Vec(0, 1.4f)), new Vec[] {
				new Vec(-0.2, -0.4),
				new Vec(0.2, -0.4)
		});
	}
	
	@Override
	public void logicLoop() {
	    super.logicLoop();
	    this.triangle.moveTo(this.getPos().addY(1.4f));
	}
	
	@Override
	public void internalRender(GameGrid f) {
		f.drawText(getPos(), text, textOptions, true);
		f.drawPolygon(triangle, color, true);
		
	}
	
	@Override
	public DynamicInanimate create(Vec startPos, String... options) {
		return new FlyingText(startPos, options.length > 0 ? options[0].replace('_', ' ').replace("0n", "\n") : "[No Text]");
	}
	
	@Override
	public void reactToCollision(GameElement element, Direction dir) {
		// do nothing
	}

}
