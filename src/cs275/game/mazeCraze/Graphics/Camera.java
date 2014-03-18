package cs275.game.mazeCraze.Graphics;

import android.opengl.Matrix;

public class Camera {
	public Camera() {
		super();
		_desired = new Orientation( START );
		_current = new Orientation( START );
		_start = new Orientation( START );
	}

	private final float TIMESTEP = 0.2f;
	private final float TIMERESET = -5.0f;
	private final Orientation START = new Orientation( 0.5f, 0.0f, 0.5f, 0.0f );
	final static Orientation STARTUP = new Orientation( 0f, 1f, 0f, 0.0f );

	private Orientation _desired;
	private Orientation _current;
	private Orientation _start;

	private float _currentDirectionTime = 0.0f;
	private float _currentPositionTime = 0.0f;
	private float[] _mViewMatrix = new float[16];

	// Set the camera position (View matrix)
	public float[] smoothRotation() {
		// smoothly move the camera to the desired location //
		_current.logistic( _currentDirectionTime, _currentPositionTime, _desired, _start );
		_currentPositionTime += TIMESTEP;
		_currentDirectionTime += TIMESTEP;
		Matrix.setLookAtM( _mViewMatrix, 0, _current.getLookX(), _current.getLookY(), _current.getLookZ(),
				_current.getX(), _current.getY(), _current.getZ(), STARTUP.getX(), STARTUP.getY(), STARTUP.getZ() );
		return _mViewMatrix;
	}
	
	// update the desired location based on the user input // 
	public void right() {
		_desired.right();
		_start.setRotation( _current );
		_currentDirectionTime = TIMERESET;
	}

	public void forward() {
		if ( _desired.checkForward() ) {
			_desired.forward();
			_start.setPosition( _current );
			_currentPositionTime = TIMERESET;
		}
	}

	public void left() {
		_desired.left();
		_start.setRotation( _current );
		_currentDirectionTime = TIMERESET;
	}
}
