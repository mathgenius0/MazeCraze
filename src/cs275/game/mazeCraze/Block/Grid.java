package cs275.game.mazeCraze.Block;

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
	private ArrayList<ArrayList<Blocks>> _blocks;

	public Grid() {
	}

	public Grid(int x, int y) {
		_gridSizeX = x;
		_gridSizeY = y;
		_blocks = new ArrayList<ArrayList<Blocks>>();
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
			ArrayList<Blocks> row = new ArrayList<Blocks>();
			for ( int x = 0; x < _gridSizeX; x++ )
				row.add( Blocks.WALL );
			_blocks.add( row );
		}
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

		if ( _blocks.get( y ).get( x ).get().traversible() ) {
			_blocks.get( y ).set( x, Blocks.WALL );
		} else
			_blocks.get( y ).set( x, Blocks.FLOOR );

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
		else
			traversible = _blocks.get( y ).get( x ).get().traversible();
		return traversible;
	}

	public ArrayList<ArrayList<Blocks>> getBlocks() {
		return _blocks;
	}

	public void setBlocks(ArrayList<ArrayList<Blocks>> blocks) {
		_blocks = blocks;
	}

	public int getGridSizeX() {
		return _gridSizeX;
	}

	public void setGridSizeX(int x) {
		_gridSizeX = x;
	}

	public int getGridSizeY() {
		return _gridSizeY;
	}

	public void setGridSizeY(int y) {
		_gridSizeY = y;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public Graphic getWallStyle() {
		return _wallstyle;
	}

	public void setWallStyle(Graphic wallstyle) {
		_wallstyle = wallstyle;
	}

	public Graphic getFloorStyle() {
		return _floorstyle;
	}

	public void setFloorStyle(Graphic floorstyle) {
		_floorstyle = floorstyle;
	}

	public String toString() {
		String str = "";
		for ( ArrayList<Blocks> row : _blocks ) {
			for ( Blocks b : row ) {
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
				Blocks curr = _blocks.get( y ).get( x );
				switch ( curr ) {
				case WALL:
					curr.get().generateBuffers( _wallstyle, x, y );
					break;
				case FLOOR:
					curr.get().generateBuffers( _floorstyle, x, y );
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
