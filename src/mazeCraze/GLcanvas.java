package mazeCraze;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.view.MotionEvent;

public class GLcanvas extends GLSurfaceView implements GLSurfaceView.Renderer {
	final static Coordinate START = new Coordinate(0.5f, 0.5f, 0.5f);
	final static Coordinate STARTUP = new Coordinate(0f, 1f, 0f);
	//	private static final String TAG = "rotate";
	private final float[] _mMVPMatrix = new float[16];
	private final float[] _mProjectionMatrix = new float[16];
	private final float[] _mViewMatrix = new float[16];
	private ArrayList<Square> _mSquare = new ArrayList<Square>();
	private float _screenwidth;
	private Direction _desireddirection = Direction.NORTH;
	private Coordinate _desiredposition = START;
	private float _currentdirection = 0.0f;
	private Coordinate _currentposition = START;
	private float _startdirection = 0.0f;
	private Coordinate _startposition = START;
	private float _currentdirtime = 0.0f;
	private float _currentpostime = 0.0f;
	private final float TIMESTEP = 0.2f;
	private final float TIMERESET = -5.0f;

	public GLcanvas(Context context) {
		super(context);
		setEGLContextClientVersion(2);
		setRenderer(this);
		// setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

	@Override
	public boolean onTouchEvent(MotionEvent mouseevent) {

		if (mouseevent.getAction() == MotionEvent.ACTION_DOWN) {
			float x = mouseevent.getX();

			/*
			 * if (x < (_screenwidth / 2.0)) { // Log.v(TAG, "Left"); //
			 * Log.v(TAG, "From: " + _currentdirection); _desireddirection =
			 * _desireddirection.left(); // Log.v(TAG, "To: " +
			 * _desireddirection.name()); } else { // Log.v(TAG, "Right"); //
			 * Log.v(TAG, "From: " + _currentdirection); _desireddirection =
			 * _desireddirection.right(); // Log.v(TAG, "To: " +
			 * _desireddirection.name()); }
			 */

			if (x < _screenwidth / 3.0) {
				_desireddirection = _desireddirection.left();
				_startdirection = _currentdirection;
				_currentdirtime = TIMERESET;
			} else if (x < 2.0 * _screenwidth / 3.0) {
				_desiredposition = _desiredposition.forward(_desireddirection);
				_startposition = _currentposition;
				_currentpostime = TIMERESET;
			} else {
				_desireddirection = _desireddirection.right();
				_startdirection = _currentdirection;
				_currentdirtime = TIMERESET;
			}
		}
		/*
		 * // Set the camera position (View matrix) if(mouseevent.getAction() ==
		 * MotionEvent.ACTION_MOVE) { float x = mouseevent.getX(); float y =
		 * mouseevent.getY(); double angle = 2*Math.PI*x/w;
		 * Matrix.setLookAtM(_mViewMatrix, 0, 0.5f-(float)(0.5
		 * *Math.cos(angle)),0.5f,0.5f-(float)(0.5*Math.sin(angle)),
		 * EYESTARTLOOK.getX(), EYESTARTLOOK.getY(), EYESTARTLOOK.getZ(),
		 * EYESTARTUP.getX(), EYESTARTUP.getY(), EYESTARTUP.getZ());
		 * requestRender(); }
		 */
		return true;
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		_currentposition = logistic(_currentpostime, _desiredposition, _startposition);
		_currentdirection = logistic(_currentdirtime, _desireddirection, _startdirection);
		_currentpostime += TIMESTEP;
		_currentdirtime += TIMESTEP;

		// Draw background color
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

		// Set the camera position (View matrix)
		Coordinate look = _currentposition.getLook(_currentdirection);
		Matrix.setLookAtM(_mViewMatrix, 0, look.getX(), look.getY(), look.getZ(), _currentposition.getX(), _currentposition.getY(),
				_currentposition.getZ(), STARTUP.getX(), STARTUP.getY(), STARTUP.getZ());

		// Calculate the projection and view transformation
		Matrix.multiplyMM(_mMVPMatrix, 0, _mProjectionMatrix, 0, _mViewMatrix, 0);

		// Draw square
		for (Square curr : _mSquare)
			curr.draw(_mMVPMatrix);
	}

	private float logistic(float x, Direction direction, float start) {
		return (float) (start + direction.angleTo(start) / (1 + Math.exp(-x)));
	}

	private Coordinate logistic(float x, Coordinate desired, Coordinate start) {
		return start.add(desired.subtract(start).scale((float) (1 / (1 + Math.exp(-x)))));
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		_screenwidth = width;
		// Adjust the viewport based on geometry changes, such as screen rotation
		GLES20.glViewport(0, 0, width, height);

		float ratio = (float) width / height;

		// this projection matrix is applied to object coordinates in the onDrawFrame() method
		// Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 0.1f, 100f);
		Matrix.perspectiveM(_mProjectionMatrix, 0, 90, ratio, 0.1f, 10f);

		// Set the camera position (View matrix)
		//	Matrix.setLookAtM(_mViewMatrix, 0, EYESTART.getX(), EYESTART.getY(), EYESTART.getZ(), EYESTARTLOOK.getX(),
		//		EYESTARTLOOK.getY(), EYESTARTLOOK.getZ(), EYESTARTUP.getX(), EYESTARTUP.getY(), EYESTARTUP.getZ());
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Set the background frame color
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		_mSquare.add(new Square(0, 1, true, new float[] { 0, 1, 0, 1 }));
		_mSquare.add(new Square(0, 0, true, new float[] { 1, 1, 0, 1 }));
		_mSquare.add(new Square(1, 0, false, new float[] { 1, 0, 0, 1 }));
		_mSquare.add(new Square(0, 0, false, new float[] { 0, 0, 1, 1 }));
	}
}