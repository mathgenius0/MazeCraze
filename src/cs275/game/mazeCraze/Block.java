package cs275.game.mazeCraze;

import java.util.ArrayList;

import com.cloudmine.api.CMObject;

public abstract class Block extends CMObject {
	//	static Random rand = new Random( System.currentTimeMillis() );

	public abstract boolean traversible();

	public abstract String toString();

	public abstract void generateBuffers(int x, int y);

	protected abstract ArrayList<Float> genVertexCoords(int x, int y);

	protected abstract ArrayList<Float> genTextureCoords();

	protected abstract ArrayList<Integer> genDrawOrder(int i);

	@Override
	public abstract String getClassName();
}
