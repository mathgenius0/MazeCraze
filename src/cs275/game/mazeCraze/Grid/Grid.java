package cs275.game.mazeCraze.Grid;

import java.util.ArrayList;

import com.cloudmine.api.CMObject;

import cs275.game.mazeCraze.Graphics.Graphic;

public class Grid extends CMObject {
	public static final String CLASS_NAME = "Grid";
	private String _name;
	private Graphic _wallstyle;
	private Graphic _floorstyle;
	private int _gridSizeX;
	private int _gridSizeY;
	private ArrayList<ArrayList<Block> > _blocks;

	public Grid() {
	}

	public Grid(int x, int y) {
		_gridSizeX = x;
		_gridSizeY = y;
		_blocks = new ArrayList<ArrayList<Block> >();
		initialize();
	}

	public Grid(int x, int y, Graphic wallstyle, Graphic floorstyle) {
		this( x, y );
		_wallstyle = wallstyle;
		_floorstyle = floorstyle;
	}

	public Grid(int x, int y, Graphic wallstyle, Graphic floorstyle, String name) {
		this( x, y, wallstyle, floorstyle );
		_name = name;
	}

	public void initialize() {
		for ( int y = 0; y < _gridSizeY; y++ ) {
			ArrayList<Block> row = new ArrayList<Block>();
			for ( int x = 0; x < _gridSizeX; x++ )
				row.add( Block.WALL );
			_blocks.add( row );
		}
		
		initializeTerminals();
	}
	
	public void initializeTerminals() {
		setBlock(0, 0, Block.ENTRANCE);
		setBlock( (_gridSizeX-1), (_gridSizeY-1), Block.EXIT);
	}

	/**
	 * If a block is selected, it's type should change.
	 */
	public void toggleBlock(int x, int y) throws UnsupportedOperationException {
		UnsupportedOperationException exception = new UnsupportedOperationException( "Cannot toggle block at coords: "
				+ x + ", " + y );
		
		if ( x < 0 || y < 0 )
			throw exception;
		if ( x >= _gridSizeX || y >= _gridSizeY )
			throw exception;
		
		// Don't have to worry that this will be out of bounds because of above checks //
		Block current = getBlock(x, y);
		if ( current != Block.ENTRANCE && current != Block.EXIT ) {
			// Entrance and Exit are not toggle-able so skip if it's one of these //
			if ( isTraversible(x, y) )
				setBlock(x, y, Block.WALL);
			else
				setBlock(x, y, Block.FLOOR);
		}

	}

	/**
	 * TODO
	 */
	public boolean isTraversible(int x, int y) {
		boolean traversible;
		
		if ( x < 0 || y < 0 )
			traversible = false;
		else if ( x >= _gridSizeX || y >= _gridSizeY )
			traversible = false;
		if ( getBlock(x, y) == Block.FLOOR ) {
			traversible = true;
		} else
			traversible = false;
		
		return traversible;
	}

	public ArrayList<ArrayList<Block> > getBlocks() { return _blocks; }
	public void setBlocks(ArrayList<ArrayList<Block> > blocks) { _blocks = blocks; }

	public int getGridSizeX() { return _gridSizeX; }
	public void setGridSizeX(int x) { _gridSizeX = x; }

	public int getGridSizeY() { return _gridSizeY; }
	public void setGridSizeY(int y) { _gridSizeY = y; }

	public String getName() { return _name; }
	public void setName(String name) { _name = name; }

	public Graphic getWallStyle() { return _wallstyle; }
	public void setWallStyle(Graphic wallstyle) { _wallstyle = wallstyle; }

	public Graphic getFloorStyle() { return _floorstyle; }
	public void setFloorStyle(Graphic floorstyle) { _floorstyle = floorstyle; }
	
	public Block getBlock(int x, int y) { return _blocks.get(y).get(x); }
	public void setBlock(int x, int y, Block b) { _blocks.get(y).set(x, b); }

	
	public String toString() {
		String str = "";
		for ( ArrayList<Block> row : _blocks ) {
			for ( Block b : row ) {
				str += b;
				str += " ";
			}
			str += "\n";
		}
		return str;
	}

	public void generateBuffers() {
		for ( int y = 0; y < _gridSizeY; y++ )
			for ( int x = 0; x < _gridSizeX; x++ ) {
				Block current = _blocks.get( y ).get( x );
				switch ( current ) {
				case WALL:
					current.generateBuffers( _wallstyle, x, y );
					break;
				case FLOOR:
					current.generateBuffers( _floorstyle, x, y );
					break;
				case ENTRANCE://TODO how should this differ?
					current.generateBuffers( _floorstyle, x, y );
					break;
				case EXIT://TODO how does this differ?
					current.generateBuffers( _floorstyle, x, y );
					break;
				}

			}
	}

	/**
	 * This method is needed for cloudmine use
	 */
	@Override
	public String getClassName() {
		return CLASS_NAME;
	}
}
