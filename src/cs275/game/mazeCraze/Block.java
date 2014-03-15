package cs275.game.mazeCraze;

import java.util.ArrayList;

public abstract class Block {
	//	static Random rand = new Random( System.currentTimeMillis() );

	public abstract boolean isTraversible();

	public abstract String toString();

	public abstract ArrayList<Float> getVertexCoords(int x, int y);

	public abstract ArrayList<Float> getTextureCoords();

	public abstract ArrayList<Integer> getDrawOrder(int i);

	public abstract void generateBuffers(int x, int y);
}
