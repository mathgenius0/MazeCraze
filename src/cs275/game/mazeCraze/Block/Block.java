package cs275.game.mazeCraze.Block;

import java.util.ArrayList;

import cs275.game.mazeCraze.Graphics.Graphic;

public abstract class Block/* extends CMObject*/ {
	//	static Random rand = new Random( System.currentTimeMillis() );

	public abstract boolean traversible();

	public abstract String toString();

	public void generateBuffers(Graphic graphic, int x, int y) {
		int count = graphic.getVertexCount();
		graphic.appendArrays( genVertexCoords( x, y ), genTextureCoords(), genDrawOrder( count ) );
	}

	protected abstract ArrayList<Float> genVertexCoords(int x, int y);

	protected abstract ArrayList<Float> genTextureCoords();

	protected abstract ArrayList<Integer> genDrawOrder(int i);

//	@Override
//	public abstract String getClassName();
}

enum Blocks {
	FLOOR(new FloorBlock()), WALL(new WallBlock());
	Block _block;

	Blocks(Block block) {
		_block = block;
	}

	public Block get() {
		return _block;
	}
	
	public String toString()
	{
		return _block.toString();
	}
}
