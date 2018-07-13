package rekit.mymod.inanimates;

import rekit.core.GameGrid;
import rekit.logic.gameelements.GameElement;
import rekit.logic.gameelements.type.DynamicInanimate;
import rekit.mymod.inanimates.states.IdleState;
import rekit.primitives.geometry.Direction;
import rekit.primitives.geometry.Polygon;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;
import rekit.util.ReflectUtils.LoadMe;

@LoadMe
public class FallBox extends DynamicInanimate {

	private FallBoxState state;
	private Polygon triangle;
	
	private FallBox() {
		super();
	}
	
	private FallBox(Vec startPos) {
		super(startPos, new Vec(1), new RGBAColor(0,0,0,1));
		this.state = new IdleState(this);
		this.triangle = new Polygon(new Vec(), new Vec[] {
				new Vec(-0.3, -0.5),
				new Vec(0.3, -0.5)
		});
	}
	
	public void setState(FallBoxState state) {
		this.state = state;
	}
	
	@Override
	public void internalRender(GameGrid f) {
		RGBAColor color = this.state.getColor();
		RGBAColor darkColor = color.darken(0.8f);
		
		Vec renderPos = this.getPos().add(this.state.getVisualOffset());
		
		f.drawRectangle(renderPos, this.getSize().scalar(0.95f), darkColor);
		f.drawRectangle(renderPos, this.getSize().scalar(0.75f), color);
		
		triangle.moveTo(renderPos.add(new Vec(0, 0.3)));
		f.drawPolygon(triangle, darkColor, true);
		
	}
	
	@Override
	public void logicLoop() {
		super.logicLoop();
		
		this.setVel(this.state.getVel());
		this.setPos(getPos().add(getVel().scalar(deltaTime / 1000f)));
		
		state.timePassed(this.deltaTime);
	}
	
	@Override
	public void reactToCollision(GameElement element, Direction dir) {
		super.reactToCollision(element, dir);
		state.reactToCollision(element, dir);
	}
	
	
	@Override
	public DynamicInanimate create(Vec startPos, String... options) {
		return new FallBox(startPos);
	}
	
	@Override
	public Integer getZHint() {
		return 1;
	}
	

}
