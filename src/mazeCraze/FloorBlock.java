package mazeCraze;

public class FloorBlock extends Block {
	
	private Square _floor;

	public FloorBlock(Graphics graphics) {
		setGraphics(graphics);
	}

	public boolean isTraversible() {
		return true;
	}

	public String toString() {
		String str = ".";

		return str;
	}
}
