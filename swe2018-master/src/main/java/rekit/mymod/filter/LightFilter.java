package rekit.mymod.filter;

import java.util.Arrays;

import rekit.logic.filters.Filter;
import rekit.primitives.image.AbstractImage;
import rekit.primitives.image.RGBAColor;
import rekit.util.ReflectUtils.LoadMe;

/**
 * A light up filter.
 *
 * @author Dominik Fuchss
 *
 */
@LoadMe
public final class LightFilter implements Filter {
	private LightFilter() {
		// Only system shall create
	}

	@Override
	public RGBAColor apply(RGBAColor color) {
		int r = (int) Math.min(255, color.red * 1.5);
		int g = (int) Math.min(255, color.green * 1.5);
		int b = (int) Math.min(255, color.blue * 1.5);
		int a = (int) Math.min(255, color.alpha / 1.5);

		return new RGBAColor(r, g, b, a);
	}

	@Override
	public AbstractImage apply(AbstractImage imageData) {
		AbstractImage result = new AbstractImage(imageData.height, imageData.width, Arrays.copyOf(imageData.pixels, imageData.pixels.length));
		for (int px = 0; px < result.pixels.length; px += 4) {
			int r = Byte.toUnsignedInt(result.pixels[px]);
			int g = Byte.toUnsignedInt(result.pixels[px + 1]);
			int b = Byte.toUnsignedInt(result.pixels[px + 2]);
			int a = Byte.toUnsignedInt(result.pixels[px + 3]);

			r = (int) Math.min(255, r * 1.5);
			g = (int) Math.min(255, g * 1.5);
			b = (int) Math.min(255, b * 1.5);
			a = (int) Math.min(255, a / 1.5);

			result.pixels[px] = (byte) r;
			result.pixels[px + 1] = (byte) g;
			result.pixels[px + 2] = (byte) b;
			result.pixels[px + 3] = (byte) a;
		}

		return result;
	}

	@Override
	public boolean isApplyPixel() {
		return true;
	}

	@Override
	public boolean isApplyImage() {
		return true;
	}

}
