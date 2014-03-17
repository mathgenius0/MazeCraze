package cs275.game.mazeCraze;

import cs275.game.mazeCraze.Generator.MazeGenerator;
import cs275.game.mazeCraze.Graphics.Graphic;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MazeCreatorView extends View {

	private Paint paint = new Paint();
	private Bitmap _bitmap;
	private Canvas _canvas;
	private Grid _grid;
	
	// Generation math variables // 
	private float _size;
	private float _topX;
	private float _topY;
	private int _viewX;
	private int _viewY;
	int _previousX = -1;
	int _previousY = -1;

	//TODO check for completed maze!! 

 
	public MazeCreatorView(Context context, AttributeSet attrs) {
		super( context, attrs );
		_grid = MazeGenerator.KRUSKAL.generate(25, 25, Graphic.BRICK, Graphic.DIRT);	
	}

	/**
	 * Has a grid of size sizeX x sizeY with walls as designated by the two graphic parameters.
	 */
	public MazeCreatorView(Context context, int sizeX, int sizeY, Graphic walls, Graphic floors, String name) {
		super(context);
		_grid = new Grid( sizeX, sizeY, walls, floors, name );
	}

	/**
	 * It has to instantiate independent from its initialization, i.e. the
	 * details regarding its size are added some delay after it is initially created. 
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged( w, h, oldw, oldh );
		_viewX = w;
		_viewY = h;
		_size = Math.min( w / _grid.getGridSizeX(), h / _grid.getGridSizeY() );
		_bitmap = Bitmap.createBitmap( Math.round( _size * _grid.getGridSizeX() ), Math.round( _size * _grid.getGridSizeY() ),
				Bitmap.Config.ARGB_8888 );
		_canvas = new Canvas( _bitmap );
		updateBitmap();
	}

	/**
	 * This function actually draws (or displays) this view on the menu screen (a.k.a. canvas).
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw( canvas );
		_topX = Math.abs( _size * _grid.getGridSizeX() - _viewX ) / 2;
		_topY = Math.abs( _size * _grid.getGridSizeY() - _viewY ) / 2;
		canvas.drawBitmap( _bitmap, _topX, _topY, paint );
	}

	/**
	 * This controls the colors of the blocks.
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int selectx = (int) Math.floor( ( event.getX() - _topX ) / _size );
		int selecty = (int) Math.floor( ( event.getY() - _topY ) / _size );
		
		// If clicked, toggle block //
		if ( event.getAction() == MotionEvent.ACTION_DOWN ) { //TODO check this comment
			
			try {
				_grid.toggleBlock( selectx, selecty );
			} catch ( Exception e ) {
			}
			updateBitmap();
		}
		
		// If moved over, still toggle block //
		if ( event.getAction() == MotionEvent.ACTION_MOVE ) {
			if ( _previousX != selectx || _previousY != selecty ) {
				try {
					_grid.toggleBlock( selectx, selecty );
				} catch ( Exception e ) {
				}
				updateBitmap(); //TODO find out why this is repeated twice.... can this be one if statement?
			}
		}
		_previousX = selectx;
		_previousY = selecty;
		invalidate();
		return true;
	}

	/**
	 * 
	 */
	public void updateBitmap() {
		for ( int y = 0; y < _grid.getGridSizeY(); y++ ) {
			for ( int x = 0; x < _grid.getGridSizeX(); x++ ) {
				paint.setColor( _grid.getBlock(x, y).getColor() );
				_canvas.drawRect( x * _size, y * _size, ( x + 1 ) * _size, ( y + 1 ) * _size, paint );
			}
		}
	}

	public Grid getGrid() {
		return _grid;
	}
}
