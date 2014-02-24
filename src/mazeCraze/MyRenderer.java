package mazeCraze;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

public class MyRenderer implements GLSurfaceView.Renderer {
	private final float[] _mProjectionMatrix = new float[16];
	private Camera _camera = new Camera();
	
	private ArrayList<Block> _blocks = new ArrayList<Block>(); //TODO this goes here?
	
	@Override
	public void onDrawFrame(GL10 gl) {

		// Enable Depth Buffering //
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		// Draw background color //
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

		float[] transformedForScreenDisplay = convertToScreenMatrix();
		// Draw blocks //
		for (Block b : _blocks) {//use this or a grid??
			b.draw(transformedForScreenDisplay);
		}
	}
	
	/**
	 * Calculate the projection and view transformation.
	 * @return 
	 */
	private float[] convertToScreenMatrix() {
		float[] _mMVPMatrix = new float[16];
		// Combine projection and camera rotation for screen display matrix //
		Matrix.multiplyMM(_mMVPMatrix, 0, _mProjectionMatrix, 0, _camera.smoothRotation(), 0);
		
		return _mMVPMatrix;
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Adjust the viewport based on geometry changes, such as screen rotation //
		GLES20.glViewport(0, 0, width, height);

		float ratio = (float) width / height;

		// Projection matrix is applied to object coordinates in the onDrawFrame() method //
		Matrix.perspectiveM(_mProjectionMatrix, 0, 90, ratio, 0.1f, 10f);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Set the background frame color // TODO anything else goes in here?
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	}
}
