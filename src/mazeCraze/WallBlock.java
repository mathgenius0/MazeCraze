package mazeCraze;

import java.util.ArrayList;

public class WallBlock extends Block {
	
	private ArrayList<Square> _walls;

	public WallBlock(Graphics graphics) {
		setGraphics(graphics);
	}

	public boolean isTraversible() {
		return false;
	}

	public String toString() {
		String str = "X";

		return str;
	}
}
