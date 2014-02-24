package mazeCraze;

public class WallBlock extends Block {

	public WallBlock(int x, int y) {
		for(int i = 0; i < 4; i++)
			_matrices.add( doMath(x, y, i) );
	}
	
	public WallBlock(int[] coords) {
		for(int i = 0; i < 4; i++)
			_matrices.add( doMath(coords[0], coords[1], i) );
	}

	public boolean isTraversible() {
		return false;
	}
	
	public String toString() {
		String str = "X";

		return str;
	}
}
