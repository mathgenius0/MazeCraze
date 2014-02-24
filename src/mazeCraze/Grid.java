package mazeCraze;

import java.util.ArrayList;

public class Grid /*implements CMObject TODO*/ {
	private int _gridSizeX;
	private int _gridSizeY;
	private static Graphics GRAPHICS = Graphics.STUB; // TODO how is this assigned? should it be initialized per maze? per block?
	// per maze and then edited per block via block or grid functionality?
	
	//TODO draw functionality for each block in some type of render function
	
	private ArrayList<Block> _blocks = new ArrayList<Block>();
	
	public Grid() {
		_gridSizeX = 10;
		_gridSizeY = 10;
		initialize();
	}
	
	public Grid(int x, int y) {
		_gridSizeX = x;
		_gridSizeY = y;
		initialize();
	}
	
	private void initialize() {
		for(int i = 0; i < _gridSizeX*_gridSizeY; i++)
			_blocks.add( new WallBlock(GRAPHICS) );
	}
	
	private void initializeRandom() {
		//TODO figure out how to initialize a random array
	}
	
	/**
	 * If a block is selected, it's type should change.
	 */
	public void toggleBlock(int x, int y) throws UnsupportedOperationException {
		UnsupportedOperationException exception = new UnsupportedOperationException("Cannot toggle block at coords: " + x + ", " + y);
		if(x < 0 || y < 0)
			throw exception;
		if(x >= _gridSizeX || y >= _gridSizeY)
			throw exception;
		
		Block b;
		int i = getBlockIndex(x, y);
		
		if( _blocks.get(i).isTraversible() )
			b = new WallBlock( _blocks.get(i).getGraphics() );
		else
			b = new FloorBlock( _blocks.get(i).getGraphics() );
		
		_blocks.set(i, b);
	}
	
	public int getBlockIndex(int x, int y) {
		// 1D array of a 2D grid => y*GRID_SIZE_X (selects row) + x (adds column) //
		return (y*_gridSizeX + x);
	}
	
	public int getGridSizeX() { return _gridSizeX; }
	public void setGridSizeX(int x) { _gridSizeX = x; }
	
	public int getGridSizeY() { return _gridSizeY; }
	public void setGridSizeY(int y) { _gridSizeY = y; }
	
	public int getGridArea() { return _gridSizeX*_gridSizeY; }
	
	public String toString() {
		String str = "";
		
		int counter = 0;
		for(Block b : _blocks) {
			str += b;
			counter++;
			
			if( counter == _gridSizeX ) {
				str += "\n";
				counter = 0;
			} else
				str += " ";
				
		}
		
		return str;
	}
}
