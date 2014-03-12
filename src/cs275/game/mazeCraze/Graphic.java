package cs275.game.mazeCraze;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Scanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

/**
 * A two-dimensional square for use as a drawn object in OpenGL ES 2.0.
 */
public enum Graphic {
	BRICK(R.drawable.red_brick), DIRT(R.drawable.dirt);
	private int _imageid;
	private int a_PositionHandle;
	private int u_TransformHandle;
	private int a_TextureCoordHandle;
	private int u_TextureDataHandle;
	private int[] mtextureHandle = new int[1];

	private static int mProgram;
	private static int vertexShader;
	private static String vertexShaderCode = "";
	private static int fragmentShader;
	private static String fragmentShaderCode = "";

	private static final int FLOAT_SIZE = 4;
	private static final int INT_SIZE = 4;
	private static final int COORDS_PER_VERTEX = 3;
	private static final int COORDS_PER_TEXTURE = 2;
	private static final String TAG = "gl";

	private FloatBuffer vertexCoordsBuffer;
	private ArrayList<Float> vertexCoordsArray = new ArrayList<Float>();

	private FloatBuffer textureCoordsBuffer;
	private ArrayList<Float> textureCoordsArray = new ArrayList<Float>();

	private IntBuffer drawOrderBuffer;
	private ArrayList<Integer> drawOrderArray = new ArrayList<Integer>();

	Graphic(int imageid) {
		_imageid = imageid;
	}

	public int getVertexCount() {
		return vertexCoordsArray.size() / 3;
	}

	public void appendArrays(ArrayList<Float> vertices, ArrayList<Float> textures, ArrayList<Integer> orders) {
		vertexCoordsArray.addAll( vertices );
		textureCoordsArray.addAll( textures );
		drawOrderArray.addAll( orders );
	}

	public void initializeBuffers() {
		float[] vertexCoords = new float[vertexCoordsArray.size()];
		float[] textureCoords = new float[textureCoordsArray.size()];
		int[] drawOrder = new int[drawOrderArray.size()];
		for ( int i = 0; i < vertexCoordsArray.size(); i++ )
			vertexCoords[i] = vertexCoordsArray.get( i );
		for ( int i = 0; i < textureCoordsArray.size(); i++ )
			textureCoords[i] = textureCoordsArray.get( i );
		for ( int i = 0; i < drawOrderArray.size(); i++ )
			drawOrder[i] = drawOrderArray.get( i );
		//		Log.v( "vertices", vertexCoordsArray.toString() );
		//		Log.v( "textures", textureCoordsArray.toString() );
		//		Log.v( "draws", drawOrderArray.toString() );

		// initialize vertex byte buffer for shape coordinates
		// (# of coordinate values * 4 bytes per float)
		ByteBuffer vertexbytebuffer = ByteBuffer.allocateDirect( vertexCoords.length * FLOAT_SIZE );
		vertexbytebuffer.order( ByteOrder.nativeOrder() );
		vertexCoordsBuffer = vertexbytebuffer.asFloatBuffer();
		vertexCoordsBuffer.put( vertexCoords );
		vertexCoordsBuffer.position( 0 );

		// initialize byte buffer for texture coordinates
		// (# of coordinate values * 4 bytes per float)
		ByteBuffer texturebytebuffer = ByteBuffer.allocateDirect( textureCoords.length * FLOAT_SIZE );
		texturebytebuffer.order( ByteOrder.nativeOrder() );
		textureCoordsBuffer = texturebytebuffer.asFloatBuffer();
		textureCoordsBuffer.put( textureCoords );
		textureCoordsBuffer.position( 0 );

		// initialize byte buffer for the draw list
		// (# of coordinate values * 2 bytes per short)
		ByteBuffer drawbytebuffer = ByteBuffer.allocateDirect( drawOrder.length * INT_SIZE );
		drawbytebuffer.order( ByteOrder.nativeOrder() );
		drawOrderBuffer = drawbytebuffer.asIntBuffer();
		drawOrderBuffer.put( drawOrder );
		drawOrderBuffer.position( 0 );
	}

	public void loadGLTexture(Context context) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;
		// loading texture from resource
		Bitmap bitmap = BitmapFactory.decodeResource( context.getResources(), _imageid, options );

		GLES20.glGenTextures( 1, mtextureHandle, 0 );
		// Bind to the texture in OpenGL
		GLES20.glBindTexture( GLES20.GL_TEXTURE_2D, mtextureHandle[0] );

		// Set filtering
		GLES20.glTexParameteri( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST );
		GLES20.glTexParameteri( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR );

		GLES20.glTexParameteri( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT );
		GLES20.glTexParameteri( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT );

		// Load the bitmap into the bound texture.
		GLUtils.texImage2D( GLES20.GL_TEXTURE_2D, 0, bitmap, 0 );

		// Recycle the bitmap, since its data has been loaded into OpenGL.
		bitmap.recycle();
	}

	/**
	 * Encapsulates the OpenGL ES instructions for drawing this shape.
	 * 
	 * @param mvpMatrix
	 *            - The Model View Project matrix in which to draw this shape.
	 */
	public void draw(float[] mvpMatrix) {
		// Add program to OpenGL environment
		GLES20.glUseProgram( mProgram );

		// get handle to vertex shader's vPosition member
		a_PositionHandle = GLES20.glGetAttribLocation( mProgram, "a_Position" );

		// Enable a handle to the vertices
		GLES20.glEnableVertexAttribArray( a_PositionHandle );

		// Prepare the coordinate data
		GLES20.glVertexAttribPointer( a_PositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, COORDS_PER_VERTEX
				* FLOAT_SIZE, vertexCoordsBuffer );

		// get handle to vertex shader's TexCoordinate member
		a_TextureCoordHandle = GLES20.glGetAttribLocation( mProgram, "a_TextureCoord" );

		// Enable a handle to the texture's coordinates
		GLES20.glEnableVertexAttribArray( a_TextureCoordHandle );

		// Prepare the texture coordinate data
		GLES20.glVertexAttribPointer( a_TextureCoordHandle, COORDS_PER_TEXTURE, GLES20.GL_FLOAT, false,
				COORDS_PER_TEXTURE * FLOAT_SIZE, textureCoordsBuffer );

		// get handle to shape's transformation matrix
		u_TextureDataHandle = GLES20.glGetUniformLocation( mProgram, "u_TextureData" );

		// Set the active texture unit to texture unit 0.
		GLES20.glActiveTexture( GLES20.GL_TEXTURE0 );
		checkGlError( "glActiveTexture" );

		// Bind the texture to this unit.
		GLES20.glBindTexture( GLES20.GL_TEXTURE_2D, mtextureHandle[0] );
		checkGlError( "glBindTexture" );

		// Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
		GLES20.glUniform1i( u_TextureDataHandle, 0 );
		checkGlError( "glUniform1i" );

		// get handle to shape's transformation matrix
		u_TransformHandle = GLES20.glGetUniformLocation( mProgram, "u_Transform" );
		checkGlError( "glGetUniformLocation" );

		// Apply the projection and view transformation
		GLES20.glUniformMatrix4fv( u_TransformHandle, 1, false, mvpMatrix, 0 );
		checkGlError( "glUniformMatrix4fv" );

		// Draw the square
		GLES20.glDrawElements( GLES20.GL_TRIANGLE_STRIP, drawOrderArray.size(), GLES20.GL_UNSIGNED_INT, drawOrderBuffer );
		checkGlError( "glDrawArrays" );

		// Disable vertex array
		GLES20.glDisableVertexAttribArray( a_PositionHandle );

		// Disable vertex array
		GLES20.glDisableVertexAttribArray( a_TextureCoordHandle );
	}

	public static void loadShaderCode(Context context) {
		Scanner scan = new Scanner( context.getResources().openRawResource( R.raw.vertexshader ) );
		while ( scan.hasNext() )
			vertexShaderCode += scan.nextLine();
		scan.close();
		scan = new Scanner( context.getResources().openRawResource( R.raw.fragmentshader ) );
		while ( scan.hasNext() )
			fragmentShaderCode += scan.nextLine();
		scan.close();

		vertexShader = loadShader( GLES20.GL_VERTEX_SHADER, vertexShaderCode );
		fragmentShader = loadShader( GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode );

		mProgram = GLES20.glCreateProgram(); // create empty OpenGL Program
		GLES20.glAttachShader( mProgram, vertexShader ); // add the vertex shader to program
		GLES20.glAttachShader( mProgram, fragmentShader ); // add the fragment shader to program
		GLES20.glLinkProgram( mProgram ); // create OpenGL program executables
	}

	public static int loadShader(int type, String shaderCode) {
		// create a vertex shader type (GLES20.GL_VERTEX_SHADER)
		// or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
		int shader = GLES20.glCreateShader( type );

		// add the source code to the shader and compile it
		GLES20.glShaderSource( shader, shaderCode );
		GLES20.glCompileShader( shader );

		return shader;
	}

	public static void checkGlError(String glOperation) {
		int error;
		while ( ( error = GLES20.glGetError() ) != GLES20.GL_NO_ERROR ) {
			Log.e( TAG, glOperation + ": glError " + error );
			throw new RuntimeException( glOperation + ": glError " + error );
		}
	}

}