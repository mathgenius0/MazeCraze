/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cs275.game.mazeCraze;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

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
	//TITS(R.drawable.texture),	
	BRICK(R.drawable.red_brick),
	DIRT(R.drawable.dirt);
	//STUB("stub.png");
	
	private int _imageid;
	private final int mProgram;
	private int a_PositionHandle;
	private int u_TransformHandle;
	private int a_TextureCoordHandle;
	private int u_TextureDataHandle;
	private int[] mtextureHandle = new int[1];
	
	// This matrix member variable provides a hook to manipulate
	// the coordinates of the objects that use this vertex shader
	// The matrix must be included as a modifier of gl_Position.
	// Note that the uMVPMatrix factor *must be first* in order
	// for the matrix multiplication product to be correct.
	private final String vertexShaderCode =
	"attribute vec2 a_TextureCoord;" +
	"varying vec2 v_TexCoordinate;" +
	"uniform mat4 u_Transform;" +
	"attribute vec4 a_Position;" +
	"void main() {" +
		"gl_Position = u_Transform * a_Position;" +
		"v_TexCoordinate = a_TextureCoord;" +
	"}";

	private final String fragmentShaderCode = 
	"uniform sampler2D u_TextureData;" +
	"varying vec2 v_TexCoordinate;" +
	"precision mediump float;" +
	"void main() {" +
		"gl_FragColor = texture2D(u_TextureData, v_TexCoordinate);" +
	"}";

	static final int COORDS_PER_VERTEX = 3;
	static final int COORDS_PER_TEXTURE = 2;
	static final int VERTICES = 4;

	private FloatBuffer textureCoordsBuffer; // buffer holding the texture coordinates
	private float textureCoords[] = {
			// Mapping coordinates for the vertices
			0.0f, 1.0f, // top left		(V2)
			0.0f, 0.0f, // bottom left	(V1)
			1.0f, 1.0f, // top right	(V4)
			1.0f, 0.0f // bottom right	(V3)
	};
	
	private final FloatBuffer vertexCoordsBuffer;
	private float vertexCoords[] = {
			0.0f, 0.0f, 0.0f, // bottom left
			0.0f, 1.0f, 0.0f, // top left
			1.0f, 0.0f, 0.0f, // bottom right
			1.0f, 1.0f, 0.0f  // top right
		};

	private static final String TAG = "gl";

	Graphic(int imageid) {
		_imageid = imageid;
	
		// initialize vertex byte buffer for shape coordinates
		// (# of coordinate values * 4 bytes per float)
		ByteBuffer bb = ByteBuffer.allocateDirect(vertexCoords.length * 4);
		bb.order(ByteOrder.nativeOrder());
		vertexCoordsBuffer = bb.asFloatBuffer();
		vertexCoordsBuffer.put(vertexCoords);
		vertexCoordsBuffer.position(0);
	
		// initialize byte buffer for texture coordinates
		// (# of coordinate values * 4 bytes per float)
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(textureCoords.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		textureCoordsBuffer = byteBuffer.asFloatBuffer();
		textureCoordsBuffer.put(textureCoords);
		textureCoordsBuffer.position(0);
	
		// prepare shaders and OpenGL program
		int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
		int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
	
		mProgram = GLES20.glCreateProgram(); // create empty OpenGL Program
		GLES20.glAttachShader(mProgram, vertexShader); // add the vertex shader to program
		GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
		GLES20.glLinkProgram(mProgram); // create OpenGL program executables
	}

	public void loadGLTexture(GL10 gl, Context context) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;
		// loading texture from resource
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), _imageid, options);
		
		GLES20.glGenTextures(1, mtextureHandle, 0);
		// Bind to the texture in OpenGL
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mtextureHandle[0]);

		// Set filtering
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

		// Load the bitmap into the bound texture.
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

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
		GLES20.glUseProgram(mProgram);

		// get handle to vertex shader's vPosition member
		a_PositionHandle = GLES20.glGetAttribLocation(mProgram, "a_Position");

		// Enable a handle to the vertices
		GLES20.glEnableVertexAttribArray(a_PositionHandle);

		// Prepare the coordinate data
		GLES20.glVertexAttribPointer(a_PositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, COORDS_PER_VERTEX * 4,
				vertexCoordsBuffer);

		// get handle to vertex shader's TexCoordinate member
		a_TextureCoordHandle = GLES20.glGetAttribLocation(mProgram, "a_TextureCoord");

		// Enable a handle to the texture's coordinates
		GLES20.glEnableVertexAttribArray(a_TextureCoordHandle);

		// Prepare the texture coordinate data
		GLES20.glVertexAttribPointer(a_TextureCoordHandle, COORDS_PER_TEXTURE, GLES20.GL_FLOAT, false,
				COORDS_PER_TEXTURE * 4, textureCoordsBuffer);

		// get handle to shape's transformation matrix
		u_TextureDataHandle = GLES20.glGetUniformLocation(mProgram, "u_TextureData");

		// Set the active texture unit to texture unit 0.
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		checkGlError("glActiveTexture");

		// Bind the texture to this unit.
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mtextureHandle[0]);
		checkGlError("glBindTexture");

		// Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
		GLES20.glUniform1i(u_TextureDataHandle, 0);
		checkGlError("glUniform1i");

		// get handle to shape's transformation matrix
		u_TransformHandle = GLES20.glGetUniformLocation(mProgram, "u_Transform");
		checkGlError("glGetUniformLocation");

		// Apply the projection and view transformation
		GLES20.glUniformMatrix4fv(u_TransformHandle, 1, false, mvpMatrix, 0);
		checkGlError("glUniformMatrix4fv");

		// Draw the square
		GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
		checkGlError("glDrawArrays");

		// Disable vertex array
		GLES20.glDisableVertexAttribArray(a_PositionHandle);
		
		// Disable vertex array
		GLES20.glDisableVertexAttribArray(a_TextureCoordHandle);
	}

	public static void checkGlError(String glOperation) {
		int error;
		while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
			Log.e(TAG, glOperation + ": glError " + error);
			throw new RuntimeException(glOperation + ": glError " + error);
		}
	}

	public static int loadShader(int type, String shaderCode) {

		// create a vertex shader type (GLES20.GL_VERTEX_SHADER)
		// or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
		int shader = GLES20.glCreateShader(type);

		// add the source code to the shader and compile it
		GLES20.glShaderSource(shader, shaderCode);
		GLES20.glCompileShader(shader);

		return shader;
	}

}