package mazeCraze;

import java.util.ArrayList;

import android.opengl.Matrix;

public abstract class Block {
	protected ArrayList<float[] > _matrices = new ArrayList<float[]>();

	public abstract boolean isTraversible();
	
	protected float[] doMath(int x, int y, int face) { // TODO what about 4 (floor) and 5 (ceiling)?
		float[] matrix = new float[16];
		Matrix.setIdentityM(matrix, 0);
		Matrix.translateM(matrix, 0, -0.5f, 0f, -0.5f);
		float[] temp = new float[16];
		Matrix.setIdentityM(temp, 0);
		Matrix.setRotateM(temp, 0, face*90, 0, 1, 0);
		Matrix.multiplyMM(matrix, 0, temp, 0, matrix, 0);
		Matrix.setIdentityM(temp, 0);
		Matrix.translateM(temp, 0, x+0.5f, 0f, y+0.5f);
		Matrix.multiplyMM(matrix, 0, temp, 0, matrix, 0);
		
		return matrix;
	}
	
	public void draw(float[] screenDisplay) {
		float[] temp = new float[16];
		for(float[] blockEdge : _matrices) {
			// Combine blockEdge (i.e. wall or floor) with screenDisplay/camera matrix //
			Matrix.multiplyMM(temp, 0, screenDisplay, 0, blockEdge, 0);
			Graphic.BRICK.draw(temp);
		}
	}
	
	public ArrayList<float[]> getMatrices() { return _matrices; }
	public void setMatrices(ArrayList<float[]> matrices) { _matrices = matrices; }
	
	public abstract String toString();
}
