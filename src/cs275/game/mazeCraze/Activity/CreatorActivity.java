package cs275.game.mazeCraze.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cloudmine.api.CMApiCredentials;

import cs275.game.mazeCraze.CloudMineClient;
import cs275.game.mazeCraze.MazeCreatorView;
import cs275.game.mazeCraze.R;
import cs275.game.mazeCraze.Graphics.Graphic;

public class CreatorActivity extends Activity implements OnClickListener {
	
	MazeCreatorView creatorView;
	
	CloudMineClient cmClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		
		cmClient = new CloudMineClient(CreatorActivity.this, "Grid successfully saved.", "Grid failed to save.");
		CMApiCredentials.initialize(cmClient.getAppId(), cmClient.getApiKey(), getApplicationContext());
		
		// Import the intent, i.e. the input from the previous menu //
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		int sizeX = intent.getIntExtra("gridx", 10);
		int sizeY = intent.getIntExtra("gridy", 10);
		Graphic walls = Graphic.valueOf(intent.getStringExtra("walls"));
		Graphic floors = Graphic.valueOf(intent.getStringExtra("floors"));
		
		setContentView(R.layout.creator_menu);
		LinearLayout layout = (LinearLayout) findViewById(R.id.laycreator);

		// Make the creator view for display, which needs the grid //
		creatorView = new MazeCreatorView(this, sizeX, sizeY, walls, floors, name);
		layout.addView(creatorView);

		// Create the buttons //
		Button returnButton = (Button) findViewById(R.id.btnreturn);
		returnButton.setOnClickListener(this);
		Button generatorButton = (Button) findViewById(R.id.btngenerate);
		generatorButton.setOnClickListener(this);
		Button saveButton = (Button) findViewById(R.id.btnsave);
		saveButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {		
		switch ( v.getId() ) {
		case R.id.btnreturn:
			NavUtils.navigateUpFromSameTask( this );
			break;
		case R.id.btngenerate:
			//TODO
			break;
		case R.id.btnsave:
			creatorView.setCreator("default");
			creatorView.getGrid().save( cmClient.getResponseCallBack() );
			break;
		}
	}
}
