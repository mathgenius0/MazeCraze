package cs275.game.mazeCraze;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GLcanvas extends GLSurfaceView {

	private Input myInput = new Input("ontouch");
	private static Camera myCamera = new Camera();
	private static Grid grid;

	public GLcanvas(Context context) {
		super(context);
		setEGLContextClientVersion(2);
		grid = new Grid();
		grid.toggleBlock(0,0);
		grid.toggleBlock(1,1);
		grid.toggleBlock(1,0);
		setRenderer( new MyRenderer(context, grid) );//TODO is renderer and grid class the same??
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
	public static float[] getLook()
	{
		return myCamera.smoothRotation();
	}
	public static boolean checkGrid(int x, int y)
	{
		return grid.isTraversible(x,y);
	}
}