package cs275.game.mazeCraze.Block;

import java.util.ArrayList;

public class WallBlock extends Block {

	//	protected static final String CLASS_NAME = "WallBlock";

	public WallBlock() {
	}

	public boolean traversible() {
		return false;
	}

	public String toString() {
		String str = "X";
		return str;
	}

	@Override
	protected ArrayList<Float> genVertexCoords(int x, int y) {
		ArrayList<Float> coords = new ArrayList<Float>();
		// @formatter:off
		coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
		coords.add( x + 0.0f ); coords.add( 0.5f ); coords.add( y + 0.0f );
		coords.add( x + 1.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
		coords.add( x + 1.0f ); coords.add( 0.5f ); coords.add( y + 0.0f );
		coords.add( x + 1.0f ); coords.add( -0.5f ); coords.add( y + 1.0f );
		coords.add( x + 1.0f ); coords.add( 0.5f ); coords.add( y + 1.0f );
		coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 1.0f );
		coords.add( x + 0.0f ); coords.add( 0.5f ); coords.add( y + 1.0f );
		coords.add( x + 0.0f ); coords.add( -0.5f ); coords.add( y + 0.0f );
		coords.add( x + 0.0f ); coords.add( 0.5f ); coords.add( y + 0.0f );
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
		coords.add( 2.0f ); coords.add( 1.0f );
		coords.add( 2.0f ); coords.add( 0.0f );
		coords.add( 3.0f ); coords.add( 1.0f );
		coords.add( 3.0f ); coords.add( 0.0f );
		coords.add( 4.0f ); coords.add( 1.0f );
		coords.add( 4.0f ); coords.add( 0.0f );
		// @formatter:on
		return coords;
	}

	@Override
	public ArrayList<Integer> genDrawOrder(int i) {
		ArrayList<Integer> order = new ArrayList<Integer>();
		for ( int a = 0; a < 10; a++ )
			order.add( i + a );
		order.add( i + 9 );
		order.add( i + 10 );
		return order;
	}

	//	/** This method is needed for cloudmine use */
	//	@Override
	//	public String getClassName() {
	//		return CLASS_NAME;
	//	}
}
