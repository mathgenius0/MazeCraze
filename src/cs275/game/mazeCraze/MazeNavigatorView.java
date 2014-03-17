package cs275.game.mazeCraze;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import cs275.game.mazeCraze.Generator.MazeGenerator;
import cs275.game.mazeCraze.Graphics.Camera;
import cs275.game.mazeCraze.Graphics.Graphic;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

public class MazeNavigatorView extends GLSurfaceView implements
		GLSurfaceView.Renderer {

	private static Input myInput = new Input( "ONTOUCH" );
	private static Camera myCamera = new Camera();
	private static Grid myGrid;

	private final float[] _mProjectionMatrix = new float[16];
	private float[] _mMVPMatrix = new float[16];
	private Context _context;

	public MazeNavigatorView(Context context) {
		super( context );
	}

	public MazeNavigatorView(Context context, MazeGenerator generator, int sizeX, int sizeY, 
			Graphic wallstyle, Graphic floorstyle) {
		super( context );
		_context = context;
		myGrid = generator.generate( sizeX, sizeY, wallstyle, floorstyle );
		// Log.v( "maze", myGrid.toString() );
		initialize();
	}

	public MazeNavigatorView(Context context, Grid grid) {
		super( context );
		_context = context;
		myGrid = grid;

		initialize();
	}

	private void initialize() {
		setEGLContextClientVersion( 2 );
		setRenderer( this );
		setRenderMode( GLSurfaceView.RENDERMODE_CONTINUOUSLY );

		setOnTouchListener( myInput );
		// TODO setAccelerometerListener(myInput);
	}

	public void left() {
		myCamera.left();
	}

	public void right() {
		myCamera.right();
	}

	public void forward() {
		myCamera.forward();
	}

	public static float[] getLook() {
		return myCamera.smoothRotation();
	}

	public static boolean checkGrid(int x, int y) {
		return myGrid.isTraversible( x, y );
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// Draw background color //
		GLES20.glClear( GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT );

		// Combine projection and camera rotation for screen display matrix //
		Matrix.multiplyMM( _mMVPMatrix, 0, _mProjectionMatrix, 0, MazeNavigatorView.getLook(), 0 );

		for ( Graphic curr : Graphic.values() )
			curr.draw( _mMVPMatrix );
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		myGrid.generateBuffers();
		// Load the texture for each graphic
		for ( Graphic curr : Graphic.values() ) {
			curr.loadGLTexture( _context );
			curr.initializeBuffers();
		}
		Graphic.loadShaderCode( _context );
		// Enable Depth Buffering //
		GLES20.glEnable( GLES20.GL_DEPTH_TEST );
		// Set the background frame color //
		GLES20.glClearColor( 0.0f, 0.0f, 0.0f, 1.0f );
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Adjust the viewport based on geometry changes, such as screen rotation //
		GLES20.glViewport( 0, 0, width, height );

		float ratio = (float) width / height;

		// Projection matrix is applied to object coordinates in the onDrawFrame() method //
		Matrix.perspectiveM( _mProjectionMatrix, 0, 90, ratio, 0.1f, 5f );
	}
}