package cs275.game.mazeCraze;

import java.util.ArrayList;

public class FloorBlock extends Block {

	private static final String CLASS_NAME = "FloorBlock";

	public FloorBlock() {
	}

	public boolean traversible() {
		return true;
	}

	public String toString() {
		String str = ".";
		return str;
	}

	@Override
	public void generateBuffers(int x, int y) {
		int count = Graphic.DIRT.getVertexCount();
		Graphic.DIRT.appendArrays( genVertexCoords(x, y), genTextureCoords(), genDrawOrder(count) );
	}

	@Override
	protected ArrayList<Float> genVertexCoords(int x, int y) {
		ArrayList<Float> coords = new ArrayList<Float>();
		// @formatter:off
		coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
		coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 1.0f );
		coords.add( x + 1.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
		coords.add( x + 1.0f ); coords.add( -0.5f ); coords.add( y + 1.0f );
		// @formatter:on
		return coords;
	}

	@Override
	protected ArrayList<Float> genTextureCoords() {
		ArrayList<Float> coords = new ArrayList<Float>();
		// @formatter:off
		coords.add( 0.0f ); coords.add( 1.0f );
		coords.add( 0.0f ); coords.add( 0.0f );
		coords.add( 1.0f ); coords.add( 1.0f );
		coords.add( 1.0f ); coords.add( 0.0f );
		// @formatter:on
		return coords;
	}

	@Override
	protected ArrayList<Integer> genDrawOrder(int i) {
		ArrayList<Integer> order = new ArrayList<Integer>();
		for (int a = 0; a < 4; a++)
			order.add(i + a);
		order.add(i + 3);
		order.add(i + 4);
		return order;
	}

	/** This method is needed for cloudmine use */
	@Override
	public String getClassName() {
		return CLASS_NAME;
	}
}
