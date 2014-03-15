package cs275.game.mazeCraze;

import java.util.ArrayList;

public class WallBlock extends Block {

	public boolean isTraversible() {
		return false;
	}

	public String toString() {
		String str = "X";
		return str;
	}

	@Override
	public void generateBuffers(int x, int y) {
		//		Graphic choice = Graphic.values( )[rand.nextInt(Graphic.values( ).length)];
		Graphic choice = Graphic.BRICK;
		int count = choice.getVertexCount();
		choice.appendArrays( getVertexCoords( x, y ), getTextureCoords(), getDrawOrder( count ) );
	}

	@Override
	public ArrayList<Float> getVertexCoords(int x, int y) {
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
	public ArrayList<Float> getTextureCoords() {
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
	public ArrayList<Integer> getDrawOrder(int i) {
		ArrayList<Integer> order = new ArrayList<Integer>();
		for ( int a = 0; a < 10; a++ )
			order.add( ( i + a ) );
		order.add( ( i + 9 ) );
		order.add( ( i + 10 ) );
		return order;
	}
}
