package cs275.game.mazeCraze;

import java.util.ArrayList;

public class FloorBlock extends Block {

	public boolean isTraversible() {
		return true;
	}

	public String toString() {
		String str = ".";
		return str;
	}

	@Override
	public void generateBuffers(int x, int y) {
		int count = Graphic.DIRT.getVertexCount();
		Graphic.DIRT.appendArrays( getVertexCoords( x, y ), getTextureCoords(), getDrawOrder( count ) );
	}

	@Override
	public ArrayList<Float> getVertexCoords(int x, int y) {
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
	public ArrayList<Float> getTextureCoords() {
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
	public ArrayList<Integer> getDrawOrder(int i) {
		ArrayList<Integer> order = new ArrayList<Integer>();
		for ( int a = 0; a < 4; a++ )
			order.add( ( i + a ) );
		order.add( ( i + 3 ) );
		order.add( ( i + 4 ) );
		return order;
	}
}
