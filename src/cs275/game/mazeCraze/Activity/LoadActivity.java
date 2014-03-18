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
import android.widget.TextView;

import com.cloudmine.api.CMApiCredentials;

import cs275.game.mazeCraze.CMClient;
import cs275.game.mazeCraze.Grid;
import cs275.game.mazeCraze.MazeNavigatorView;
import cs275.game.mazeCraze.R;

public class LoadActivity extends Activity implements OnClickListener, OnItemLongClickListener {
	//TODO eventually we will add suggested mazes if nothing is returned....
	CMClient cmClient;

	private static ArrayList<Grid> _grids = new ArrayList<Grid>();
	private static ArrayAdapter<Grid> _adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );

		// Initialize cloudmine //
		cmClient = new CMClient( LoadActivity.this, "Search success! Loading Results...",
				"Search failure! No results to load." );
		CMApiCredentials.initialize( cmClient.getAppId(), cmClient.getApiKey(), getApplicationContext() );

		setContentView( R.layout.load_menu );

		Button ret = (Button) findViewById( R.id.btnreturn );
		ret.setOnClickListener( this );

		Button search = (Button) findViewById( R.id.btnsearch );
		search.setOnClickListener( this );

		// Create adapter for listview //
		_adapter = new ArrayAdapter<Grid>( this, android.R.layout.simple_list_item_1, _grids );

		// Create the display as a list view, using the information adapted above. //
		ListView searchResults = (ListView) findViewById( R.id.lstResults );
		searchResults.setAdapter( _adapter );
		// Listener for long press. //
		searchResults.setOnItemLongClickListener( this );
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId() ) {
		case R.id.btnreturn:
			NavUtils.navigateUpFromSameTask( this );
			break;
		case R.id.btnsearch:
			// Query the grids saved on cloud mine using the user inputed info //
			String mazeName = ( (TextView) findViewById( R.id.txtgridx ) ).getText().toString();
			String mazeCreator = ( (TextView) findViewById( R.id.txtMazeCreator ) ).getText().toString();
			cmClient.searchForGrids( mazeName, mazeCreator );
			break;
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long rowid) {
		// position is the index of the item that was clicked. //
		if ( position < _grids.size() )
			setContentView( new MazeNavigatorView( this, _grids.get( position ) ) );
		return true;
	}

	public static void refresh(ArrayList<Grid> updated) {
		// Update the listview's contents //
		_grids.clear();
		_grids.addAll( updated );
		_adapter.notifyDataSetChanged();
	}

}
