package mazeCraze;

public class WallBlock extends Block {

	public WallBlock(Graphics graphics) {
		setGraphics(graphics);
	}

	public boolean isPassable() {
		return false;
	}

	public String toString() {
		String str = "X";

		return str;
	}
}
