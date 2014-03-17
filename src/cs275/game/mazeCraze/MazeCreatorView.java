package cs275.game.mazeCraze;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
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
	private float _size;
	private float _topX;
	private float _topY;
	private int _viewX;
	private int _viewY;
	int _previousX = -1;
	int _previousY = -1;
	private Context _context;
 
	public MazeCreatorView(Context context, AttributeSet attrs) {
		super( context, attrs );
		_context = context;
		
		ObjectModificationResponseCallback responseCallback = new ObjectModificationResponseCallback() {
			public void onCompletion(ObjectModificationResponse response) {
				if ( response.wasSuccess() )
					Toast.makeText( _context, "Grid Saved", Toast.LENGTH_SHORT ).show();
			}

			public void onFailure(Throwable e, String msg) {
				Log.v( "cloudmine", "Failed to save grid", e );
			}
		};
		
		_grid = new MazeGenerator().KruskalGenerate( 11, 11 );
		if ( !isInEditMode() )
			_grid.save(responseCallback);
		_gridX = _grid.getGridSizeX();
		_gridY = _grid.getGridSizeY();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged( w, h, oldw, oldh );
		_viewX = w;
		_viewY = h;
		_size = Math.min( w / _gridX, h / _gridY );
		_bitmap = Bitmap.createBitmap( Math.round( _size * _gridX ), Math.round( _size * _gridY ),
				Bitmap.Config.ARGB_8888 );
		_canvas = new Canvas( _bitmap );
		updateBitmap();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw( canvas );
		_topX = Math.abs( _size * _gridX - _viewX ) / 2;
		_topY = Math.abs( _size * _gridY - _viewY ) / 2;
		canvas.drawBitmap( _bitmap, _topX, _topY, paint );
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int selectx = (int) Math.floor( ( event.getX() - _topX ) / _size );
		int selecty = (int) Math.floor( ( event.getY() - _topY ) / _size );
		if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
			try {
				_grid.toggleBlock( selectx, selecty );
			} catch ( Exception e ) {
			}
			updateBitmap();
		}
		if ( event.getAction() == MotionEvent.ACTION_MOVE ) {
			if ( _previousX != selectx || _previousY != selecty ) {
				try {
					_grid.toggleBlock( selectx, selecty );
				} catch ( Exception e ) {
				}
				updateBitmap();
			}
		}
		_previousX = selectx;
		_previousY = selecty;
		invalidate();
		return true;
	}

	public void updateBitmap() {
		for ( int y = 0; y < _gridY; y++ ) {
			for ( int x = 0; x < _gridX; x++ ) {
				if ( _grid.isTraversible( x, y ) )
					paint.setColor( Color.GREEN );
				else
					paint.setColor( Color.RED );
				_canvas.drawRect( x * _size, y * _size, ( x + 1 ) * _size, ( y + 1 ) * _size, paint );
			}
		}
	}

}
