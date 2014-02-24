package mazeCraze;

public class FloorBlock extends Block {
	
	public FloorBlock(int x, int y) {
		int face = 4; //TODO 4 won't work yet. fix block doMath function
		_matrices.add( doMath(x, y, face) );
	}
	
	public FloorBlock(int[] coords) {
		int face = 4; //TODO 4 won't work yet. fix block doMath function
		_matrices.add( doMath(coords[0], coords[1], face) );
	}
	
	public boolean isTraversible() {
		return true;
	}

	public String toString() {
		String str = ".";

		return str;
	}
}
