package cs275.game.mazeCraze;

import java.util.ArrayList;

public abstract class Block/* extends CMObject*/ {
	//	static Random rand = new Random( System.currentTimeMillis() );

	public abstract boolean traversible();

	public abstract String toString();

	public abstract void generateBuffers(int x, int y);

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

	Block get() {
		return _block;
	}
}
