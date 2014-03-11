package cs275.game.mazeCraze;

public class FloorBlock extends Block {

	public FloorBlock(int x, int y) {
		int face = 4;
		_matrices.add( computeSideTransforms( x, y, face ) );
	}

	public FloorBlock(int[] coords) {
		int face = 4;
		_matrices.add( computeSideTransforms( coords[0], coords[1], face ) );
	}

	public boolean isTraversible() {
		return true;
	}

	public String toString() {
		String str = ".";

		return str;
	}
}
