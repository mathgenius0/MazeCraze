package cs275.game.mazeCraze;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

public class MyRenderer implements GLSurfaceView.Renderer {
	private final float[] _mProjectionMatrix = new float[16];
	private Context _context;
	private Grid _grid;
	public MyRenderer(Context context, Grid grid) {
		super();
		_context = context;
		_grid = grid;
	}

	@Override
	public void onDrawFrame(GL10 gl) {

		// Draw background color //
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

		float[] transformedForScreenDisplay = convertToScreenMatrix();
		_grid.drawAllBlocks(transformedForScreenDisplay);
	}

	/**
	 * Calculate the projection and view transformation.
	 * 
	 * @return
	 */
	private float[] convertToScreenMatrix() {
		float[] _mMVPMatrix = new float[16];
		// Combine projection and camera rotation for screen display matrix //
		Matrix.multiplyMM(_mMVPMatrix, 0, _mProjectionMatrix, 0, GLcanvas.getLook(), 0);
		return _mMVPMatrix;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		
		// Load the texture for each graphic
		for(Graphic curr : Graphic.values())
			curr.loadGLTexture(gl, _context);	
		// Enable Depth Buffering //
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		// Set the background frame color //
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Adjust the viewport based on geometry changes, such as screen rotation //
		GLES20.glViewport(0, 0, width, height);

		float ratio = (float) width / height;

		// Projection matrix is applied to object coordinates in the onDrawFrame() method //
		Matrix.perspectiveM(_mProjectionMatrix, 0, 90, ratio, 0.1f, 5f);
	}
}
