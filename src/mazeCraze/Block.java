package mazeCraze;

public abstract class Block {
	private Graphics _graphics;
	
	public abstract boolean isPassable();
	
	public void setGraphics(Graphics graphics) { _graphics = graphics; }
	public Graphics getGraphics() { return _graphics; };
	
	public abstract String toString();
}
