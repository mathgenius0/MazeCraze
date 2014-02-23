package mazeCraze;

import java.util.ArrayList;

//TODO SERIALIZE
public class Grid {
	private static int GRID_SIZE = 10;
	//TODO recentangular grid => extract the grid lookup into its own function
	private static Graphics GRAPHICS; // TODO how is this assigned? should it be initialized per maze? per block?
	// per maze and then edited per block via block or grid functionality?
	
	//TODO draw functionality for each block in some type of render function
	
	private ArrayList<Block> _blocks;
	
	public Grid() {
		initialize();
	}
	
	private void initialize() {
		for(int i = 0; i < GRID_SIZE*GRID_SIZE; i++)
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
		if(x > GRID_SIZE || y > GRID_SIZE)
			throw exception;
		
		int index = (y-1)*GRID_SIZE + x;
		if( _blocks.get(index).getClass().equals(WallBlock.class) )
			makePassable(x, y);
		else if( _blocks.get(index).getClass().equals(FloorBlock.class) )
			makeImpassable(x, y);
	}
	
	
	/**
	 * Changes the block at x, y to "passable", i.e. changing the
	 * block to type "FloorBlock"
	 */
	public void makePassable(int x, int y) {
		// 1D array of a 2D grid => y*GRID_SIZE (selects row) + x (adds column) //
		int index = (y-1)*GRID_SIZE + x;
		
		Block b = new FloorBlock( _blocks.get(index).getGraphics() ); 
		_blocks.set(index, b);
	}
	
	/**
	 * Changes the block at x, y to "impassable", i.e. changing the
	 * block to type "WallBlock"
	 */
	public void makeImpassable(int x, int y) {
		// 1D array of a 2D grid => y*GRID_SIZE (selects row) + x (adds column) // 
		int index = (y-1)*GRID_SIZE + x;
		
		Block b = new WallBlock( _blocks.get(index).getGraphics() );
		_blocks.set(index, b);
	}
	
	public int getGridSize() {
		return GRID_SIZE;
	}

}
