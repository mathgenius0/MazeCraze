package mazeCraze;

import android.opengl.Matrix;

public class Camera {
	private final float TIMESTEP = 0.2f;
	private final float TIMERESET = -5.0f;
	private final Orientation START = new Orientation(0.5f, 0.5f, 0.5f, 0.0f);
	final static Orientation STARTUP = new Orientation(0f, 1f, 0f, 0.0f);


	private Orientation _desired = START;
	private Orientation _current = START;
	private Orientation _start = START;
	
	private float _currentDirectionTime = 0.0f;
	private float _currentPositionTime = 0.0f;
	
	// Set the camera position (View matrix)
	public float[] smoothRotation() {
		
		_current.logistic(_currentDirectionTime, _currentPositionTime, _desired, _start);
		_currentPositionTime += TIMESTEP;
		_currentDirectionTime += TIMESTEP;
		
		float[] mViewMatrix = new float[16];
		Orientation look = _current.getLook();
		Matrix.setLookAtM( mViewMatrix, 0, look.getX(), look.getY(), look.getZ(), 
							_current.getX(), _current.getY(), _current.getZ(), 
							STARTUP.getX(), STARTUP.getY(), STARTUP.getZ() );
		return mViewMatrix;
		
	}
	
	public void right() {
		_desired.right();
		_start.setPosition(_current);
		_currentDirectionTime = TIMERESET;
	}

	public void forward() {
		_desired.forward();
		_start.setPosition(_current);
		_currentPositionTime = TIMERESET;
	}

	public void left() {
		_desired.left();
		_start.setPosition(_current);
		_currentDirectionTime = TIMERESET;
	}
	
	//taken from:
//	public void right() {
//		_desireddirection = _desireddirection.right();
//		_startdirection = _currentdirection;
//		_currentdirtime = TIMERESET;
//	}
//
//	public void forward() {
//		_desiredposition = _desiredposition.forward(_desireddirection);
//		_startposition = _currentposition;
//		_currentpostime = TIMERESET;
//	}
//
//	public void left() {
//		_desireddirection = _desireddirection.left();
//		_startdirection = _currentdirection;
//		_currentdirtime = TIMERESET;
//	}
}
