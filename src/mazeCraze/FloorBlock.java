package mazeCraze;

public class FloorBlock extends Block {

	public FloorBlock(Graphics graphics) {
		setGraphics(graphics);
	}

	public boolean isPassable() {
		return true;
	}

	public String toString() {
		String str = ".";

		return str;
	}
}
