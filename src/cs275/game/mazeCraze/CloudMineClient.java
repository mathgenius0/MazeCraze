package cs275.game.mazeCraze;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.cloudmine.api.rest.callbacks.ObjectModificationResponseCallback;
import com.cloudmine.api.rest.response.ObjectModificationResponse;

public class CloudMineClient {
	
	// Find this in your developer console
	private static final String APP_ID = "849d9c0416c54f8b9b9569717b67ead3";
	// Find this in your developer console
	private static final String API_KEY = "4d0454120b944a63a14eb9d645088460";
	
	private Context _context;
	private String _success = "success";
	private String _failure = "fail";
	
	public CloudMineClient(Context context, String success, String failure) {
		_context = context;
		_success = success;
		_failure = failure;
	}
	
	private ObjectModificationResponseCallback responseCallback = new ObjectModificationResponseCallback() {
		
		public void onCompletion(ObjectModificationResponse response) {
			if ( response.wasSuccess() )
				Toast.makeText( _context, _success, Toast.LENGTH_SHORT ).show();
		}

		public void onFailure(Throwable e, String msg) {
			Log.v( "cloudmine", "failed to save grid", e );
			Toast.makeText( _context, _failure, Toast.LENGTH_SHORT ).show();
		}
	};
	
	public ObjectModificationResponseCallback getResponseCallBack() { return responseCallback; }
	
	public String getAppId() { return APP_ID; }
	public String getApiKey() { return API_KEY; }

}
