package cs275.game.mazeCraze;

import java.util.ArrayList;

import com.cloudmine.api.CMObject;

public class Grid extends CMObject {
	private int _gridSizeX;
	private int _gridSizeY;
	
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
		for(int i = 0; i < _gridSizeX*_gridSizeY; i++) {
			Block b = new FloorBlock( getBlockCoords(i) );
			_blocks.add(b);
		}
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
		
		if( _blocks.get(i).isTraversible() ) {
			b = new WallBlock(x, y);
		}
		else
			b = new FloorBlock(x, y);
		
		_blocks.set(i, b);
	}
	public boolean isTraversible(int x, int y)
	{
		if(x < 0 || y < 0)
			return false;
		if(x >= _gridSizeX || y >= _gridSizeY)
			return false;
		return _blocks.get(getBlockIndex(x,y)).isTraversible();
	}
	
	public int getBlockIndex(int x, int y) {
		// 1D array of a 2D grid => y*GRID_SIZE_X (selects row) + x (adds column) //
		return (y*_gridSizeX + x);
	}
	
	public int[] getBlockCoords(int index) {
		int[] coords = new int[2];
		coords[0] = index % _gridSizeX;
		coords[1] = index / _gridSizeX;
				
		return coords;
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

	public void drawAllBlocks(float[] matrix) {
		for (Block b : _blocks) {
			b.drawAllSides(matrix);
		}
	}
}
