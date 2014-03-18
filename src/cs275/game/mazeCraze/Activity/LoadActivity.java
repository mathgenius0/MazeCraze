package cs275.game.mazeCraze.Activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.cloudmine.api.CMApiCredentials;

import cs275.game.mazeCraze.CloudMineClient;
import cs275.game.mazeCraze.Grid;
import cs275.game.mazeCraze.MazeNavigatorView;
import cs275.game.mazeCraze.R;

public class LoadActivity extends Activity implements OnClickListener, OnItemLongClickListener {
	//TODO eventually we will add suggested mazes if nothing is returned....
	private static String NO_RESULTS_FOUND = "No search results were found. Please try other search parameters.";
	CloudMineClient cmClient;
	
	private ArrayList<Grid> _grids = new ArrayList<Grid>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
	
		cmClient = new CloudMineClient(LoadActivity.this, "Grid successfully loaded.", "Grid failed to load.");
		CMApiCredentials.initialize(cmClient.getAppId(), cmClient.getApiKey(), getApplicationContext());
		
		setContentView( R.layout.load_menu );

		refresh();
	}
	
	public void refresh() {
		// Don't reset content view since this is the only menu in the class, and because we want //
		// the information in the text view to persist after failed search. //
		
		Button ret = (Button) findViewById( R.id.btnreturn );
		ret.setOnClickListener( this );
		
		Button search = (Button) findViewById(R.id.btnsearch);
		search.setOnClickListener(this);
		
		( (ListView) findViewById(R.id.lstResults) ).setOnItemLongClickListener(this); // Listener for long press. //
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArrayListAsStrings(_grids) );
		
		// Create the display as a list view, using the information adapted above. //
		ListView searchResults = (ListView) findViewById(R.id.lstResults);
		searchResults.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId() ) {
		case R.id.btnreturn:
			NavUtils.navigateUpFromSameTask(this);
			break;
		case R.id.btnsearch:
			String mazeName = findViewById( R.id.txtgridx ).toString();
			String mazeCreator = findViewById( R.id.txtMazeCreator ).toString();
			
			_grids = locateGrids(mazeName, mazeCreator);
			refresh();
			break;
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// arg2 is the index of the item that was clicked. //
		if( arg2 < _grids.size() )
			setContentView( new MazeNavigatorView( this, _grids.get(arg2) ) );
		else
			refresh();
		
		return true;
	}
	
	public ArrayList<String> ArrayListAsStrings(ArrayList<Grid> raw) {
		ArrayList<String> converted = new ArrayList<String>();
		String tmp = "";
		
		if( raw.size() == 0 ) { //TODO initial message should read a little different i think.... 
			tmp = NO_RESULTS_FOUND;
			converted.add(tmp);
		} else {
			for(Grid current : raw) {
				tmp = current.getName() + " | " + current.getCreator();
				converted.add(tmp);
			}
		}
		
		return converted;
	}
	
	public ArrayList<Grid> locateGrids(String mazeName, String MazeCreator) {
		ArrayList<Grid> found = new ArrayList<Grid>();
		// TODO locate via CM query
		// TODO de-serialize list of mazes from database
		
		return found;
	}


}
