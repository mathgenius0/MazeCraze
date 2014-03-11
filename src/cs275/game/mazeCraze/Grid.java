package cs275.game.mazeCraze;

import java.util.ArrayList;

import android.util.Log;

import com.cloudmine.api.CMObject;

public class Grid extends CMObject {
	MazeGenerator _generator = new MazeGenerator();
	
	private int _gridSizeX;
	private int _gridSizeY;
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

	public void initialize() {
		for (int i = 0; i < _gridSizeX * _gridSizeY; i++) {
			Block b = new FloorBlock(getBlockCoords(i));
			_blocks.add(b);
		}
	}

	public void initializeRandom() {
		_blocks.clear();
		_generator.DFSGenerate(_gridSizeX, _gridSizeY);
		
		Log.v("maze", toString());
	}

//	public void toggleBlock(int i) {
//		int[] coords = getBlockCoords(i);
//		toggleBlock(coords[0], coords[1]);
//	} TODO get rid of?

	/**
	 * If a block is selected, it's type should change.
	 */
	public void toggleBlock(int x, int y) throws UnsupportedOperationException {
		UnsupportedOperationException exception = new UnsupportedOperationException(
				"Cannot toggle block at coords: " + x + ", " + y);
		if (x < 0 || y < 0)
			throw exception;
		if (x >= _gridSizeX || y >= _gridSizeY)
			throw exception;

		Block b;
		int i = getBlockIndex(x, y);

		if (_blocks.get(i).isTraversible()) {
			b = new WallBlock(x, y);
		} else
			b = new FloorBlock(x, y);

		_blocks.set(i, b);
	}

	/**
	 * TODO
	 */
	public boolean isTraversible(int x, int y) {
		boolean traversible;
		if (x < 0 || y < 0)
			traversible = false;
		else if (x >= _gridSizeX || y >= _gridSizeY)
			traversible = false;
		else
			traversible = _blocks.get(getBlockIndex(x, y)).isTraversible();
		return traversible;
	}

	public int getBlockIndex(int x, int y) {
		// 1D array of a 2D grid => y*GRID_SIZE_X (selects row) + x (adds
		// column) //
		return (y * _gridSizeX + x);
	}

	public int[] getBlockCoords(int index) {
		int[] coords = new int[2];
		coords[0] = index % _gridSizeX;
		coords[1] = index / _gridSizeX;

		return coords;
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

	public int getGridArea() {
		return _gridSizeX * _gridSizeY;
	}

	public String toString() {
		String str = "";

		int counter = 0;
		for (Block b : _blocks) {
			str += b;
			counter++;

			if (counter == _gridSizeX) {
				str += "\n";
				counter = 0;
			} else
				str += " ";
		}
		return str;
	}

	public void draw(float[] matrix) {
		for (Block b : _blocks) {
			b.draw(matrix);
		}
	}
}
