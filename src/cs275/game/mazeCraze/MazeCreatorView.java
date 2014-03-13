package cs275.game.mazeCraze;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.cloudmine.api.rest.callbacks.ObjectModificationResponseCallback;
import com.cloudmine.api.rest.response.ObjectModificationResponse;

public class MazeCreatorView extends View {

	private Paint paint = new Paint();
	private Bitmap _bitmap;
	private Canvas _canvas;
	private Grid _grid;
	private int _gridX;
	private int _gridY;
	private float size;
	private float _topX;
	private float _topY;
	int _previousX = -1;
	int _previousY = -1;
	private Context _context;

	public MazeCreatorView(Context context) {
		super( context );
		_context = context;
		_grid = new MazeGenerator().KruskalGenerate( 11, 11 );
		_grid.save( new ObjectModificationResponseCallback() {
			public void onCompletion(ObjectModificationResponse response) {
				if ( response.wasSuccess() )
					Toast.makeText( _context, "Grid Saved", Toast.LENGTH_SHORT ).show();
			}

			public void onFailure(Throwable e, String msg) {
				Log.v( "cloudmine", "Failed to save grid", e );
			}
		} );
		_gridX = _grid.getGridSizeX();
		_gridY = _grid.getGridSizeY();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int mousex = (int) event.getX();
		int mousey = (int) event.getY();
		int selectx = Math.round( ( mousex - _topX ) / size );
		int selecty = Math.round( ( mousey - _topY ) / size );
		if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
			try {
				_grid.toggleBlock( selectx, selecty );
			} catch ( Exception e ) {
			}
			updateBitmap();
			invalidate();
		}
		if ( event.getAction() == MotionEvent.ACTION_MOVE ) {
			if ( _previousX != selectx || _previousY != selecty ) {
				try {
					_grid.toggleBlock( selectx, selecty );
				} catch ( Exception e ) {
				}
				updateBitmap();
				invalidate();
			}
		}
		_previousX = selectx;
		_previousY = selecty;
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw( canvas );
		float x = canvas.getWidth();
		float y = canvas.getHeight();
		size = Math.min( x / _gridX, y / _gridY );
		if ( _bitmap == null ) {
			_bitmap = Bitmap.createBitmap( Math.round( size * _gridX ), Math.round( size * _gridY ), Bitmap.Config.ARGB_8888 );
			_canvas = new Canvas( _bitmap );
			updateBitmap();
		}
		_topX = ( Math.max( size * _gridX, x ) - Math.min( size * _gridX, x ) ) / 2;
		_topY = ( Math.max( size * _gridY, y ) - Math.min( size * _gridY, y ) ) / 2;
		canvas.drawBitmap( _bitmap, _topX, _topY, paint );
	}

	public void updateBitmap() {
		for ( int y = 0; y < _gridY; y++ ) {
			for ( int x = 0; x < _gridX; x++ ) {
				if ( _grid.isTraversible( x, y ) )
					paint.setColor( Color.GREEN );
				else
					paint.setColor( Color.RED );
				_canvas.drawRect( x * size, y * size, ( x + 1 ) * size, ( y + 1 ) * size, paint );
			}
		}
	}

}
