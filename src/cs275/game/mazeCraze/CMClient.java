package cs275.game.mazeCraze;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.cloudmine.api.CMObject;
import com.cloudmine.api.persistance.ClassNameRegistry;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.callbacks.CMObjectResponseCallback;
import com.cloudmine.api.rest.callbacks.ObjectModificationResponseCallback;
import com.cloudmine.api.rest.response.CMObjectResponse;
import com.cloudmine.api.rest.response.ObjectModificationResponse;

import cs275.game.mazeCraze.Activity.LoadActivity;

public class CMClient {
	
	// Find this in your developer console
	private static final String APP_ID = "849d9c0416c54f8b9b9569717b67ead3";
	// Find this in your developer console
	private static final String API_KEY = "4d0454120b944a63a14eb9d645088460";
	
	static {
	    ClassNameRegistry.register(Grid.CLASS_NAME, Grid.class);
	}
	
	private Context _context;
	private String _success = "success";
	private String _failure = "fail";
	
	private ArrayList<Grid> _results;
	
	public CMClient(Context context, String success, String failure) {
		_context = context;
		_success = success;
		_failure = failure;
	}
	
	private ObjectModificationResponseCallback modificationResponseCallback = new ObjectModificationResponseCallback() {
		
		public void onCompletion(ObjectModificationResponse response) {
			if ( response.wasSuccess() )
				Toast.makeText( _context, _success, Toast.LENGTH_SHORT ).show();
		}

		public void onFailure(Throwable e, String msg) {
			Log.v( "cloudmine", _failure, e );
			Toast.makeText( _context, _failure, Toast.LENGTH_SHORT ).show();
		}
	};
	
	private CMObjectResponseCallback gridResponseCallback = new CMObjectResponseCallback() {
		public void onCompletion(CMObjectResponse response) { 
			_results = new ArrayList<Grid>();
			
			if ( response.wasSuccess() ) {
				if( response.getObjects().isEmpty() )
					Toast.makeText( _context, _failure, Toast.LENGTH_SHORT ).show();
				else {
					Toast.makeText( _context, _success, Toast.LENGTH_SHORT ).show();
					for( CMObject object : response.getObjects() ){
						Grid grid = (Grid) object;
						_results.add(grid);
					}
				}
				LoadActivity.refresh(_results);
			}
	    }
		
		public void onFailure(Throwable e, String msg) {
			Log.v( "cloudmine", _failure, e );
			Toast.makeText( _context, _failure, Toast.LENGTH_SHORT ).show();
		}
	};
	
	public ObjectModificationResponseCallback getModificationResponseCallBack() { return modificationResponseCallback; }
	
	public String getAppId() { return APP_ID; }
	public String getApiKey() { return API_KEY; }
	
	public void searchForGrids(String mazeName, String mazeCreator) {
		// Null values will be read as incorrect syntax //
		if( mazeName.equals("") )
			mazeName = "unknown";
		if( mazeCreator.equals("") )
			mazeCreator = "unknown";
				
		// compound queries of any number are allowed as long as you don't mix "," (and) and "or" //
		String cmQuery = "[name = /" + mazeName + "/i or creator = /" + mazeCreator + "/i]";
		
		CMStore store = CMStore.getStore();
		store.loadApplicationObjectsSearch(cmQuery, gridResponseCallback);
	}
	
}
