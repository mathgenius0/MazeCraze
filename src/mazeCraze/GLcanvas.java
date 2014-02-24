package mazeCraze;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GLcanvas extends GLSurfaceView {

	private Input myInput = new Input("ontouch");
	private Camera myCamera = new Camera();

	public GLcanvas(Context context, Grid grid) {
		super(context);
		setEGLContextClientVersion(2);
		setRenderer( new MyRenderer() );//TODO is renderer and grid class the same??
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

		setOnTouchListener(myInput);
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
}